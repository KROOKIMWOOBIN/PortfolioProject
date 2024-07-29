package org.project.service.board;

import lombok.RequiredArgsConstructor;
import org.project.entity.Board;
import org.project.repository.BoardRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyNotificationScheduler {

    private final BoardRepository boardRepository;

    @Scheduled(cron = "0, 0, 0 * * ?")
    public void checkAndSendNotifications() {

        LocalDateTime now = LocalDateTime.now();
        List<Board> boardList = boardRepository.findAll();

        for (Board board : boardList) {
            LocalDateTime nineDaysAfterCreateAt = board.getCreate_at().plusDays(9);
            if (now.isEqual(nineDaysAfterCreateAt) || now.isAfter(nineDaysAfterCreateAt)) {
                System.out.println("이제 이메일 및 메시지로 전송을 해야함");
            }
        }
    }

}
