package fghost.carview.v1.users.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class Preference {

    @NotBlank
    @JsonProperty(value = "key")
    private String preferenceKey;

    @NotBlank
    @JsonProperty(value = "value")
    private String preferenceValue;
}
