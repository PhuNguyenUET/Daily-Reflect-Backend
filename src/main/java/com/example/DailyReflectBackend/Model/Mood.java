package com.example.DailyReflectBackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "moods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String mood;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Entry> entries = new ArrayList<>();
}
