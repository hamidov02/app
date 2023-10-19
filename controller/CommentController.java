package com.eTaskifyAPI.etaskify.controller;

import com.eTaskifyAPI.etaskify.model.Comment;
import com.eTaskifyAPI.etaskify.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @GetMapping("/gorev/{gorevId}")
    public ResponseEntity<List<Comment>> getCommentBelongsTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(commentService.getCommentBelongsTask(taskId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComent(id);
        return ResponseEntity.noContent().build();
    }

}
