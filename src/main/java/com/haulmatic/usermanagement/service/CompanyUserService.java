package com.haulmatic.usermanagement.service;

import com.haulmatic.usermanagement.dto.CompanyUserDTO;
import com.haulmatic.usermanagement.entity.CompanyUser;
import com.haulmatic.usermanagement.repository.CompanyUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyUserService {

    private CompanyUserRepository companyUserRepository;

    @Autowired
    public CompanyUserService(CompanyUserRepository companyUserRepository) {
        this.companyUserRepository = companyUserRepository;
    }

    public ResponseEntity<List<CompanyUserDTO>> getAllCompanyUsers() {
        log.info("Fetching all company users");
        try {
            List<CompanyUser> companyUsers = companyUserRepository.findAll();
            return new ResponseEntity<>(convertToDTO(companyUsers), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<CompanyUserDTO> convertToDTO(List<CompanyUser> companyUsers) {
        log.info("Converting company users to DTO");
        try {
            return companyUsers.stream().map(companyUser -> {
                CompanyUserDTO companyUserDTO = new CompanyUserDTO();
                companyUserDTO.setId(String.valueOf(companyUser.getId()));
                companyUserDTO.setFirstName(companyUser.getFirstName());
                companyUserDTO.setLastName(companyUser.getLastName());
                companyUserDTO.setEmail(companyUser.getEmail());
                companyUserDTO.setAge(String.valueOf(companyUser.getAge()));
                return companyUserDTO;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<String> createCompanyUser(CompanyUserDTO companyUserDTO) {
        log.info("Creating company user");
        try {
            CompanyUser companyUser = new CompanyUser();
            companyUser.setId(Long.valueOf(companyUserDTO.getId()));
            companyUser.setFirstName(companyUserDTO.getFirstName());
            companyUser.setLastName(companyUserDTO.getLastName());
            companyUser.setEmail(companyUserDTO.getEmail());
            companyUser.setAge(Integer.valueOf(companyUserDTO.getAge()));
            companyUserRepository.save(companyUser);
            return new ResponseEntity<>("Company User created successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<CompanyUserDTO> getCompanyUserById(Long id) {
        log.info("Fetching company user by id");
        try {
            CompanyUser companyUser = companyUserRepository.findById(id).orElse(null);
            CompanyUserDTO companyUserDTO = new CompanyUserDTO();
            companyUserDTO.setId(String.valueOf(companyUser.getId()));
            companyUserDTO.setFirstName(companyUser.getFirstName());
            companyUserDTO.setLastName(companyUser.getLastName());
            companyUserDTO.setEmail(companyUser.getEmail());
            companyUserDTO.setAge(String.valueOf(companyUser.getAge()));
            return new ResponseEntity<>(companyUserDTO,  HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> deleteCompanyUser(Long id) {
        log.info("Deleting company user");
        try {
            companyUserRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> updateCompanyUser(Long id, CompanyUserDTO companyUserDTO) {
        log.info("Updating company user");
        try {
            CompanyUser companyUser1 = companyUserRepository.findById(id).orElse(null);
            if (companyUser1 != null) {
                companyUser1.setFirstName(companyUserDTO.getFirstName());
                companyUser1.setLastName(companyUserDTO.getLastName());
                companyUser1.setEmail(companyUserDTO.getEmail());
                companyUser1.setAge(Integer.valueOf(companyUserDTO.getAge()));
                companyUserRepository.save(companyUser1);
                return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
