package com.haulmatic.usermanagement.repository;

import com.haulmatic.usermanagement.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long>{
}
