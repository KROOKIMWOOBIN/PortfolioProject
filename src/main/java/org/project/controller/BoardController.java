package org.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.BoardNewDto;
import org.project.dto.BoardUpdateDto;
import org.project.service.board.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private String getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
