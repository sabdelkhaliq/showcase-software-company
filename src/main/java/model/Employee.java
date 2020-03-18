package model;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
public class Employee {
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer departmentId;
}
