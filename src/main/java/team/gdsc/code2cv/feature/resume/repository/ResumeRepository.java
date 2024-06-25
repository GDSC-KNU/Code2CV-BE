package team.gdsc.code2cv.feature.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.gdsc.code2cv.feature.resume.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}