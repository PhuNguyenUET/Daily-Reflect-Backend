package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.Mood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodRepository extends JpaRepository<Mood, Integer> {
}
