package com.QuizApp.quizapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionClass {
	
	@Autowired
	QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionDao.findAll();
	}

	public List<Question> findByCategory(String category) {
		// TODO Auto-generated method stub
		return questionDao.findByCategory(category);
	}

	public void addQuestion(Question question) {
		
		questionDao.save(question);
		
	}

	public void updateQuestionTitle(Integer id, String questionTitle) {
		
		questionDao.updateQuestionTitle(id, questionTitle);
	}	

}
