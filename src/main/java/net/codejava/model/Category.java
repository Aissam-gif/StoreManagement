package net.codejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    /*
        One Category can be managed by many users
     */
    @OneToMany
    private List<User> users;

    /*
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

     */

}
