package team.gdsc.code2cv.feature.resume.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import team.gdsc.code2cv.feature.resume.dto.ResumeRes;
import team.gdsc.code2cv.feature.resume.service.ResumeService;
import team.gdsc.code2cv.global.jwt.JwtUser;

@Tag(name = "Resume", description = "이력서 API")
@RestController
@RequiredArgsConstructor
public class ResumeController {
	private final ResumeService resumeService;

	@GetMapping("/api/resumes")
	public List<ResumeRes.ResumeDto> getResumes(@AuthenticationPrincipal JwtUser jwtUser) {
		return resumeService.getAllResumes(jwtUser);
	}
}
