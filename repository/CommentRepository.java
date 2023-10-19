package com.eTaskifyAPI.etaskify.repository;

import com.eTaskifyAPI.etaskify.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentByTaskId(Long id);
}
