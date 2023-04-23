package realsoft.carservice.carlogservice.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import realsoft.carservice.carlogservice.entity.CarLog;
import realsoft.carservice.carlogservice.repo.CarLogRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class Senders {
    public static final String EXCHANGE = "car.exchange";
    public static final String ROUTING_KEY = "car.key.car.assign";
    public static final String CAR_KEY_CAR_UNASSIGN = "car.key.car.unassign";
    public static final String KEY_DRIVER_ASSIGN = "car.key.driver.assign";
    public static final String CAR_KEY_DRIVER_UNASSIGN = "car.key.driver.unassign";
    @Autowired
    private CarLogRepository repository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void assignCar(Long carId){
        log.info("Sending message to car.assign queue");
     rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY,carId);
    }
    public void unAssignCar(Long carId){
        log.info("Sending message to car.unassign queue");
        rabbitTemplate.convertAndSend(EXCHANGE, CAR_KEY_CAR_UNASSIGN,carId);
    }
    public void assignDriver(Long driverId){
        log.info("Sending message to driver.assign queue");
        rabbitTemplate.convertAndSend(EXCHANGE, KEY_DRIVER_ASSIGN,driverId);
    }
    public void unAssignDriver(Long driverId){
        log.info("Sending message to driver.unassign queue");
        rabbitTemplate.convertAndSend(EXCHANGE, CAR_KEY_DRIVER_UNASSIGN,driverId);
    }
    @Scheduled(fixedDelay = 6000000)
    public void checkExpiredLogs(){
        List<CarLog> carLogList = repository.findByEndTimeLessThan(LocalDate.now());

        for (CarLog carLog: carLogList){
            Long carId = carLog.getCarId();
            Long driverId = carLog.getDriverId();
            unAssignCar(carId);
            unAssignDriver(driverId);
        }
    }
}
