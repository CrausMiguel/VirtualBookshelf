package com.api.services;

import com.api.dtos.GenderDto;
import com.api.dtos.GenderPagination;
import com.api.models.Gender;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.UUID;
@Controller
public interface GenderService {
    Gender createGender(GenderDto genderDto);
    Gender updateGender(GenderDto genderDto, UUID id);
    Optional<Gender> getGenderDetails(UUID id);
    GenderPagination getAllGenders(int pageNum, int pageSize);
    Optional<Gender> getGenderByDescription(String description);
    void deleteGender(UUID id);
}
