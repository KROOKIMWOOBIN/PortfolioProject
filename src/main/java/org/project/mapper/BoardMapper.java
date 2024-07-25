package org.project.mapper;

import org.project.dto.BoardNewDto;
import org.project.entity.Board;
import org.project.entity.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BoardMapper {

    public Board toEntity(BoardNewDto boardNewDto, Member member) {
        return Board.builder()
                .member(member)
                .title(boardNewDto.getTitle())
                .content(boardNewDto.getContent())
                .create_at(LocalDateTime.now())
                .update_at(LocalDateTime.now())
                .build();
    }

}
