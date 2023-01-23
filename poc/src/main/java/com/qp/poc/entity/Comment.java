package com.qp.poc.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
	@jakarta.persistence.Id
	private int id;
	private String by;
	private int kids[];
	private int parent;
	@NotBlank(message = "comment should contain comment text!")
	private String text;
	private Date time;
	private String type;
}
