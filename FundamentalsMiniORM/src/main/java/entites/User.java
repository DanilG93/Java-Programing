package entites;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import orm.MyConnector;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.sql.Connection;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "users")
public class User {
    @Id
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "age")
    private int age;
    @Column(name = "registration")
    private LocalDate registration;
    @Column(name = "test")
    private String test;

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
    }
}
