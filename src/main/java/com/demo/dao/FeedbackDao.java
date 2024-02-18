package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.models.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback,Integer> {

}