package com.example.DailyReflectBackend.Controller;

import com.example.DailyReflectBackend.Model.Mood;
import com.example.DailyReflectBackend.Repository.MoodRepository;
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
    public ResponseEntity<Mood> insertMood(@RequestBody @Valid Mood mood) {
        Mood m = moodService.addMood(mood);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Mood> updateMood(@RequestBody @Valid Mood mood, @PathVariable("id") int moodId) {
        Mood m = moodService.updateMood(mood, moodId);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteMood(@PathVariable("id") int moodId) {
        moodService.deleteMood(moodId);
        return ResponseEntity.status(HttpStatus.OK).body("Mood deleted successfully");
    }

    @GetMapping(path = "/getMood")
    public ResponseEntity<Mood> getMood(@RequestParam("id") int moodId) {
        Mood m = moodService.getMoodById(moodId);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @GetMapping(path = "/getAllMood")
    public ResponseEntity<List<Mood>> getAllMood() {
        List<Mood> moodList = moodService.getAllMood();
        return ResponseEntity.status(HttpStatus.OK).body(moodList);
    }
}
