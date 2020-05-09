package com.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aims.model.HCDetails;
import com.aims.model.HCDetailsPk;

@Repository
public interface HCMAsterRepository extends JpaRepository<HCDetails, HCDetailsPk> {

	
	List<HCDetails> findAll();
}
