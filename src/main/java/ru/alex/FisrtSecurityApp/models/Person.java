package ru.alex.FisrtSecurityApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "person")
public class Person {

    @Column(name = "person_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Size(min = 2, max = 100, message = "Имя должно быть длиной от 2 до 100 символов")
    @Column(name = "username")
    private String username;

    @Min(value = 1900,message = "Год рождения должен быть больше, чем 1900")
    @NotNull(message = "Год рождения не должен быть пустым.")
    @Column(name = "year_of_birth")
    private Integer yearOfBirth;

    @NotEmpty(message = "Пароль не должен быть пустым.")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "person")
    private List<Website> websites;

    public Person(String username, int yearOfBirth, String password) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }
}
