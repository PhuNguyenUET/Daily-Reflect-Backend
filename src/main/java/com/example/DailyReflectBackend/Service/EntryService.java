package com.example.DailyReflectBackend.Service;

import com.example.DailyReflectBackend.Exceptions.EntryAlreadyExistsException;
import com.example.DailyReflectBackend.Exceptions.NoSuchEntryException;
import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Repository.EntryRepository;
import com.example.DailyReflectBackend.Repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService {
    private EntryRepository entryRepository;
    private MoodRepository moodRepository;

    @Autowired
    public void setMoodRepository(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Autowired
    public void setEntryRepository(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry getEntryById(int entryId) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);
        if (entryOptional.isEmpty()) {
            throw new NoSuchEntryException("No such entry exists with id" + entryId);
        }
        return entryOptional.get();
    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    public List<Entry> getAllEntriesInDay(Date day) {
        return entryRepository.findAllBySavedDay(day);
    }

    public List<Entry> getAllEntriesBetween(Date startDate, Date endDate) {
        return entryRepository.findAllBySavedDayBetween(startDate, endDate);
    }

    public void deleteEntriesById(int entryId) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);
        if(entryOptional.isEmpty()) {
            return;
        }
        entryRepository.deleteById(entryId);
    }

    public Entry addEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public Entry updateEntry(Entry entry, int entryId) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);

        if(entryOptional.isEmpty()) {
            throw new NoSuchEntryException("No such entry exists with id" + entryId);
        }

        entry.setId(entryId);

        return entryRepository.save(entry);
    }
}
