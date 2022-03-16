package net.codejava.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartKey  implements Serializable {
    @Column(name = "product_id")
    Long productId;

    @Column(name = "user_id")
    Long userId;
}
