package PM.login.model;

/**
 *
 * @author andreendo
 */
public class User {
    private String username, password;
    private UserType type;
    private boolean bloqueado;
    
    public User(String username, String password, UserType type, boolean bloqueado) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.bloqueado = bloqueado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public UserType getType() {
        return type;
    }   
}
