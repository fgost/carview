package fghost.carview.v1.category.domain;

import fghost.carview.v1.type.domain.TypeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private CategoryEnum category;

    @ManyToMany
    @JoinTable(
            name = "categories_types",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<TypeEntity> types;

    @PrePersist
    private void setCode() {
        this.code = UUID.randomUUID().toString();
    }

}
