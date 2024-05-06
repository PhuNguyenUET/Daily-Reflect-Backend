package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
    List<Entry> findAllBySavedDayAndUser(Date savedDay, User user);
    List<Entry> findAllByUserAndSavedDayBetween(User user, Date savedDayStart, Date savedDayEnd);
    List<Entry> findALlByUser(User user);
}
