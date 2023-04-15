package fghost.carview.v1.category.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String code;
    private String categoryName;
}
