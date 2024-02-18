package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.models.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, String> {

}