package com.sherlock.apiservice.service;

import com.sherlock.apiservice.model.Locker;
import com.sherlock.apiservice.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerService {
    LockerRepository lockerRepository;

    @Autowired
    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public List<Locker> getAll() {
        return lockerRepository.findAll();
    }

    public Locker getLockerByID(Integer id) {
        return lockerRepository.findById(id).orElse(null);
    }

    public Locker setLocker(Locker locker) {
        return lockerRepository.save(locker);
    }

    public Locker updateLocker(Locker updatedLocker) {
        Locker locker = lockerRepository.findById(updatedLocker.getId()).orElse(null);

        if(locker != null) {
            if(updatedLocker.getAddress() != null)
                locker.setAddress(updatedLocker.getAddress());
            if(updatedLocker.getLatitude() != null)
                locker.setLatitude(updatedLocker.getLatitude());
            if(updatedLocker.getLongitude() != null)
                locker.setLongitude(updatedLocker.getLongitude());

            return lockerRepository.save(locker);
        }
        return null;
    }

    public Boolean deleteLocker(Integer id) {
        if(lockerRepository.existsById(id))  {
            lockerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
