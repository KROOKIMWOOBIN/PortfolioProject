package org.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    MEMBER_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "M001", "이미 사용중인 이메일입니다."),
    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "M002", "해당하는 사용자를 찾을 수 없습니다."),
    INVALID_LOGIN_CREDENTIALS(HttpStatus.BAD_REQUEST, "L001", "로그인 자격 증명이 잘못되었습니다"),
    BOARD_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "B001", "해당하는 게시글을 찾을 수 없습니다."),
    BOARD_NOT_UPDATE_EXCEPTION(HttpStatus.BAD_REQUEST, "B002", "게시판을 작성한 지 10일이 지나 업데이트가 불가능합니다.");

    private final HttpStatus status;

    private final String code;

    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
