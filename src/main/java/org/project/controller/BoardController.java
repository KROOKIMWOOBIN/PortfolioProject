package org.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;
import org.project.dto.BoardViewDto;
import org.project.entity.Board;
import org.project.service.board.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/new")
    public ResponseEntity<String> createBoard(@Valid @RequestBody BoardNewDto boardNewDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().toString());
        }
        boardService.createBoard(boardNewDto, getLoginUser());
        return ResponseEntity.ok("게시글 생성 성공");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") Long id, @Valid @RequestBody BoardUpdateDto boardUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().toString());
        }
        boardService.updateDto(id, boardUpdateDto);
        return ResponseEntity.ok("게시글 수정 성공");
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<BoardViewDto> getBoard(@PathVariable("id") Long id) {
        return ResponseEntity.ok(boardService.viewBoard(id));
    }

    @GetMapping("/view/createAt/up")
    public ResponseEntity<List<BoardViewDto>> getBoardListByCreateAtDesc() {
        return ResponseEntity.ok(boardService.viewBoardsByCreatedAtDesc());
    }

    @GetMapping("/view/createAt/down")
    public ResponseEntity<List<BoardViewDto>> getBoardListByCreateAtAsc() {
        return ResponseEntity.ok(boardService.viewBoardsByCreatedAtAsc());
    }

    @GetMapping("/view/search")
    public ResponseEntity<List<BoardViewDto>> getBoardSearchList(@RequestParam(value = "search",required = false) String search) {
        return ResponseEntity.ok(boardService.findByTitleBoard(search));
    }

    @DeleteMapping("/delete/soft/{id}")
    public ResponseEntity<String> softDeleteBoard(@PathVariable("id") Long id) {
        boardService.softDeleteBoard(id);

        return ResponseEntity.ok("삭제 성공");
    }

    @DeleteMapping("/delete/hard/{id}")
    public ResponseEntity<String> hardDeleteBoard(@PathVariable("id") Long id) {
        boardService.hardDeleteBoard(id);

        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping("/view/delete")
    public ResponseEntity<List<BoardViewDto>> getDeleteBoardList() {
        return ResponseEntity.ok(boardService.deleteBoardList());
    }

    private String getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
