package com.example.DailyReflectBackend.DTO;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private int backgroundId;
    private int welcomeSongId;
}
