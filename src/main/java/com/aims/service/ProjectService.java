package com.aims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aims.model.Project;
import com.aims.repository.ProjectRepository;


@Service
public class ProjectService {

	@Autowired
	ProjectRepository<Project> projectRepository;

	@Transactional
	public boolean addProject(Project project) {
		return projectRepository.save(project) != null;
	}
	
	@Transactional
	public List<Project> addProjects(List<Project> projects) {
		//return employeeRepository.save(employees) != null;
		List<Project> response = (List<Project>) projectRepository.saveAll(projects);
		return response;
	}

	@Transactional
	public boolean updateProject(Project project) {
		return projectRepository.save(project) != null;
	}
	
	@Transactional
	public void deleteProject(Integer projectId) {
		projectRepository.deleteById(projectId);
	}

	@Transactional
	public Optional<Project> getById(Integer id) {
		return projectRepository.findById(id);
	}

	@Transactional
	public List<Project> getAllProjects() {
		return (List<Project>) projectRepository.findAll();
	}
	
	

}