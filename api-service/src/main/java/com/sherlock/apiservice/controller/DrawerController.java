package com.sherlock.apiservice.controller;

import com.sherlock.apiservice.model.Drawer;
import com.sherlock.apiservice.service.DrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drawer")
public class DrawerController {
    private final DrawerService drawerService;
    private final Map<String, String> errorResponse = new HashMap<>();
    private Drawer drawer;

    @Autowired
    public DrawerController(DrawerService drawerService) {
        this.drawerService = drawerService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDrawers() {
        List<Drawer> drawers = drawerService.getAll();

        if(drawers.isEmpty()) {
            errorResponse.put("error", "drawers not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(drawers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDrawer(@PathVariable Integer id) {
        drawer = drawerService.getDrawerByID(id);

        if (drawer == null) {
            errorResponse.put("error", "drawer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(drawer);
    }

    @GetMapping("/by_locker/{id}")
    public ResponseEntity<Object> getDrawersByLockerID(@PathVariable Integer id) {
        List<Drawer> drawers = drawerService.getDrawersByLockerID(id);

        if (drawers.isEmpty()) {
            errorResponse.put("error", "drawers not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(drawers);
    }

    @PostMapping
    public ResponseEntity<Object> createDrawer(@RequestBody Drawer drawer) {

        if(drawer == null) {
            errorResponse.put("error", "missing information");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        this.drawer = drawerService.setDrawer(drawer);

        return ResponseEntity.ok(this.drawer);
    }

    @PutMapping
    public ResponseEntity<Object> updateDrawer(@RequestBody Drawer drawer) {
        this.drawer = drawerService.updateDrawer(drawer);

        if(this.drawer == null) {
            errorResponse.put("error", "drawer not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(this.drawer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDrawer(@PathVariable Integer id) {
        HashMap<String, String> successResponse = new HashMap<>();
        successResponse.put("message", "drawer successfully deleted");

        if(!drawerService.deleteDrawer(id)) {
            errorResponse.put("error", "drawer was not deleted");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(successResponse);
    }
}
