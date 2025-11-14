package hu.unideb.inf.suitup.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "wardrobe_items")
public class WardrobeItemEntity {
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

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "topics", nullable = true, columnDefinition = "TEXT")
    private String topics;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WardrobeItemEntity item = (WardrobeItemEntity) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Transient
    private List<String> topicList;

    public void prepareTopicList() {
        if (this.topics != null && !this.topics.trim().isEmpty()) {
            this.topicList = Arrays.asList(this.topics.trim().split("\\s+"));
        } else {
            this.topicList = Collections.emptyList();
        }
    }
}
