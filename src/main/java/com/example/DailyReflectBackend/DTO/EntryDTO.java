package com.example.DailyReflectBackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryDTO {
    private int id;
    private String content;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date savedDay;

    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    private Date savedTime;

    private int moodId;

}
