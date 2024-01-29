package ru.alex.FisrtSecurityApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "website")
public class Website {
    @Column(name = "website_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int websiteId;

    @Column(name = "name")
    @Pattern(
            regexp = "[A-Za-zА-Яа-я]+.[A-Za-zА-Яа-я]+",
            message = "Название сайта должно быть в формате \"название_сайта.домен\"."
    )
    @NotEmpty(message = "Имя сайта не должно быть пустым.")
    @Size(min = 5, max = 100, message = "Название сайта должно быть от 5 до 100 символов.")
    private String name;

    @NotEmpty(message = "Логин не должен быть пустым")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 3,message = "Длина пароля должна быть от 3-х симолов.")
    @Column(name = "password")
    private String password;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    public Website(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Website{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
