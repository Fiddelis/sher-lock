package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Drawer;
import br.com.inatel.sherlock.services.DrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drawer")
public class DrawerController {

    @Autowired
    DrawerService drawerService;

    @GetMapping("/list-in-locker/{lockerId}")
    public ResponseEntity<List<Drawer>> checkDrawersInLocker(@PathVariable Long lockerId) {
        List<Drawer> drawers = drawerService.getByLockerId(lockerId);

        if (drawers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Drawer> getDrawer(@PathVariable Long id) {
        Optional<Drawer> drawerOptional = drawerService.getById(id);

        return drawerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/reserve/{id}")
    public ResponseEntity<Drawer> reserveDrawer(@PathVariable Long id) {
        Optional<Drawer> drawerOptional = drawerService.getById(id);

        if (drawerOptional.isPresent()) {
            Drawer drawer = drawerOptional.get();
            drawer.setAvailable(false);
            drawerService.save(drawer);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Drawer> createDrawer(@RequestBody Drawer drawerSave) {
        Drawer drawer = drawerService.save(drawerSave);

        if (drawer != null)
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

}
