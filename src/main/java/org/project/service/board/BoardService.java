package org.project.service.board;

import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;

public interface BoardService {

    void createBoard(BoardNewDto boardNewDto, String username);

    void updateDto(Long boardId, BoardUpdateDto boardUpdateDto);

}
