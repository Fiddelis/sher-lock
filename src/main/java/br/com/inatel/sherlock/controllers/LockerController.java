package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Locker;
import br.com.inatel.sherlock.services.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/locker")
public class LockerController {
    @Autowired
    private LockerService lockerService;

    @GetMapping("/list")
    public @ResponseBody ResponseEntity<List<Locker>> listLocker() {
        return ResponseEntity.ok(lockerService.getAll());
    }
}
