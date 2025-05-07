package com.example.pafbackend.controllers;

import com.example.pafbackend.models.SkillPlan;
import com.example.pafbackend.repositories.SkillPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skillPlans")
public class SkillPlanController {

    private final SkillPlanRepository skillPlanRepository;

    @Autowired
    public SkillPlanController(SkillPlanRepository skillPlanRepository) {
        this.skillPlanRepository = skillPlanRepository;
    }

    // ✅ Get all skill plans for current user only
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillPlan>> getSkillPlansByUserId(@PathVariable String userId) {
        List<SkillPlan> skillPlans = skillPlanRepository.findByUserId(userId);
        return new ResponseEntity<>(skillPlans, HttpStatus.OK);
    }

    // ✅ Create new skill plan (userId comes from frontend or token logic)
    @PostMapping
    public ResponseEntity<SkillPlan> createSkillPlan(@RequestBody SkillPlan skillPlan) {
        SkillPlan savedSkillPlan = skillPlanRepository.save(skillPlan);
        return new ResponseEntity<>(savedSkillPlan, HttpStatus.CREATED);
    }
