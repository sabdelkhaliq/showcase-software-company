package model;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
public class Department {
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String name;


}
