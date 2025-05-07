package com.springbootacademy.skill_sync.controller;


import com.example.pafbackend.models.Learning;
import com.example.pafbackend.repositories.LearningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/learning")
public class LearningController {

    private final LearningRepository learningRepository;

    @Autowired
    public LearningController(LearningRepository learningRepository) {
        this.learningRepository = learningRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Learning>> getLearningByUserId(@PathVariable String userId) {
        List<Learning> learningEntries = learningRepository.findByUserId(userId);
        return new ResponseEntity<>(learningEntries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Learning> createLearning(@RequestBody Learning learning) {
        if (learning.getTimestamp() == null) {
            learning.setTimestamp(LocalDateTime.now());
        }
        Learning savedLearning = learningRepository.save(learning);
        return new ResponseEntity<>(savedLearning, HttpStatus.CREATED);
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<Learning> getLearningById(@PathVariable String id) {
        Optional<Learning> learning = learningRepository.findById(id);
        return learning.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Learning> updateLearning(@PathVariable String id, @RequestBody Learning updatedLearning) {
        Optional<Learning> optionalLearning = learningRepository.findById(id);
        if (optionalLearning.isPresent()) {
            Learning existingLearning = optionalLearning.get();

            if (!existingLearning.getUserId().equals(updatedLearning.getUserId())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            if (updatedLearning.getTopic() != null) {
                existingLearning.setTopic(updatedLearning.getTopic());
            }
            if (updatedLearning.getDescription() != null) {
                existingLearning.setDescription(updatedLearning.getDescription());
            }
            if (updatedLearning.getResourceLink() != null) {
                existingLearning.setResourceLink(updatedLearning.getResourceLink());
            }
            if (updatedLearning.getStatus() != null) {
                existingLearning.setStatus(updatedLearning.getStatus());
            }
            if (updatedLearning.getReflection() != null) {
                existingLearning.setReflection(updatedLearning.getReflection());
            }
            if (updatedLearning.getTemplate() != null) {
                existingLearning.setTemplate(updatedLearning.getTemplate());
            }

            if (updatedLearning.getCertificationName() != null) {
                existingLearning.setCertificationName(updatedLearning.getCertificationName());
            }
            if (updatedLearning.getProvider() != null) {
                existingLearning.setProvider(updatedLearning.getProvider());
            }
            if (updatedLearning.getDateObtained() != null) {
                existingLearning.setDateObtained(updatedLearning.getDateObtained());
            }
            if (updatedLearning.getChallengeName() != null) {
                existingLearning.setChallengeName(updatedLearning.getChallengeName());
            }
            if (updatedLearning.getResult() != null) {
                existingLearning.setResult(updatedLearning.getResult());
            }

            Learning savedLearning = learningRepository.save(existingLearning);
            return new ResponseEntity<>(savedLearning, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearning(@PathVariable String id, @RequestParam String userId) {
        Optional<Learning> learning = learningRepository.findById(id);
        if (learning.isPresent()) {
            if (!learning.get().getUserId().equals(userId)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            learningRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
  