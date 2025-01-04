package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 컨트롤러를 통해 클라이언트의 요청받기, 응답전달해주기를 수행. 실질적기능은 서비스에서 작동.
@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // GET
    // 게시글 리스트 조회
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index(); // 서비스에서 repository에 명령.
    }

    // 게시글 단일 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) { // → 게시글 단일 조회
        return articleService.show(id); // 서비스에서 repository에 명령.
    }

    // POST
    // 게시글 생성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto); // 서비스에서 repository에 명령.
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :    // → 정상작동을 클라이언트에게 알림
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // → 문제발생을 클라이언트에게 알림
    }

    // PATCH
    // 게시글 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto); // 서비스에서 repository에 명령.
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :    // → 정상작동을 클라이언트에게 알림
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // → 문제발생을 클라이언트에게 알림
    }


    // DELETE
    // 게시글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id); // 서비스에서 repository에 명령.
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :  // → 정상작동을 클라이언트에게 알림
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // → 문제발생을 클라이언트에게 알림
    }

    // 트랜잭션 연습 (대량 게시글 생성)
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) { // → 게시글 수정
//        // 1. DTO -> 엔티티
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2. 타겟 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리
//        if (target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // → 문제발생을 클라이언트에게 알림
//        }
//
//        // 4. 업데이트 및 정상 응답(200)
//        target.patch(article); // → article엔티티의 데이터를 target에 업데이트(패치)
//        Article updated = articleRepository.save(target); // → target을 리턴할 데이터에 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // → 잘 저장됨을 클라이언트에게 알림
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) { // → 게시글 삭제
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // → 문제발생을 클라이언트에게 알림
//        }
//        // 대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build(); // → 잘 동작함을 클라이언트에게 알림
//    }
}
