package org.project;

import org.junit.jupiter.api.Test;
import org.project.config.SecurityConfig;
import org.project.controller.BoardController;
import org.project.service.board.BoardService;
import org.project.service.member.MyUserDetailsService;
import org.project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BoardController.class)
@Import(SecurityConfig.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2dpblRlc3RAbmF2ZXIuY29tIiwiaWF0IjoxNzIxOTA0NjE1LCJleHAiOjE3MjE5MTU0MTV9.cqb3_n5uGKovsMW1ReSy8Zg2TIe1F-pU9shZUWF9eQnBLPSyUDjS0OJpKnZJOd-R1uKO7DWZbqv3vsIPhE5jdg";

    @Test
    public void 게시글_성공() throws Exception {
        String jsonContent = "{\"title\":\"테스트용 첫번째 게시글\",\"content\":\"잘만들어지네요\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/board/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void 게시글_실패_제목() throws Exception {
        String jsonContent = "{\"title\":\"테\",\"content\":\"제목 실패입니다.\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/board/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("제목은 최소 2글자 이상 및 200자 이하여야 합니다.")));;
    }

    @Test
    public void 게시글_실패_제목공백() throws Exception {
        String jsonContent = "{\"title\":\"\",\"content\":\"제목 실패입니다.\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/board/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("제목은 필수 입력입니다.")));;
    }

    @Test
    public void 게시글_실패_공백() throws Exception {
        String jsonContent = "{\"title\":\"테스트용 첫번째 게시글\",\"content\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/board/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("내용은 필수 입력입니다.")));;
    }

}
