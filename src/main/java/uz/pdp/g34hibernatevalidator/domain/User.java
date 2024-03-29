package uz.pdp.g34hibernatevalidator.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @Column(nullable = false)
    @Email
    private String email;

    @Length(min = 8, max = 24)
    @NotBlank
    private String password;

    @URL
    private String imgUrl;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Past(message = "birthDate cannot be past")
    private LocalDate birthDate;

    @Range(min = 1, max = 100)
    private Double salary;

    @CreditCardNumber
    private String cardNumber;
}
