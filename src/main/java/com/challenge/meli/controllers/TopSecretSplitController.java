package com.challenge.meli.controllers;

import com.challenge.meli.dto.request.TopSecretSplitRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;
import com.challenge.meli.services.ITopSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {

    private ITopSecretService service;

    @Autowired
    public TopSecretSplitController(ITopSecretService service) {
        this.service = service;
    }

    @PostMapping("/{satellite_name}")
    public Boolean addSatelliteData(
            @PathVariable String satellite_name,
            @RequestBody TopSecretSplitRequestDto request) {
        try {
            CompletableFuture<Boolean> futureResult = this.service.addSatelliteData(satellite_name, request);
            return new ResponseEntity<Boolean>(futureResult.get(), HttpStatus.OK).getBody();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(path = "/", method = RequestMethod.GET, produces = "application/json")
    public TopSecretResponseDto getTopSecretData() {
        try {
            CompletableFuture<TopSecretResponseDto> futureResult = this.service.getTopSecretData();

            return new ResponseEntity<TopSecretResponseDto>(futureResult.get(), HttpStatus.OK).getBody();
        }catch (Exception e){
            String msg = e.getMessage();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
