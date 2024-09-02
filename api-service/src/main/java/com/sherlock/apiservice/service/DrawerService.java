package com.sherlock.apiservice.service;

import com.sherlock.apiservice.model.Drawer;
import com.sherlock.apiservice.repository.DrawerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawerService {
    DrawerRepository drawerRepository;

    @Autowired
    public DrawerService(DrawerRepository drawerRepository) {
        this.drawerRepository = drawerRepository;
    }

    public Drawer getDrawerByID(Integer id) {
        return drawerRepository.findById(id).orElse(null);
    }

    public Drawer setDrawer(Drawer drawer) {
        return drawerRepository.save(drawer);
    }

    public Drawer updateDrawer(Drawer updatedDrawer) {
        Drawer drawer = drawerRepository.findById(updatedDrawer.getId()).orElse(null);

        if(drawer != null) {
            if(updatedDrawer.getLockerId() != null)
                drawer.setLockerId(updatedDrawer.getLockerId());
            if(updatedDrawer.getDimension() != null)
                drawer.setDimension(updatedDrawer.getDimension());
            if(updatedDrawer.getAvailable() != null)
                drawer.setAvailable(updatedDrawer.getAvailable());

            return drawerRepository.save(drawer);
        }
        return null;
    }

    public Boolean deleteDrawer(Integer id) {
        if(drawerRepository.existsById(id))  {
            drawerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
