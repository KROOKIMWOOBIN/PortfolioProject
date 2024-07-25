package org.project.service;

import lombok.RequiredArgsConstructor;
import org.project.dto.BoardNewDto;
import org.project.entity.Board;
import org.project.entity.Member;
import org.project.exception.CustomException;
import org.project.exception.ErrorCode;
import org.project.mapper.BoardMapper;
import org.project.repository.BoardRepository;
import org.project.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    @Override
    public void createBoard(BoardNewDto boardNewDto, String username) {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION));
        Board board = boardMapper.toEntity(boardNewDto, member);
        boardRepository.save(board);
    }
}
