package com.example.DailyReflectBackend.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "Entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String content;

    private LocalDateTime date;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moodId", referencedColumnName = "id")
    private Mood mood;

    @NotNull
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}
