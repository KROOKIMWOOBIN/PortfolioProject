package org.project;

import org.junit.jupiter.api.Test;
import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;
import org.project.dto.BoardViewDto;
import org.project.entity.Board;
import org.project.entity.Member;
import org.project.repository.BoardRepository;
import org.project.repository.MemberRepository;
import org.project.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    public void 게시글생성() {
        Member member = memberRepository.findById("loginTest@naver.com")
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
        BoardNewDto boardNewDto = new BoardNewDto("게시글 테스트", "게시글 테스트");
        boardService.createBoard(boardNewDto, member.getEmail());
    }

    @Test
    @Transactional
    public void 게시글수정() {
        BoardUpdateDto boardUpdateDto = new BoardUpdateDto("게시글 업데이트 테스트", "게시글 업데이트 테스트");
        boardService.updateDto(3L, boardUpdateDto);
    }

    @Test
    @Transactional
    public void 게시글보기() {
        System.out.println(boardService.viewBoard(3L));
    }

    @Test
    @Transactional
    public void 게시글Soft삭제() {
        boardService.softDeleteBoard(3L);
    }

    @Test
    @Transactional
    public void 게시글Hard삭제() {
        boardService.hardDeleteBoard(3L);
    }

    @Test
    @Transactional
    public void 삭제된게시글조회() {
        boardService.softDeleteBoard(3L);
    }

    @Test
    @Transactional
    public void 제목으로게시글검색() {
        boardService.findByTitleBoard("테");
    }

}
