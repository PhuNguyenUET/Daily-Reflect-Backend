package com.example.DailyReflectBackend.Service;

import com.example.DailyReflectBackend.Exceptions.MoodAlreadyExistsException;
import com.example.DailyReflectBackend.Exceptions.NoSuchMoodException;
import com.example.DailyReflectBackend.Model.Mood;
import com.example.DailyReflectBackend.Repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoodService {
    private MoodRepository moodRepository;

    @Autowired
    public void setMoodRepository(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public Mood getMoodById(int moodId) {
        Optional<Mood> moodOptional = moodRepository.findById(moodId);

        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        return moodOptional.get();
    }

    public Mood addMood(Mood mood) {
        Optional<Mood> moodOptional = moodRepository.findByMood(mood.getMood());

        if(moodOptional.isPresent()) {
            throw new MoodAlreadyExistsException("Mood " + mood.getMood() + " already exists");
        }

        return moodRepository.save(mood);
    }

    public List<Mood> getAllMood() {
        return moodRepository.findAll();
    }

    public void deleteMood(int moodId) {
        Optional<Mood> moodOptional = moodRepository.findById(moodId);

        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        moodRepository.deleteById(moodId);
    }

    public Mood updateMood(Mood mood, int moodId) {
        Optional<Mood> moodOptional = moodRepository.findById(moodId);
        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        mood.setId(moodId);

        return moodRepository.save(mood);
    }
}
