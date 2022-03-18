package net.codejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Table(name = "product")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private BigDecimal price;
    private int quantity;

    @ManyToOne(cascade = { CascadeType.ALL })
    private Category category;

}
