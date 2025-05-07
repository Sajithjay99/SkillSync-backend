package com.springbootacademy.skill_sync.service.imp;

import com.springbootacademy.skill_sync.dto.ProgressUpdateDTO;
import com.springbootacademy.skill_sync.entity.ProgressUpdate;
import com.springbootacademy.skill_sync.repo.ProgressUpdateRepo;
import com.springbootacademy.skill_sync.service.ProgressUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProgressUpdateServiceIMPL implements ProgressUpdateService {

    private final ProgressUpdateRepo progressUpdateRepo;

    @Autowired
    public ProgressUpdateServiceIMPL(ProgressUpdateRepo progressUpdateRepo) {
        this.progressUpdateRepo = progressUpdateRepo;
    }

    @Override
    public String createProgressUpdate(ProgressUpdateDTO dto) {
        // Validate progress between 0 and 100
        if (dto.getProgress() < 0 || dto.getProgress() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Progress must be between 0 and 100");
        }

        ProgressUpdate progressUpdate = new ProgressUpdate(
                null, dto.getSkillTitle(), dto.getSkillUrl(), dto.getDescription(), dto.getField(),
                dto.getStartDate(), dto.getEndDate(), dto.getProgress()
        );

        progressUpdateRepo.save(progressUpdate);
        return "Progress update created successfully!";
    }

    @Override
    public ProgressUpdateDTO getProgressUpdateById(String id) {
        ProgressUpdate progressUpdate = progressUpdateRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Update not found"));

        return new ProgressUpdateDTO(
                progressUpdate.getSkillTitle(),
                progressUpdate.getSkillUrl(),
                progressUpdate.getDescription(),
                progressUpdate.getField(),
                progressUpdate.getStartDate(),
                progressUpdate.getEndDate(),
                progressUpdate.getProgress()
        );
    }

    @Override
    public String updateProgressUpdate(String id, ProgressUpdateDTO dto) {
        // Validate progress between 0 and 100
        if (dto.getProgress() < 0 || dto.getProgress() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Progress must be between 0 and 100");
        }

        ProgressUpdate progressUpdate = progressUpdateRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Update not found"));

        progressUpdate.setSkillTitle(dto.getSkillTitle());
        progressUpdate.setSkillUrl(dto.getSkillUrl());
        progressUpdate.setDescription(dto.getDescription());
        progressUpdate.setField(dto.getField());
        progressUpdate.setStartDate(dto.getStartDate());
        progressUpdate.setEndDate(dto.getEndDate());
        progressUpdate.setProgress(dto.getProgress());

        progressUpdateRepo.save(progressUpdate);
        return "Progress update updated successfully!";
    }

    @Override
    public String deleteProgressUpdate(String id) {
        ProgressUpdate progressUpdate = progressUpdateRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Progress Update not found"));

        progressUpdateRepo.delete(progressUpdate);
        return "Progress update deleted successfully!";
    }
}
