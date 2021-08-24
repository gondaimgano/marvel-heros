package zw.co.marvel.marvelheros.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "hero")
@Entity
@Getter
@Setter
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

}