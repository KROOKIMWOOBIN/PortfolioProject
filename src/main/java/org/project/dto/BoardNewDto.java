package org.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardNewDto {

    @NotBlank(message = "제목은 필수 입력입니다.")
    @Pattern(regexp = "^.{2,200}$", message = "제목은 최소 2글자 이상 및 200자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다.")
    @Pattern(regexp = "^.{1,1000}$", message = "내용은 1000자 이하여야 합니다.")
    private String content;

}
