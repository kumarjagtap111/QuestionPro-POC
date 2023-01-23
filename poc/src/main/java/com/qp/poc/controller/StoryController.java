package com.qp.poc.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qp.poc.entity.Story;
import com.qp.poc.repo.StoryRepository;

@RestController
public class StoryController {

	@Autowired
	StoryRepository storyRepository;

	List<Story> stories = new ArrayList<>(storiesInLast15Min());

	@PostMapping("/addStory/{id}")
	public Story addStorys(@PathVariable(value = "id") int id) {

		String url = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json?print=pretty";
		RestTemplate restTemplate = new RestTemplate();
		Story Story = restTemplate.getForObject(url, Story.class);
		storyRepository.save(Story);
		return Story;
	}

	@PostMapping("/postStory")
	public Story createStory(@RequestBody Story Story) {
		return storyRepository.save(Story);
	}

	@GetMapping("/score/{title}")
	public List<Story> getComments(@PathVariable(value = "title") String title) {
		List<Story> list = storyRepository.findByTitle(title);
		List<Story> stories = list.stream().sorted((o1, o2) -> o2.getScore() - o1.getScore()).toList();
		return stories;
		
	}

	private String getDate(LocalDateTime date) {
		String[] datetimeLocal = date.toString().split("T");
		String dateLast15 = datetimeLocal[0];
		String time15 = datetimeLocal[1];
		String[] time1 = time15.split(":");
		String hrs = time1[0];
		String min = time1[1];
		String sec = time1[2];
		String sec0 = sec.substring(0, 2);
		String date15min = dateLast15 + " " + hrs + ":" + min + ":" + sec0;
		return date15min;
	}

	@GetMapping("/top-stories")
	public List<Story> storiesInLast15Min() {
		return storyRepository.findByTimeBetween(getDate(LocalDateTime.now().minusMinutes(15)),
				getDate(LocalDateTime.now()));
	}
	 
	@GetMapping("/past-stories")
	public List<Story> pastStories()
	{
		return stories;
	}
}
