package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.Entry;
import com.example.DailyReflectBackend.Model.User;
import jakarta.persistence.TemporalType;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
    default List<Entry> findAllByUserAndDate(User user, LocalDate date) {
        return findAllByUserAndDateBetween(user, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    default List<Entry> findAllByUserAndDateBetween(User user, LocalDate dateStart, LocalDate dateEnd) {
        return findAllByUserAndDateBetween(user, dateStart.atStartOfDay(), dateEnd.plusDays(1).atStartOfDay());
    }

    List<Entry> findAllByUserAndDateBetween(User user, LocalDateTime dateStart, LocalDateTime dateEnd);

    List<Entry> findALlByUser(User user);
}
