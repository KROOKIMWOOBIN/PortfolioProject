package org.project.service.board;

import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;
import org.project.dto.BoardViewDto;
import org.project.entity.Board;

public interface BoardService {

    void createBoard(BoardNewDto boardNewDto, String username);

    void updateDto(Long boardId, BoardUpdateDto boardUpdateDto);

    BoardViewDto viewBoard(Long id);

}
