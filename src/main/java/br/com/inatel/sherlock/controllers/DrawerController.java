package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Drawer;
import br.com.inatel.sherlock.services.DrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class DrawerController {

    @Autowired
    DrawerService drawerService;

    @GetMapping("/drawer/listInLocker/{lockerId}")
    public List<Drawer> checkDrawersInLocker(@PathVariable int lockerId) {
        return drawerService.getByLockerId(lockerId);
    }

    @GetMapping("/drawer/availability/{id}")
    public Boolean checkDrawerAvailability(@PathVariable int id) {
        Optional<Drawer> drawerOptional = drawerService.getById(id);

        if(drawerOptional.isPresent()) {
            Drawer drawer = drawerOptional.get();
            return drawer.getAvailable();
        }
        return null;
    }

    @GetMapping("/drawer/reserve/{id}")
    public Drawer reserveDrawer(int id) {
        Optional<Drawer> drawerOptional = drawerService.getById(id);

        if(drawerOptional.isPresent()) {
            Drawer drawer = drawerOptional.get();
            drawer.setAvailable(false);

            return drawer;
        }
        return null;
    }
}
