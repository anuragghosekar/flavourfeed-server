package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.models.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {

}