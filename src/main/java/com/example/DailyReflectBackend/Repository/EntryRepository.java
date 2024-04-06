package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
}
