package com.example.DailyReflectBackend.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


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

    @Temporal(TemporalType.DATE)
    private Date savedDay;

    @Temporal(TemporalType.TIME)
    private Date savedTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "moodId", referencedColumnName = "id")
    private Mood mood;
}
