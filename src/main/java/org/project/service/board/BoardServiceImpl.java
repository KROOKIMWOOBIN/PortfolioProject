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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public BoardViewDto viewBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND_EXCEPTION));

        return boardMapper.toViewDto(board);
    }

    @Override
    @Transactional
    public List<BoardViewDto> viewBoardsByCreatedAtAsc() {
        List<Board> boardList = boardRepository.findByDeletedFalse();
        boardList.sort(Comparator.comparing(Board::getCreate_at).reversed());

        List<BoardViewDto> boardViewDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardViewDtoList.add(boardMapper.toViewDto(board));
        }

        return boardViewDtoList;
    }

    @Override
    @Transactional
    public List<BoardViewDto> viewBoardsByCreatedAtDesc() {
        List<Board> boardList = boardRepository.findByDeletedFalse();
        boardList.sort(Comparator.comparing(Board::getCreate_at));

        List<BoardViewDto> boardViewDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardViewDtoList.add(boardMapper.toViewDto(board));
        }

        return boardViewDtoList;
    }

    @Override
    @Transactional
    public List<BoardViewDto> findByTitleBoard(String search) {
        List<Board> boardList = boardRepository.findByTitleContainingAndDeletedFalse(search);

        if (boardList.isEmpty()) {
            boardList = boardRepository.findByDeletedFalse();
            boardList.sort(Comparator.comparing(Board::getCreate_at));
        }

        List<BoardViewDto> boardViewDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardViewDtoList.add(boardMapper.toViewDto(board));
        }

        return boardViewDtoList;
    }

    @Override
    @Transactional
    public void softDeleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND_EXCEPTION));
        board.softDelete();

        boardRepository.save(board);
    }

    @Override
    @Transactional
    public void hardDeleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND_EXCEPTION));
        boardRepository.delete(board);
    }

    @Override
    @Transactional
    public List<BoardViewDto> deleteBoardList() {
        List<Board> boardList = boardRepository.findByDeletedTrue();

        List<BoardViewDto> boardViewDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardViewDtoList.add(boardMapper.toViewDto(board));
        }

        return boardViewDtoList;
    }


}
