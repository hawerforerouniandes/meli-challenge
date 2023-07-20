package com.challenge.meli.controllers;

import com.challenge.meli.dto.request.TopSecretRequestDto;
import com.challenge.meli.dto.response.TopSecretResponseDto;
import com.challenge.meli.services.ITopSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/topsecret")
public class TopSecretController {

    private ITopSecretService service;

    @Autowired
    public TopSecretController(ITopSecretService service) {
        this.service = service;
    }
    @ResponseBody
    @RequestMapping(path = "/", method = RequestMethod.POST, produces = "application/json")
    public TopSecretResponseDto topSecret(@RequestBody TopSecretRequestDto request) {
        try {
            CompletableFuture<TopSecretResponseDto> futureResult = this.service.addTopSecretData(request);
            return new ResponseEntity<TopSecretResponseDto>(futureResult.get(), HttpStatus.OK).getBody();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
