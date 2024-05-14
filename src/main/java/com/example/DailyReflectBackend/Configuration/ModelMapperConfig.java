package com.example.DailyReflectBackend.Configuration;

import com.example.DailyReflectBackend.DTO.EntryDTO;
import com.example.DailyReflectBackend.DTO.MoodDTO;
import com.example.DailyReflectBackend.DTO.UserDTO;
import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Model.Mood;
import com.example.DailyReflectBackend.Model.User;
import com.example.DailyReflectBackend.Service.EntryService;
import com.example.DailyReflectBackend.Service.MoodService;
import com.example.DailyReflectBackend.Service.UserService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private final EntryService _entryService = new EntryService();
    private final MoodService _moodService = new MoodService();
    private final UserService _userService = new UserService();

    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Entry, EntryDTO> entryDTOConverter = new Converter<Entry, EntryDTO>() {
            @Override
            public EntryDTO convert(MappingContext<Entry, EntryDTO> context) {
                Entry entry = context.getSource();
                EntryDTO entryDTO = context.getDestination();
                entryDTO.setId(entry.getId());
                entryDTO.setContent(entry.getContent());
                entryDTO.setSavedDay(entry.getSavedDay());
                entryDTO.setSavedTime(entry.getSavedTime());
                entryDTO.setMoodId(entry.getMood().getId());
                entryDTO.setUserId(entry.getUser().getId());

                return entryDTO;
            }
        };

        Converter<Mood, MoodDTO> moodDTOConverter = new Converter<Mood, MoodDTO>() {
            @Override
            public MoodDTO convert(MappingContext<Mood, MoodDTO> context) {
                Mood mood = context.getSource();
                MoodDTO moodDTO = context.getDestination();
                moodDTO.setId(mood.getId());
                moodDTO.setMood(mood.getMood());

                return moodDTO;
            }
        };

        Converter<User, UserDTO> userDTOConverter = new Converter<User, UserDTO>() {
            @Override
            public UserDTO convert(MappingContext<User, UserDTO> context) {
                User user = context.getSource();
                UserDTO userDTO = context.getDestination();

                userDTO.setId(user.getId());
                userDTO.setName(user.getName());
                userDTO.setBackgroundId(user.getBackgroundId());
                userDTO.setWelcomeSongId(user.getWelcomeSongId());

                return userDTO;
            }
        };

        Converter<EntryDTO, Entry> entryConverter = new Converter<EntryDTO, Entry>() {
            @Override
            public Entry convert(MappingContext<EntryDTO, Entry> context) {
                EntryDTO entryDTO = context.getSource();
                Entry entry = context.getDestination();
                entry.setId(entryDTO.getId());
                entry.setContent(entryDTO.getContent());
                entry.setSavedDay(entryDTO.getSavedDay());
                entry.setSavedTime(entryDTO.getSavedTime());
                entry.setMood(_moodService.getMoodObjectById(entryDTO.getMoodId()));
                entry.setUser(_userService.getUserById(entryDTO.getUserId()));
                return entry;
            }
        };

        Converter<MoodDTO, Mood> moodConverter = new Converter<MoodDTO, Mood>() {
            @Override
            public Mood convert(MappingContext<MoodDTO, Mood> context) {
                MoodDTO moodDTO = context.getSource();
                Mood mood = context.getDestination();
                mood.setId(moodDTO.getId());
                mood.setMood(moodDTO.getMood());

                return mood;
            }
        };

        return modelMapper;
    }
}
