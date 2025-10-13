package hu.unideb.inf.suitup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "wardrobe_items")
public class WardrobeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String season;

    private String brand;

    @Column(nullable = false)
    private String color;

    private String material;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
