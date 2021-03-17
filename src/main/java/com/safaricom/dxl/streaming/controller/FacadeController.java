package com.safaricom.dxl.streaming.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.safaricom.dxl.streaming.models.Message;
import com.safaricom.dxl.streaming.producer.ProducerService;
import com.safaricom.dxl.streaming.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msk")
public class FacadeController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/test")
    public String test() {
        return "test : test data";
    }

    @PostMapping("/produce")
    public String msgProduce(@RequestBody Message msg) {
        final String data;
        try {
            data = Utility.getMapper().writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            return "gen-produce : {JsonProcessingException} \n" + e.getMessage();
        }
        producerService.sendMessage(data);
        return "gen-produce : invoked with\n " + data;
    }
}
