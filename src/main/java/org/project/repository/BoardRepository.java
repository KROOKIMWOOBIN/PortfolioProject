package org.project.repository;

import org.project.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByDeletedFalse();

    List<Board> findByTitleContainingAndDeletedFalse(String title);

    List<Board> findByDeletedTrue();

}
