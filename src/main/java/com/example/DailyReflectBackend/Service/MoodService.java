package com.example.DailyReflectBackend.Service;

import com.example.DailyReflectBackend.DTO.MoodDTO;
import com.example.DailyReflectBackend.Exceptions.MoodAlreadyExistsException;
import com.example.DailyReflectBackend.Exceptions.NoSuchMoodException;
import com.example.DailyReflectBackend.Model.Mood;
import com.example.DailyReflectBackend.Repository.MoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoodService {
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

    public MoodDTO getMoodById(int moodId) {
        Optional<Mood> moodOptional = moodRepository.findById(moodId);

        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        return this.modelMapper.map(moodOptional.get(), MoodDTO.class);
    }

    public Mood getMoodObjectById(int moodId) {
        Optional<Mood> moodOptional = moodRepository.findById(moodId);

        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        return moodOptional.get();
    }

    public MoodDTO getMoodByMoodString(String mood) {
        Optional<Mood> moodOptional = moodRepository.findByMood(mood);

        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No such mood: " + mood + " exists");
        }

        return this.modelMapper.map(moodOptional.get(), MoodDTO.class);
    }

    public MoodDTO addMood(MoodDTO mood) {
        Mood m = this.modelMapper.map(mood, Mood.class);
        Optional<Mood> moodOptional = moodRepository.findByMood(m.getMood());

        if(moodOptional.isPresent()) {
            throw new MoodAlreadyExistsException("Mood " + mood.getMood() + " already exists");
        }

        Mood moodAfterSave = moodRepository.save(m);
        return this.modelMapper.map(moodAfterSave, MoodDTO.class);
    }

    public List<MoodDTO> getAllMood() {
        List<Mood> moodList = moodRepository.findAll();
        return moodList.stream().map(m -> this.modelMapper.map(m, MoodDTO.class)).toList();
    }

    public void deleteMood(int moodId) {
        Optional<Mood> moodOptional = moodRepository.findById(moodId);

        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        moodRepository.deleteById(moodId);
    }

    public MoodDTO updateMood(MoodDTO mood, int moodId) {
        Mood m = this.modelMapper.map(mood, Mood.class);
        Optional<Mood> moodOptional = moodRepository.findById(moodId);
        if(moodOptional.isEmpty()) {
            throw new NoSuchMoodException("No mood with id " + moodId + " exists");
        }

        mood.setId(moodId);

        Mood moodAfterSave = moodRepository.save(m);

        return this.modelMapper.map(moodAfterSave, MoodDTO.class);
    }
}
