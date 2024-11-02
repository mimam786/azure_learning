package com.learn.question.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learn.question.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

	@Query("Select q from Question q where q.category=:category")
	List<Question> getQuestionsByCategory(String category);

	@Query("Select q.id from Question q where q.category=:category order by RANDOM() Limit :numQues")
	List<Integer> getQuestionIdsByCategory(String category, Integer numQues);
	
	

}
