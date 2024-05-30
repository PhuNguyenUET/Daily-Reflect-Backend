package com.example.DailyReflectBackend.Configuration;

import com.example.DailyReflectBackend.DTO.EntryDTO;
import com.example.DailyReflectBackend.DTO.MoodDTO;
import com.example.DailyReflectBackend.DTO.UserDTO;
import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Model.Mood;
import com.example.DailyReflectBackend.Model.User;
import com.example.DailyReflectBackend.Repository.MoodRepository;
import com.example.DailyReflectBackend.Repository.UserRepository;
import com.example.DailyReflectBackend.Service.EntryService;
import com.example.DailyReflectBackend.Service.MoodService;
import com.example.DailyReflectBackend.Service.UserService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ModelMapperConfig {
    private final MoodRepository _moodRepository;
    private final UserRepository _userRepository;

    @Autowired
    public ModelMapperConfig(MoodRepository moodRepository, UserRepository userRepository) {
        _moodRepository = moodRepository;
        _userRepository = userRepository;
    }

    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Entry, EntryDTO> entryDTOConverter = new Converter<Entry, EntryDTO>() {
            @Override
            public EntryDTO convert(MappingContext<Entry, EntryDTO> context) {
                Entry entry = context.getSource();
                EntryDTO entryDTO = new EntryDTO();
                entryDTO.setId(entry.getId());
                entryDTO.setContent(entry.getContent());
                entryDTO.setSavedDay(entry.getDate().toLocalDate());
                entryDTO.setSavedTime(entry.getDate().toLocalTime());
                entryDTO.setMoodId(entry.getMood().getId());
                entryDTO.setUserId(entry.getUser().getId());

                return entryDTO;
            }
        };

        modelMapper.addConverter(entryDTOConverter);

        Converter<Mood, MoodDTO> moodDTOConverter = new Converter<Mood, MoodDTO>() {
            @Override
            public MoodDTO convert(MappingContext<Mood, MoodDTO> context) {
                Mood mood = context.getSource();
                MoodDTO moodDTO = new MoodDTO();
                moodDTO.setId(mood.getId());
                moodDTO.setMood(mood.getMood());

                return moodDTO;
            }
        };

        modelMapper.addConverter(moodDTOConverter);

        Converter<User, UserDTO> userDTOConverter = new Converter<User, UserDTO>() {
            @Override
            public UserDTO convert(MappingContext<User, UserDTO> context) {
                User user = context.getSource();
                UserDTO userDTO = new UserDTO();

                userDTO.setId(user.getId());
                userDTO.setName(user.getName());
                userDTO.setBackgroundId(user.getBackgroundId());
                userDTO.setWelcomeSongId(user.getWelcomeSongId());

                return userDTO;
            }
        };

        modelMapper.addConverter(userDTOConverter);

        Converter<EntryDTO, Entry> entryConverter = new Converter<EntryDTO, Entry>() {
            @Override
            public Entry convert(MappingContext<EntryDTO, Entry> context) {
                EntryDTO entryDTO = context.getSource();
                Entry entry = new Entry();
                entry.setId(entryDTO.getId());
                entry.setContent(entryDTO.getContent());
                entry.setDate(LocalDateTime.of(entryDTO.getSavedDay(), entryDTO.getSavedTime()));
                entry.setMood(_moodRepository.findById(entryDTO.getMoodId()).get());
                entry.setUser(_userRepository.findById(entryDTO.getUserId()).get());
                return entry;
            }
        };

        modelMapper.addConverter(entryConverter);

        Converter<MoodDTO, Mood> moodConverter = new Converter<MoodDTO, Mood>() {
            @Override
            public Mood convert(MappingContext<MoodDTO, Mood> context) {
                MoodDTO moodDTO = context.getSource();
                Mood mood = new Mood();
                mood.setId(moodDTO.getId());
                mood.setMood(moodDTO.getMood());

                return mood;
            }
        };

        modelMapper.addConverter(moodConverter);

        return modelMapper;
    }
}
