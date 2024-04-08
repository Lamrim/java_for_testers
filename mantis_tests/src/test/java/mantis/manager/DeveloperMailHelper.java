package mantis.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeUtility;
import mantis.manager.developerMail.AddUserResponse;
import mantis.manager.developerMail.GetIdsResponse;
import mantis.manager.developerMail.GetMessageResponse;
import mantis.model.DeveloperMailUser;
import okhttp3.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.CookieManager;
import java.time.Duration;

public class DeveloperMailHelper extends HelperBase {

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client;

    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public DeveloperMailUser addUser() {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url("https://www.developermail.com/api/v1/mailbox")
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            var text = response.body().string();
            var addUserresponse = new ObjectMapper().readValue(text, AddUserResponse.class);
            if (!addUserresponse.success()) {
                throw new RuntimeException(addUserresponse.errors().toString());
            }
            return addUserresponse.result();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(DeveloperMailUser user) {
        Request request = new Request.Builder()
                .url(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()))
                .header("X-MailboxToken", user.token())
                .delete()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String receive(DeveloperMailUser user, Duration duration) {
        var startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + duration.toMillis()) {
            try {
                var request1 =
                        get(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()), user.token());
                GetIdsResponse response1 = new ObjectMapper().readValue(request1, GetIdsResponse.class);
                if (!response1.success()) {
                    throw new RuntimeException(response1.errors().toString());
                }
                if (!response1.result().isEmpty()) {
                    var request2 =
                            get(String.format("https://www.developermail.com/api/v1/mailbox/%s/messages/%s",
                                    user.name(), response1.result().getFirst()), user.token());
                    var response2 = new ObjectMapper().readValue(request2, GetMessageResponse.class);
                    if (!response2.success()) {
                        throw new RuntimeException(response2.errors().toString());
                    }
                    return new String(MimeUtility.decode
                            (new ByteArrayInputStream(response2.result().getBytes()), "quoted-printable").readAllBytes());
                }
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail");

    }

    String get(String url, String token) {
        Request request = new Request.Builder()
                .url(url)
                .header("X-MailboxToken", token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
