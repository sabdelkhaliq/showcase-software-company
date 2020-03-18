package model;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
public class Department {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;


}
