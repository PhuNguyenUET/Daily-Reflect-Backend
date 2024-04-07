package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoodRepository extends JpaRepository<Mood, Integer> {
    Optional<Mood> findByMood(String mood);
}
