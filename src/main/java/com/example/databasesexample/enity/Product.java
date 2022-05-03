package com.example.databasesexample.enity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product extends Model {

    @Length(max = 5, message = "title must be less then 5")
    String title;

    @NotNull
    @Max(value = 30, message = "cost must be less then 30")
    BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    ProductCategory category;

}
