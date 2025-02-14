package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Comment 엔티티를 관리하므로 대상엔티티값에 Comment, id값에 Long넣어줌. ↑
    // CRUD작업만한다면 CrudRepository 사용으로 충분.
    // CRUD작업 + 페이지처리와 정렬작업이 필요하다면 JpaRepository사용.
    @Query(value =
            "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true)
    // ↓ 특정 게시글의 모든 댓글 조회.
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // ↓ 특정 닉네임의 모든 댓글 조회.
    List<Comment> findByNickname(String nickname);
}
