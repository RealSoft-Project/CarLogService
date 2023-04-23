package realsoft.carservice.carlogservice.service;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realsoft.carservice.carlogservice.entity.CarLog;
import realsoft.carservice.carlogservice.messaging.Senders;
import realsoft.carservice.carlogservice.repo.CarLogRepository;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CarLogService {
    @Autowired
    private CarLogRepository repository;
    @Autowired
    private Senders senders;

    public List<CarLog> getAllLogs(){
        return repository.findAll();
    }

    public CarLog getLog(Long logId) throws FileNotFoundException {
        Optional<CarLog> carLog = repository.findById(logId);
        if(carLog.isPresent()){
            return carLog.get();
        } else {
            throw new FileNotFoundException("Log Not found with this id: "+ logId);
        }
    }
    public CarLog saveLog(CarLog carLog){
        senders.assignDriver(carLog.getDriverId());
        senders.assignCar(carLog.getCarId());
        return repository.save(carLog);
    }
    public CarLog updateLog(Long logId, CarLog carLog) throws FileNotFoundException {
        Optional<CarLog> log = repository.findById(logId);
        if (log.isPresent()){
            CarLog log1 = log.get();
            log1.setCarId(carLog.getCarId());
            log1.setDriverId(carLog.getDriverId());
            return repository.save(log1);
        }else {
            throw new FileNotFoundException("Log Not found with this id: "+ logId);
        }
    }
    public void deleteLog(Long logId){
        repository.deleteById(logId);
    }
}
