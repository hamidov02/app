package com.eTaskifyAPI.etaskify.service;

import com.eTaskifyAPI.etaskify.model.Comment;
import com.eTaskifyAPI.etaskify.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentBelongsTask(Long taskId) {
        return commentRepository.findCommentByTaskId(taskId);
    }
    public Comment updateComment(Long id, Comment newComment) {
        Comment existComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment Not Found"));

        existComment.setText(newComment.getText());

        return commentRepository.save(existComment);
    }
    public void deleteComent(Long id) {
        commentRepository.deleteById(id);
    }
}
