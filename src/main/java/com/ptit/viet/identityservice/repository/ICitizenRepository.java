package com.ptit.viet.identityservice.repository;

import com.ptit.viet.identityservice.model.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitizenRepository extends JpaRepository<Citizen, Long> {

}
