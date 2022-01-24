package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.validation.Constraint;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class Login {
    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;

    public String validate() throws NoSuchAlgorithmException {
        if (authenticate(username, password) == null) {
            return "Invalid user or password";
        }
        return null;
    }

    public static Admin authenticate(String username, String password) throws java.security.NoSuchAlgorithmException {
        Model.Finder<Long, Admin> find = new Model.Finder<Long, Admin> (
                Long.class, Admin.class
        );
        String hashedPassword = "";
        if(password != null) {
            hashedPassword = sha512(password);
        }
        return find.where()
                .eq("username", username)
                .eq("password", hashedPassword)
                .findUnique();
    }

    public static String sha512(String message) throws java.security.NoSuchAlgorithmException {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-512");
        StringBuilder sb = new StringBuilder();
        md.update(message.getBytes());
        byte[] mb = md.digest();
        for(byte m : mb) {
            String hex = String.format("%02x", m);
            sb.append(hex);
        }
        return sb.toString();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
