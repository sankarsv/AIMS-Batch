package com.aims.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aims.model.Project;

@Repository
public interface ProjectRepository<P> extends JpaRepository<Project, Integer> {
	
	
}
