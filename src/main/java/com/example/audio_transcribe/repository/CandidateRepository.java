package com.example.audio_transcribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.audio_transcribe.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long>{

}
