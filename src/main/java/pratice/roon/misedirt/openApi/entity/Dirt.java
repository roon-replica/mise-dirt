package pratice.roon.misedirt.openApi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Dirt extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dirtId;

    private int pm10;

    private int pm25;

    @ManyToOne
    @JoinColumn(name="name")
    private Region region;
}
