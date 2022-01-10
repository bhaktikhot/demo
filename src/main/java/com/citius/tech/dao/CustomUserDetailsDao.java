package com.citius.tech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citius.tech.bean.HospitalUserRegistration;

@Repository
public interface CustomUserDetailsDao extends JpaRepository<HospitalUserRegistration, Long> {

	@Query(value = "Select * from hospital_User_Registration where username = :userName", nativeQuery = true)
	HospitalUserRegistration findByUsername(@Param("userName") String username);

}
