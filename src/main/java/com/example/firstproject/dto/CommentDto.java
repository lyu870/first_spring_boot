package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), // 댓글 엔티티의 id.
                comment.getArticle().getId(), // 댓글 엔티티가 속한 부모게시글의 id.
                comment.getNickname(), // 댓글 엔티티의 nickname 변수.
                comment.getBody() // 댓글 엔티티의 body.
        );
    }
}