package org.example.amazonupdate.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotEmpty(message = "the name cannot be empty")
    @Length(min = 6,message = "the length should be more than 5")
    @Column(columnDefinition = "varchar(10) check(length(userName)>6)  not null")
    private String userName;
    @NotEmpty(message = "the password cannot be empty ")
    @Length(min = 6,message = "the password should be at least 6 char")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$" ,message = "the password should be letter and numbers")
   @Column(columnDefinition = "varchar(15) check(length (password)>6 ) not null ")
    private String password;
    @NotEmpty(message = "the email cannot be empty ")
    @Email
    @Column(columnDefinition = "varchar(20) unique not null ")
    private String email;
    @NotEmpty(message = "the role cannot be empty")
    @Pattern(regexp = "(admin|customer)" ,message = "the role should be admin OR customer")
    @Column(columnDefinition = "varchar(8) not null check (role='admin' or role='customer')")
    private String role;
    @NotNull(message = "the balance cannot be empty")
    @Positive
    @Column(columnDefinition = "double not null ")
    private double balance;
}
