package team.gdsc.code2cv.feature.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.gdsc.code2cv.feature.resume.dto.ResumeRes;
import team.gdsc.code2cv.feature.resume.entity.Resume;
import team.gdsc.code2cv.feature.resume.repository.ResumeRepository;
import team.gdsc.code2cv.global.jwt.JwtUser;

@Service
@RequiredArgsConstructor
public class ResumeService {
	private final ResumeRepository resumeRepository;

	public List<ResumeRes.ResumeDto> getAllResumes(JwtUser jwtUser) {
		List<Resume> resumes = resumeRepository.findAllByUserId(jwtUser.getId());

		return resumes.stream()
			.map(ResumeRes.ResumeDto::from)
			.toList();
	}
}
