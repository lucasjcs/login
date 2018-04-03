package PM.login.PM;

import PM.login.model.User;

/**
 *
 * @author andreendo
 */
public class PagePM {
    User loggedUser;
    
    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User user) {
        loggedUser = user;
    }
}
