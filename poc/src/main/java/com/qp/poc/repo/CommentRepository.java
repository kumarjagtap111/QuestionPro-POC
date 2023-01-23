package com.qp.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qp.poc.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	public List<Comment> findByParent(int parent);
	

}
