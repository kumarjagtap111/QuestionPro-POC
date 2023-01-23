package com.qp.poc.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Story {
	
	 //title, URL, score, time of submission, and the user who submitted
	 @Id
	 private int id;
	 private String by;
	 private int descendants;
	 private int kids[];
	 @NotNull
	 private int score;
	
	 @DateTimeFormat(iso = ISO.DATE_TIME)
	 private LocalDateTime time;
	 @NotBlank
	 private String title;
	 private String type;
	 @NotBlank
	 private String url;
	
	@PrePersist
	public void onCreate()
	{
		this.time=LocalDateTime.now();
	}
}
