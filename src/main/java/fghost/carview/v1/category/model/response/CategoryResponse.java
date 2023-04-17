package fghost.carview.v1.category.model.response;

import fghost.carview.v1.category.domain.CategoryEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String code;
    private CategoryEnum category;
}
