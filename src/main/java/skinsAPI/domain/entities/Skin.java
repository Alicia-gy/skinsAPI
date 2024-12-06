package skinsAPI.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skin")
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "colour", nullable = false)
    private String colour;


    @Override
    public String toString() {
        return "name: " + getName() +
                "\ntype: " + getType() +
                "\nprice " + getPrice() +
                "\ncolour " + getColour() +
                "\nid: " + getId();
    }


}
