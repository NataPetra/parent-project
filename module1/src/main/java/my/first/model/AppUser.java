package my.first.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "USER_NAME")
    private  String username;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_ROLE")
    private String role;
}
