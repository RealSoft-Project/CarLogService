package realsoft.carservice.carlogservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import realsoft.carservice.carlogservice.entity.CarLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CarLogRepository extends JpaRepository<CarLog,Long> {
    List<CarLog> findByEndTimeLessThan(LocalDate date);
}
