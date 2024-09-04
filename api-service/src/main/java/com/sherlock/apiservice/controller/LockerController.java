package com.sherlock.apiservice.controller;

import com.sherlock.apiservice.model.Locker;
import com.sherlock.apiservice.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locker")
public class LockerController {
    private final LockerService lockerService;
    private final Map<String, String> errorResponse = new HashMap<>();
    private Locker locker;

    @Autowired
    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllLockers() {
        List<Locker> lockers = lockerService.getAll();

        if(lockers.isEmpty()) {
            errorResponse.put("error", "lockers not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(lockers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLocker(@PathVariable Integer id) {
        locker = lockerService.getLockerByID(id);

        if (locker == null) {
            errorResponse.put("error", "locker not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(locker);
    }

    @PostMapping
    public ResponseEntity<Object> createLocker(@RequestBody Locker locker) {

        if(locker == null) {
            errorResponse.put("error", "missing information");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        this.locker = lockerService.setLocker(locker);

        return ResponseEntity.ok(this.locker);
    }

    @PutMapping
    public ResponseEntity<Object> updateLocker(@RequestBody Locker locker) {
        this.locker = lockerService.updateLocker(locker);

        if(this.locker == null) {
            errorResponse.put("error", "locker not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(this.locker);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLocker(@PathVariable Integer id) {
        HashMap<String, String> successResponse = new HashMap<>();
        successResponse.put("message", "locker successfully deleted");

        if(!lockerService.deleteLocker(id)) {
            errorResponse.put("error", "locker was not deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(successResponse);
    }
}
