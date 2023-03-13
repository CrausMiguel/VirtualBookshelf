package com.api.controllers;

import com.api.dtos.GenderDto;
import com.api.dtos.GenderPagination;
import com.api.exceptions.GenderNotFoundException;
import com.api.models.Gender;
import com.api.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/gender")
public class GenderController {
    private final GenderService genderService;
    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping("")
    public ResponseEntity<Gender> createGender(@RequestBody GenderDto newGender){
        return ResponseEntity.status(HttpStatus.CREATED).body(genderService.createGender(newGender));
    }

    @PutMapping("")
    public ResponseEntity<Gender> updateGender(@RequestBody GenderDto genderUpdate,
                                               @RequestParam(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(genderService.updateGender(genderUpdate, id));
    }

    @GetMapping("")
    public ResponseEntity<GenderPagination> getAllGenders(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue= "10", required = false) int pageSize){
        return ResponseEntity.status(HttpStatus.FOUND).body(genderService.getAllGenders(pageNum, pageSize));
    }

    @GetMapping("/detail")
    public ResponseEntity<Gender> getGenderDetails(@RequestParam(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.FOUND).body(genderService.getGenderDetails(id));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteGender(@RequestParam(value= "id") UUID id){
        genderService.deleteGender(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
