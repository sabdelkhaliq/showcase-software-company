package model;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
public class Employee {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int depatmentId;
}
