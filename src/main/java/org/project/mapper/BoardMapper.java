package org.project.mapper;

import org.project.dto.BoardNewDto;
import org.project.dto.BoardViewDto;
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
                .deleted(false)
                .build();
    }

    public BoardViewDto toViewDto(Board board) {

        LocalDateTime deadline = board.getCreate_at().plusDays(10);

        return BoardViewDto.builder()
                .id(board.getId())
                .email(board.getMember().getEmail())
                .title(board.getTitle())
                .content(board.getContent())
                .create_at(board.getCreate_at())
                .deadline(deadline)
                .build();
    }

}
