package com.example.DailyReflectBackend.Service;

import com.example.DailyReflectBackend.DTO.EntryDTO;
import com.example.DailyReflectBackend.Exceptions.EntryAlreadyExistsException;
import com.example.DailyReflectBackend.Exceptions.NoSuchEntryException;
import com.example.DailyReflectBackend.Exceptions.NoSuchMoodException;
import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Model.Mood;
import com.example.DailyReflectBackend.Repository.EntryRepository;
import com.example.DailyReflectBackend.Repository.MoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService {
    private EntryRepository entryRepository;
    private MoodRepository moodRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setMoodRepository(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Autowired
    public void setEntryRepository(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public EntryDTO getEntryById(int entryId) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);
        if (entryOptional.isEmpty()) {
            throw new NoSuchEntryException("No such entry exists with id" + entryId);
        }
        return this.modelMapper.map(entryOptional.get(), EntryDTO.class);
    }

    public List<EntryDTO> getAllEntries() {
        List<Entry> entries = entryRepository.findAll();
        return entries.stream().map(e -> this.modelMapper.map(e, EntryDTO.class)).toList();
    }

    public List<EntryDTO> getAllEntriesInDay(Date day) {
        List<Entry> entries = entryRepository.findAllBySavedDay(day);
        return entries.stream().map(e -> this.modelMapper.map(e, EntryDTO.class)).toList();
    }

    public List<EntryDTO> getAllEntriesBetween(Date startDate, Date endDate) {
        List<Entry> entries = entryRepository.findAllBySavedDayBetween(startDate, endDate);
        return entries.stream().map(e -> this.modelMapper.map(e, EntryDTO.class)).toList();
    }

    public void deleteEntriesById(int entryId) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);
        if(entryOptional.isEmpty()) {
            return;
        }
        Mood mood = entryOptional.get().getMood();
        mood.getEntries().remove(entryOptional.get());
        moodRepository.save(mood);
        entryRepository.deleteById(entryId);
    }

    public EntryDTO addEntry(EntryDTO entry) {
        Entry entry1 = this.modelMapper.map(entry, Entry.class);
        Entry e = entryRepository.save(entry1);
        Mood mood = e.getMood();
        mood.getEntries().add(e);
        moodRepository.save(mood);
        return this.modelMapper.map(e, EntryDTO.class);
    }

    public EntryDTO updateEntry(EntryDTO entry, int entryId) {
        Entry entry1 = this.modelMapper.map(entry, Entry.class);
        Optional<Entry> entryOptional = entryRepository.findById(entryId);

        if(entryOptional.isEmpty()) {
            throw new NoSuchEntryException("No such entry exists with id" + entryId);
        }

        entry1.setId(entryId);
        Mood previousMood = entryOptional.get().getMood();
        Mood subsequentMood = entry1.getMood();

        previousMood.getEntries().remove(entryOptional.get());
        subsequentMood.getEntries().add(entry1);
        moodRepository.save(previousMood);
        moodRepository.save(subsequentMood);

        Entry e = entryRepository.save(entry1);
        return this.modelMapper.map(e, EntryDTO.class);
    }

    public List<EntryDTO> getAllEntriesOfMood(String mood){
        Optional<Mood> moodObject = moodRepository.findByMood(mood);

        if(moodObject.isEmpty()) {
            throw new NoSuchMoodException("No such mood: " + mood + " exists");
        }


        List<Entry> entries = moodObject.get().getEntries();

        return entries.stream().map(e -> this.modelMapper.map(e, EntryDTO.class)).toList();
    }
}
