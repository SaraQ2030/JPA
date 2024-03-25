package org.example.amazonupdate.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class MerchantStock {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @NotNull(message = "the product id cannot be empty")
@Column(columnDefinition = "int not null")
    private Integer product_id;
    @NotNull(message = "the merchant id cannot be empty")
    @Column(columnDefinition = "int not null")
    private Integer merchant_id;
    @NotNull(message = "the stock cannot be empty")
    @Min(value = 11 ,message = "the stock should be at least 11 at start ")
    @Column(columnDefinition = "int not null")
    private Integer stock;

}
