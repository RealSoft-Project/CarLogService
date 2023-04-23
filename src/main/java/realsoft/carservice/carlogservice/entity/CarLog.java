package realsoft.carservice.carlogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carLogId;
    private Long driverId;
    private Long carId;
    private LocalDate startTime;
    private LocalDate endTime;
}
