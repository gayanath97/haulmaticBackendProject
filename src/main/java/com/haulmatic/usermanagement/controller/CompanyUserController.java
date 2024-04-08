package com.haulmatic.usermanagement.controller;

import com.haulmatic.usermanagement.dto.CompanyUserDTO;
import com.haulmatic.usermanagement.service.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class CompanyUserController {


    private CompanyUserService companyUserService;

    @Autowired
    public CompanyUserController(CompanyUserService companyUserService) {
        this.companyUserService = companyUserService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> createCompanyUser(@RequestBody  CompanyUserDTO companyUserDTO) {
        return companyUserService.createCompanyUser(companyUserDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> updateCompanyUser(@PathVariable Long id, @RequestBody CompanyUserDTO companyUserDTO) {
        return companyUserService.updateCompanyUser(id, companyUserDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CompanyUserDTO> getCompanyUserById(@PathVariable Long id) {
        return companyUserService.getCompanyUserById(id);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CompanyUserDTO>> getAllCompanyUsers() {
        return companyUserService.getAllCompanyUsers();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCompanyUser(@PathVariable Long id) {
        return companyUserService.deleteCompanyUser(id);
    }

}
