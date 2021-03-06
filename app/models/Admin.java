package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "admins")
public class Admin extends Model {
    @Id
    public Long id;
    @Constraints.Required
    public String username;
    @Constraints.Required
    public String password;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
