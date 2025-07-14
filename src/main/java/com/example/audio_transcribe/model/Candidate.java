package com.example.audio_transcribe.model;

import java.time.LocalDate;
import java.util.List;

import javax.naming.Reference;

import com.example.audio_transcribe.enums.ExperienceLevel;
import com.example.audio_transcribe.enums.TemplateType;
import com.example.audio_transcribe.validator.CandidateConsistency;
import com.example.audio_transcribe.validator.ValidPhone;
import com.example.audio_transcribe.validator.ValidUrl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CandidateConsistency // this is custom validator annotation
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Full name is required")
	@Size(min=2 ,max=100 ,message="Full name must be between 2 and 100 characters")
	private String Fullname;
	
	
	 @NotBlank(message = "Email is required")
	 @Email(message = "Invalid email format")
	 @Size(max = 255, message = "Email must not exceed 255 characters")
	 private String email;
	 
	 
	 @NotBlank(message = "Phone number is required")
	 @ValidPhone(message="Invalid phone number format")
	 private String phone;
	
	 

	    @NotBlank(message = "Location is required")
	    @Size(max = 200, message = "Location must not exceed 200 characters")
	    private String location;

	    @Size(max = 100, message = "Nationality must not exceed 100 characters")
	    private String nationality;

	    @Past(message = "Date of birth must be in the past")
	    @NotNull(message = "Date of birth is required")
	    private LocalDate dateOfBirth;

	    @Size(max = 50, message = "Gender must not exceed 50 characters")
	    private String gender;

	    @ValidUrl(message = "Invalid profile picture URL")
	    private String profilePictureUrl;

	    // 🔹 Professional Links
	    @ValidUrl(message = "Invalid LinkedIn URL")
	    private String linkedInUrl;

	    @ValidUrl(message = "Invalid GitHub URL")
	    private String githubUrl;

	    @ValidUrl(message = "Invalid portfolio URL")
	    private String portfolioUrl;

	    @ValidUrl(message = "Invalid personal website URL")
	    private String personalWebsite;

	    @ValidUrl(message = "Invalid Twitter URL")
	    private String twitterUrl;

	    @ValidUrl(message = "Invalid blog URL")
	    private String blogUrl;

	    // 🔹 Experience
	    @NotNull(message = "Experience level is required")
	    @Enumerated(EnumType.STRING)
	    private ExperienceLevel experienceLevel;

	    @Min(value = 0, message = "Years of experience cannot be negative")
	    private Integer yearsOfExperience;

	    @Size(max = 100, message = "Current job title must not exceed 100 characters")
	    private String currentJobTitle;

	    @Size(max = 100, message = "Current company must not exceed 100 characters")
	    private String currentCompany;

	    // 🔹 Career Objective / Summary
	    @Size(max = 1000, message = "Objective must not exceed 1000 characters")
	    private String objective;

	    @Size(max = 1000, message = "Professional summary must not exceed 1000 characters")
	    private String professionalSummary;

	    // 🔹 Skills
	    @NotEmpty(message = "At least one technical skill is required")
	    @Size(max = 50, message = "Maximum 50 technical skills allowed")
	    @ElementCollection
	    private List<@NotBlank(message = "Technical skill cannot be blank") @Size(max = 100, message = "Technical skill must not exceed 100 characters") String> technicalSkills;

	    @Size(max = 50, message = "Maximum 50 soft skills allowed")
	    @ElementCollection
	    private List<@NotBlank(message = "Soft skill cannot be blank") @Size(max = 100, message = "Soft skill must not exceed 100 characters") String> softSkills;

	    @Size(max = 50, message = "Maximum 50 tools/frameworks allowed")
	    @ElementCollection
	    private List<@NotBlank(message = "Tool/framework cannot be blank") @Size(max = 100, message = "Tool/framework must not exceed 100 characters") String> toolsAndFrameworks;

	    // 🔹 Languages Known
	    @NotEmpty(message = "At least one language is required")
	    @Size(max = 10, message = "Maximum 10 languages allowed")
	    @ElementCollection
	    @Valid
	    private List<LanguageProficiency> languages;

	    // 🔹 Achievements
	    @Size(max = 20, message = "Maximum 20 achievements allowed")
	    @ElementCollection
	    @Column(length = 1000)
	    private List<@NotBlank(message = "Achievement cannot be blank") @Size(max = 1000, message = "Achievement must not exceed 1000 characters") String> achievements;

	    // 🔹 Work Experience
	    @Size(max = 20, message = "Maximum 20 work experiences allowed")
	    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
	    @Valid
	    private List<WorkExperience> workExperienceList;

	    // 🔹 Education
	    @NotEmpty(message = "At least one education entry is required")
	    @Size(max = 10, message = "Maximum 10 education entries allowed")
	    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
	    @Valid
	    private List<Education> educationList;

	    // 🔹 Projects
	    @Size(max = 20, message = "Maximum 20 projects allowed")
	    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
	    @Valid
	    private List<Project> projectList;

	    // 🔹 Certifications
	    @Size(max = 20, message = "Maximum 20 certifications allowed")
	    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
	    @Valid
	    private List<Certification> certificationList;

	    // 🔹 Internships
	    @Size(max = 20, message = "Maximum 20 internships allowed")
	    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
	    @Valid
	    private List<Internship> internshipList;

	    // 🔹 Hobbies and Interests
	    @Size(max = 20, message = "Maximum 20 hobbies allowed")
	    @ElementCollection
	    private List<@NotBlank(message = "Hobby cannot be blank") @Size(max = 100, message = "Hobby must not exceed 100 characters") String> hobbies;

	    // 🔹 References
	    @Size(max = 5, message = "Maximum 5 references allowed")
	    @ElementCollection
	    @Valid
	    private List<Reference> references;

	    // 🔹 Template Preference
	    @NotNull(message = "Template type is required")
	    @Enumerated(EnumType.STRING)
	    private TemplateType templateType;

	    // 🔹 Additional Preferences
	    @Size(max = 100, message = "Preferred job role must not exceed 100 characters")
	    private String preferredJobRole;

	    @Size(max = 100, message = "Preferred industry must not exceed 100 characters")
	    private String preferredIndustry;

	    @Size(max = 200, message = "Preferred location must not exceed 200 characters")
	    private String preferredLocation;

	    @NotNull(message = "Include photo preference is required")
	    private Boolean includePhoto;

	    @Size(max = 50, message = "Font preference must not exceed 50 characters")
	    private String fontPreference;

	    @Size(max = 50, message = "Color scheme must not exceed 50 characters")
	    private String colorScheme;
	
	
}
