package PM.login.PM;

import PM.login.model.UserType;
import PM.login.model.User;
import PM.login.DAO.UserDAO;

/**
 *
 * @author andreendo
 */
public class AddUserPM {
    private String username, password, confirmPassword;
    private String selectedType;
    UserDAO userDao;

    public AddUserPM() {
        username = "";
        password = "";
        confirmPassword = "";
        selectedType = "normal";
    }

    public void clear() {
        username = "";
        password = "";
        confirmPassword = "";
        selectedType = "normal";
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    
    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public String save() throws Exception {
        if(! password.equals(confirmPassword))
            throw new Exception("Passwords are different");
        
        if(userDao.exist(username))
            throw new Exception("Username already exists");
        
        UserType type = UserType.ADMIN;
        if(selectedType.equals("normal"))
            type = UserType.NORMALUSER;
        
        User newUser = new User(username, password, type);
        userDao.save(newUser);
        
        clear();
        
        return "New user added";
    }
}
