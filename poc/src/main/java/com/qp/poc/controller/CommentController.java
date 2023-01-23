package com.qp.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qp.poc.entity.Comment;
import com.qp.poc.repo.CommentRepository;

@RestController
public class CommentController {
	
	@Autowired
	CommentRepository commentRepository; 
	//https://hacker-news.firebaseio.com/v0/item/2921983.json?print=pretty
	
	@PostMapping("/addComment/{id}")
	public Comment addComments(@PathVariable(value = "id") int id)
	{
		System.out.println("nchfjhj");
		String url="https://hacker-news.firebaseio.com/v0/item/"+id+".json?print=pretty";
		RestTemplate restTemplate=new RestTemplate();
		Comment comment=restTemplate.getForObject(url, Comment.class);
		commentRepository.save(comment);
		return comment;
	}
	
	@PostMapping("/postComment")
	public Comment createComment(@RequestBody Comment comment)
	{
		return commentRepository.save(comment);
	}
	
	
	@GetMapping("/parent/{id}")
	public List<Comment> getComments(@PathVariable(value = "id") int id)
	{
		
		
		return commentRepository.findByParent(id);
	}
}
