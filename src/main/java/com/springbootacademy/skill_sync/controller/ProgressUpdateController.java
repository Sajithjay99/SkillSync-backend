package com.springbootacademy.skill_sync.controller;

import com.springbootacademy.skill_sync.dto.ProgressUpdateDTO;
import com.springbootacademy.skill_sync.service.ProgressUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/progress-update")
@CrossOrigin
public class ProgressUpdateController {

    private final ProgressUpdateService progressUpdateService;

    @Autowired
    public ProgressUpdateController(ProgressUpdateService progressUpdateService) {
        this.progressUpdateService = progressUpdateService;
    }

    @PostMapping("/create")
    public String createProgressUpdate(@RequestBody ProgressUpdateDTO progressUpdateDTO) {
        return progressUpdateService.createProgressUpdate(progressUpdateDTO);
    }

    @GetMapping("/view/{id}")
    public ProgressUpdateDTO getProgressUpdate(@PathVariable String id) {
        return progressUpdateService.getProgressUpdateById(id);
    }

    @PutMapping("/update/{id}")
    public String updateProgressUpdate(@PathVariable String id, @RequestBody ProgressUpdateDTO progressUpdateDTO) {
        return progressUpdateService.updateProgressUpdate(id, progressUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProgressUpdate(@PathVariable String id) {
        return progressUpdateService.deleteProgressUpdate(id);
    }
}
