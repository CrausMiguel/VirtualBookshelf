package com.api.services.impl;

import com.api.dtos.GenderDto;
import com.api.dtos.GenderPagination;
import com.api.exceptions.GenderAlreadyExistException;
import com.api.exceptions.GenderNotFoundException;
import com.api.models.Gender;
import com.api.repositories.GenderRepository;
import com.api.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;
    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public Gender createGender(GenderDto genderDto) {
        checkAlreadyExist(genderDto.getDescription());
        Gender newGender = new Gender();
        newGender.setDescription(genderDto.getDescription());
        return genderRepository.save(newGender);
    }

    @Override
    public Gender updateGender(GenderDto genderDto, UUID id) {
        checkAlreadyExist(genderDto.getDescription(), id);
        Gender searchedGender = getGenderDetails(id).orElseThrow(() -> new GenderNotFoundException("Not Found"));
        searchedGender.setDescription(genderDto.getDescription());
        return genderRepository.save(searchedGender);
    }

    @Override
    public Optional<Gender> getGenderDetails(UUID id) {
        return genderRepository.findById(id);
    }

    @Override
    public GenderPagination getAllGenders(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Gender> genders = genderRepository.findAll(pageable);
        List<Gender> listOfGender = genders.getContent();

        GenderPagination genderPagination = new GenderPagination();
        genderPagination.setContent(listOfGender);
        genderPagination.setPageNo(genders.getNumber());
        genderPagination.setPageSize(genders.getSize());
        genderPagination.setTotalElements(genders.getTotalElements());
        genderPagination.setTotalPages(genders.getTotalPages());
        genderPagination.setLast(genders.isLast());
        return genderPagination;
    }

    @Override
    public Optional<Gender> getGenderByDescription(String description) {
        return genderRepository.getGenderByDescription(description);
    }

    @Override
    public void deleteGender(UUID id) {
        getGenderDetails(id);
        genderRepository.deleteById(id);
    }

    private void checkAlreadyExist(String description, UUID id){
        Optional<Gender> search = getGenderByDescription(description);
        boolean checkExist = search.isPresent() && !search.get().getId().equals(id);
        if(checkExist){
            throw new GenderAlreadyExistException("This gender already exists");
        }
    }

    private void checkAlreadyExist(String description){
        boolean checkExist = getGenderByDescription(description).isPresent();
        if(checkExist){
            throw new GenderAlreadyExistException("This gender already exists");
        }
    }
}
