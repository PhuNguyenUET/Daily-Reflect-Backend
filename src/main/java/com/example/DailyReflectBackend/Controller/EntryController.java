package com.example.DailyReflectBackend.Controller;

import com.example.DailyReflectBackend.DTO.EntryDTO;
import com.example.DailyReflectBackend.Service.EntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/entry")
public class EntryController {
    private EntryService entryService;

    @Autowired
    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<EntryDTO> insertEntry(@RequestBody @Valid EntryDTO entry) {
        EntryDTO e = entryService.addEntry(entry);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<EntryDTO> updateEntry(@RequestBody @Valid EntryDTO entry, @PathVariable("id") int entryId) {
        EntryDTO e = entryService.updateEntry(entry, entryId);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteEntryById(@PathVariable("id") int entryId) {
        entryService.deleteEntriesById(entryId);
        return ResponseEntity.status(HttpStatus.OK).body("Entry deleted successfully");
    }

    @GetMapping(path = "/getEntry")
    public ResponseEntity<EntryDTO> getEntryById(@RequestParam("id") int id) {
        EntryDTO e = entryService.getEntryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @GetMapping(path = "/getAllEntries")
    public ResponseEntity<List<EntryDTO>> getAllEntries() {
        List<EntryDTO> entries = entryService.getAllEntries();
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @GetMapping(path = "/getEntriesInDay")
    public ResponseEntity<List<EntryDTO>> getAllEntriesInDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("userId") int userId) {
        List<EntryDTO> entries = entryService.getAllEntriesInDay(date, userId);
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @GetMapping(path = "/getEntriesBetween")
    public ResponseEntity<List<EntryDTO>> getAllEntriesBetween(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam("userId") int userId
    ) {
        List<EntryDTO> entries = entryService.getAllEntriesBetween(startDate, endDate, userId);
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @GetMapping(path = "/getEntriesFromMood")
    public ResponseEntity<List<EntryDTO>> getEntriesFromMood(@RequestParam("mood") String mood) {
        List<EntryDTO> entries = entryService.getAllEntriesOfMood(mood);
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }
}
