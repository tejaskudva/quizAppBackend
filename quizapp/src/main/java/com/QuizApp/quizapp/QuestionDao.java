package com.QuizApp.quizapp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	
	List<Question> findByCategory(String category);
	
	@Modifying
	@Transactional
	@Query("update Question q set q.question_title = ?2 where q.id = ?1")
	void updateQuestionTitle(Integer id, String questionTitle);
	
	@Query(value = "select * from Question q where q.category=:category ORDER BY RANDOM() LIMIT :number", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, Integer number);

	@Query(value = "select q.right_answer from Question q where q.id=:id", nativeQuery = true)
	String getScore(Integer id);
	
}
