package com.qp.poc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qp.poc.entity.Story;
@Repository
public interface StoryRepository extends JpaRepository<Story, Integer>{
	
	public List<Story> findByScore(int score);
	
	public List<Story> findByTitle(String title);	
	
	@Query(value="select * from story where time between cast((:startDate)as DATETIME) AND cast((:endDate) as DATETIME)"
			,nativeQuery = true)
	List<Story> findByTimeBetween(@Param(value ="startDate") String starDate,@Param(value ="endDate")  String endDate);
	
	

}
