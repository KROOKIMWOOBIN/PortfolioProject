package org.project.service.board;

import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;
import org.project.dto.BoardViewDto;
import org.project.entity.Board;

import java.util.List;

public interface BoardService {

    void createBoard(BoardNewDto boardNewDto, String username);

    void updateDto(Long boardId, BoardUpdateDto boardUpdateDto);

    BoardViewDto viewBoard(Long id);

    List<BoardViewDto> viewBoardsByCreatedAtAsc();

    List<BoardViewDto> viewBoardsByCreatedAtDesc();

    List<BoardViewDto> findByTitleBoard(String search);

    void softDeleteBoard(Long id);

    void hardDeleteBoard(Long id);

    List<BoardViewDto> deleteBoardList();

}
