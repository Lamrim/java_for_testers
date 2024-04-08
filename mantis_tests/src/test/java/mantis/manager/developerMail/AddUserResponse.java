package mantis.manager.developerMail;

import mantis.model.DeveloperMailUser;

public record AddUserResponse(Boolean success, Object errors, DeveloperMailUser result) {

}
