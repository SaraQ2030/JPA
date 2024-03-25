package org.example.amazonupdate.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "the name cannot be empty")
    @Length(min = 4,message = "the length should be more than 3")
    @Column(columnDefinition = "varchar(10) check(length(userName)>3)  not null")
    private String name;
    @NotNull(message = "the price cannot be empty")
    @Positive
    @Column(columnDefinition = "double not null")
    private double price;
    @NotNull(message = "the category id  cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer category_id;

}
