package org.project.service.board;

import lombok.RequiredArgsConstructor;
import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;
import org.project.dto.BoardViewDto;
import org.project.entity.Board;
import org.project.entity.Member;
import org.project.exception.CustomException;
import org.project.exception.ErrorCode;
import org.project.mapper.BoardMapper;
import org.project.repository.BoardRepository;
import org.project.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    @Override
    @Transactional
    public void createBoard(BoardNewDto boardNewDto, String username) {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION));
        Board board = boardMapper.toEntity(boardNewDto, member);
        boardRepository.save(board);
    }

    @Override
    @Transactional
    public void updateDto(Long boardId, BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND_EXCEPTION));
        if (!LocalDateTime.now().isBefore(board.getCreate_at().plusDays(10))) {
            throw new CustomException(ErrorCode.BOARD_NOT_UPDATE_EXCEPTION);
        }

        board.updateTitle(boardUpdateDto.getTitle());
        board.updateContent(boardUpdateDto.getContent());
        board.updateTimeToNow();
        boardRepository.save(board);
    }

    @Override
    public BoardViewDto viewBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND_EXCEPTION));

        return boardMapper.toViewDto(board);
    }
}
