package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static common.CommonFunctions.randomString;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }

    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "first name")) {
            for (var lastName : List.of("", "last name")) {
                for (var address : List.of("", "address")) {
                    for (var email1 : List.of("", "email1")) {
                        for (var mobilePhone : List.of("", "7777777")) {
                            result.add(new ContactData()
                                    .withFirstName(firstName)
                                    .withLastName(lastName)
                                    .withAddress(address)
                                    .withEmail1(email1)
                                    .withMobilePhone(mobilePhone));
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= count; i++) {
            result.add(new ContactData(
                    "", CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomString(i * (i + 1)),
                    CommonFunctions.randomFile("./src/test/resources/images")));
        }

        return result;
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (var name : List.of("", "name")) {
            for (var header : List.of("", "header")) {
                for (var footer : List.of("", "footer")) {
                    result.add(new GroupData().
                            withName(name).
                            withHeader(header).
                            withFooter(footer));
                }
            }
        }
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 5))
                    .withHeader(randomString(i * 7))
                    .withFooter(randomString(i * 8)));
        }
        return result;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else if ("yaml".equals(format)) {
            ObjectMapper mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            ObjectMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
