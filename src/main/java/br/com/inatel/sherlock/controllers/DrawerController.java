package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Drawer;
import br.com.inatel.sherlock.services.DrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class DrawerController {

    @Autowired
    DrawerService drawerService;

    public Boolean checkDrawerAvailability(int id) {
        Optional<Drawer> drawerOptional = drawerService.getById(id);

        if(drawerOptional.isPresent()) {
            Drawer drawer = drawerOptional.get();
            return drawer.getAvailable();
        }

        return null;
    }

    public Drawer reserveDrawer(int id) {
        // criar metodo
    }
}
