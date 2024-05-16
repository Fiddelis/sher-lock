package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Locker;
import br.com.inatel.sherlock.services.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locker")
public class LockerController {

    @Autowired
    private LockerService lockerService;

    @GetMapping("/list")
    public ResponseEntity<List<Locker>> listLocker() {
        return ResponseEntity.ok(lockerService.getAll());
    }
}
