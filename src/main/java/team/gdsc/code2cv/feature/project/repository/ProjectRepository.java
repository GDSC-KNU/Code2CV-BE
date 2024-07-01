package team.gdsc.code2cv.feature.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team.gdsc.code2cv.feature.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findAllByUserId(Long userId);
}
