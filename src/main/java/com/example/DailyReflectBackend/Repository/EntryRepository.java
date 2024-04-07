package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
    List<Entry> findAllBySavedDay(Date savedDay);
    List<Entry> findAllBySavedDayBetween(Date savedDayStart, Date savedDayEnd);
}
