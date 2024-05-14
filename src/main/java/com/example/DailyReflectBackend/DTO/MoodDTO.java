package com.example.DailyReflectBackend.DTO;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodDTO {
    private int id;
    private String mood;
}
