package realsoft.carservice.carlogservice.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realsoft.carservice.carlogservice.entity.CarLog;
import realsoft.carservice.carlogservice.service.CarLogService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/carlogs")
@Api(value = "CarLog service Endpoints", tags = {"CarLogs"})
public class CarLogController {
    @Autowired
    private CarLogService carLogService;

    @GetMapping
    public ResponseEntity<List<CarLog>> getAllLogs(){
        return ResponseEntity.ok(carLogService.getAllLogs());
    }
    @GetMapping("/{logId}")
    public ResponseEntity<CarLog> getLog(@PathVariable Long id) throws FileNotFoundException {
        return ResponseEntity.ok(carLogService.getLog(id));
    }
    @PostMapping
    public ResponseEntity<CarLog> saveLog(@RequestBody CarLog carLog){
        return ResponseEntity.ok(carLogService.saveLog(carLog));
    }
    @PutMapping("/{logId}")
    public ResponseEntity<CarLog> updateLog(@PathVariable Long logId, @RequestBody CarLog carLog) throws FileNotFoundException {
        return ResponseEntity.ok(carLogService.updateLog(logId,carLog));
    }
    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long logId){
        carLogService.deleteLog(logId);
        return ResponseEntity.noContent().build();
    }
}
