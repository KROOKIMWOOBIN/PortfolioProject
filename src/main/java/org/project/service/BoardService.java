package org.project.service;

import org.project.dto.BoardNewDto;

public interface BoardService {

    void createBoard(BoardNewDto boardNewDto, String username);

}
