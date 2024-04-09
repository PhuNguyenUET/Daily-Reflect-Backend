package com.example.DailyReflectBackend.Controller;

import com.example.DailyReflectBackend.DTO.MoodDTO;
import com.example.DailyReflectBackend.Service.MoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mood")
public class MoodController {
    private MoodService moodService;

    @Autowired
    public void setMoodService(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<MoodDTO> insertMood(@RequestBody @Valid MoodDTO mood) {
        MoodDTO m = moodService.addMood(mood);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<MoodDTO> updateMood(@RequestBody @Valid MoodDTO mood, @PathVariable("id") int moodId) {
        MoodDTO m = moodService.updateMood(mood, moodId);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteMood(@PathVariable("id") int moodId) {
        moodService.deleteMood(moodId);
        return ResponseEntity.status(HttpStatus.OK).body("Mood deleted successfully");
    }

    @GetMapping(path = "/getMood")
    public ResponseEntity<MoodDTO> getMood(@RequestParam("id") int moodId) {
        MoodDTO m = moodService.getMoodById(moodId);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @GetMapping(path = "/getAllMood")
    public ResponseEntity<List<MoodDTO>> getAllMood() {
        List<MoodDTO> moodList = moodService.getAllMood();
        return ResponseEntity.status(HttpStatus.OK).body(moodList);
    }

    @GetMapping(path = "/getMood")
    public ResponseEntity<MoodDTO> getMoodByMoodString(@RequestParam("mood") String mood) {
        MoodDTO m = moodService.getMoodByMoodString(mood);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }
}
