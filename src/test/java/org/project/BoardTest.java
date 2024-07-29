package org.project;

import org.junit.jupiter.api.Test;
import org.project.entity.Board;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTest {

    @Test
    public void 게시글생성() {
        Board board = new Board();
    }

}
