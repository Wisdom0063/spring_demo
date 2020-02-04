package com.example.demo.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import com.example.demo.model.Phone;
import com.example.demo.dao.PhoneRepository;

@RestController
@RequestMapping("/phones")
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    @PostMapping()
    public Phone addPhone(@Valid @RequestBody Phone phone) {
        return phoneRepository.save(phone);

    }

}
