package br.com.inatel.sherlock.controllers;

import br.com.inatel.sherlock.models.Locker;
import br.com.inatel.sherlock.services.LockerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LockerController {
    @Autowired
    private LockerService lockerService;

    @GetMapping("/locker")
    public @ResponseBody String listLocker() {
        List<Locker> lockerList = lockerService.getAll();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(lockerList);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Erro ao converter para JSON";
        }
    }
}
