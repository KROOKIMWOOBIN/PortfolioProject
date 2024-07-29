package org.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardViewDto {

    private String title;

    private String content;

    private String email;

    private LocalDateTime create_at;

    private LocalDateTime deadline;

}
