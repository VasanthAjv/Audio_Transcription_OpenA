package com.example.audio_transcribe.service;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.audio_transcribe.model.Candidate;
import com.example.audio_transcribe.repository.CandidateRepository;
import com.example.audio_transcribe.service.impl.OpenAIService;

import jakarta.validation.Valid;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final OpenAIService openAiService;

    public CandidateService(CandidateRepository candidateRepository, OpenAIService openAiService) {
        this.candidateRepository = candidateRepository;
        this.openAiService = openAiService;
    }

    @Transactional
    public Candidate saveCandidate(@Valid Candidate candidate) {
        // Enhance objective using OpenAI if not provided or empty
        if (candidate.getObjective() == null || candidate.getObjective().isBlank()) {
            String generatedObjective = openAiService.generateObjective(
                candidate.getPreferredJobRole(),
                candidate.getPreferredIndustry(),
                candidate.getExperienceLevel()
            );
            candidate.setObjective(generatedObjective);
        }

        // Set candidate reference for related entities
        setCandidateReferences(candidate);

        return candidateRepository.save(candidate);
    }

    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    @Transactional
    public Candidate updateCandidate(Long id, @Valid Candidate updatedCandidate) {
        Candidate existingCandidate = candidateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));

        // Update fields
        updateCandidateFields(existingCandidate, updatedCandidate);

        // Enhance objective if updated or empty
        if (updatedCandidate.getObjective() == null || updatedCandidate.getObjective().isBlank()) {
            String generatedObjective = openAiService.generateObjective(
                updatedCandidate.getPreferredJobRole(),
                updatedCandidate.getPreferredIndustry(),
                updatedCandidate.getExperienceLevel()
            );
            existingCandidate.setObjective(generatedObjective);
        }

        // Set candidate reference for related entities
        setCandidateReferences(existingCandidate);

        return candidateRepository.save(existingCandidate);
    }

    @Transactional
    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw new RuntimeException("Candidate not found with id: " + id);
        }
        candidateRepository.deleteById(id);
    }

    private void setCandidateReferences(Candidate candidate) {
        // Set candidate reference for related entities to maintain JPA relationships
        if (candidate.getWorkExperienceList() != null) {
            candidate.getWorkExperienceList().forEach(work -> work.setCandidate(candidate));
        }
        if (candidate.getEducationList() != null) {
            candidate.getEducationList().forEach(edu -> edu.setCandidate(candidate));
        }
        if (candidate.getProjectList() != null) {
            candidate.getProjectList().forEach(project -> project.setCandidate(candidate));
        }
        if (candidate.getCertificationList() != null) {
            candidate.getCertificationList().forEach(cert -> cert.setCandidate(candidate));
        }
        if (candidate.getInternshipList() != null) {
            candidate.getInternshipList().forEach(intern -> intern.setCandidate(candidate));
        }
    }

    private void updateCandidateFields(Candidate existing, Candidate updated) {
        // Update basic info
        existing.setFullname(updated.getFullname());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setLocation(updated.getLocation());
        existing.setNationality(updated.getNationality());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setGender(updated.getGender());
        existing.setProfilePictureUrl(updated.getProfilePictureUrl());

        // Update professional links
        existing.setLinkedInUrl(updated.getLinkedInUrl());
        existing.setGithubUrl(updated.getGithubUrl());
        existing.setPortfolioUrl(updated.getPortfolioUrl());
        existing.setPersonalWebsite(updated.getPersonalWebsite());
        existing.setTwitterUrl(updated.getTwitterUrl());
        existing.setBlogUrl(updated.getBlogUrl());

        // Update experience
        existing.setExperienceLevel(updated.getExperienceLevel());
        existing.setYearsOfExperience(updated.getYearsOfExperience());
        existing.setCurrentJobTitle(updated.getCurrentJobTitle());
        existing.setCurrentCompany(updated.getCurrentCompany());

        // Update summaries
        existing.setProfessionalSummary(updated.getProfessionalSummary());

        // Update collections
        existing.setTechnicalSkills(updated.getTechnicalSkills());
        existing.setSoftSkills(updated.getSoftSkills());
        existing.setToolsAndFrameworks(updated.getToolsAndFrameworks());
        existing.setLanguages(updated.getLanguages());
        existing.setAchievements(updated.getAchievements());
        existing.setHobbies(updated.getHobbies());
        existing.setReferences(updated.getReferences());

        // Update related entities
        existing.setWorkExperienceList(updated.getWorkExperienceList());
        existing.setEducationList(updated.getEducationList());
        existing.setProjectList(updated.getProjectList());
        existing.setCertificationList(updated.getCertificationList());
        existing.setInternshipList(updated.getInternshipList());

        // Update preferences
        existing.setTemplateType(updated.getTemplateType());
        existing.setPreferredJobRole(updated.getPreferredJobRole());
        existing.setPreferredIndustry(updated.getPreferredIndustry());
        existing.setPreferredLocation(updated.getPreferredLocation());
        existing.setIncludePhoto(updated.getIncludePhoto());
        existing.setFontPreference(updated.getFontPreference());
        existing.setColorScheme(updated.getColorScheme());
    }
}