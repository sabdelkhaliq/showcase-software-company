package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode
public class Bug {
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Integer userId;
}
