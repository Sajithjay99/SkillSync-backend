package com.springbootacademy.skill_sync.service;

import com.springbootacademy.skill_sync.dto.ProgressUpdateDTO;

public interface ProgressUpdateService {
    String createProgressUpdate(ProgressUpdateDTO progressUpdateDTO);
    ProgressUpdateDTO getProgressUpdateById(String id);
    String updateProgressUpdate(String id, ProgressUpdateDTO progressUpdateDTO);
    String deleteProgressUpdate(String id);
}
