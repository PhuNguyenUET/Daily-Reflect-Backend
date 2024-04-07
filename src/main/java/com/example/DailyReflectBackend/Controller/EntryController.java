package com.example.DailyReflectBackend.Controller;

import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Service.EntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Entry> insertEntry(@RequestBody @Valid Entry entry) {
        Entry e = entryService.addEntry(entry);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Entry> updateEntry(@RequestBody @Valid Entry entry, @PathVariable("id") int entryId) {
        Entry e = entryService.updateEntry(entry, entryId);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteEntryById(@PathVariable("id") int entryId) {
        entryService.deleteEntriesById(entryId);
        return ResponseEntity.status(HttpStatus.OK).body("Entry deleted successfully");
    }

    @GetMapping(path = "/getEntry")
    public ResponseEntity<Entry> getEntryById(@RequestParam("id") int id) {
        Entry e = entryService.getEntryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }

    @GetMapping(path = "/getAllEntries")
    public ResponseEntity<List<Entry>> getAllEntries() {
        List<Entry> entries = entryService.getAllEntries();
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @GetMapping(path = "/getEntriesInDay")
    public ResponseEntity<List<Entry>> getAllEntriesInDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Entry> entries = entryService.getAllEntriesInDay(date);
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @GetMapping(path = "/getEntriesBetween")
    public ResponseEntity<List<Entry>> getAllEntriesBetween(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Entry> entries = entryService.getAllEntriesBetween(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }


}
