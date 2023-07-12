package com.QuizApp.quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public void createQuiz(String category, Integer number, String title) {
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		
		quiz.setQuestions(questionDao.findRandomQuestionsByCategory(category, number));
		
		quizDao.save(quiz);
	}

	public List<Quiz> getAllQuiz() {
		
		return quizDao.findAll();
		
	}
	
	public List<QuestionWrapper> getQuiz(Integer id) {
		
		Optional<Quiz> quiz = quizDao.findById(id);
		
		List<Question> questionsFromQuiz = quiz.get().getQuestions();
		List<QuestionWrapper> questionWrapper = new ArrayList<QuestionWrapper>();
		
		for(Question q : questionsFromQuiz) {
			QuestionWrapper quesWrapper = new QuestionWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestion_title());
			
			questionWrapper.add(quesWrapper);			
		}
		
		return questionWrapper;
	}

	public double getScore(Integer id, List<QuizResponse> responses) {
		int sum=0;
		
		
		Optional<Quiz> quiz = quizDao.findById(id);
		double totalQuestions = quiz.get().getQuestions().size();
		
		for(QuizResponse response : responses) {
			
			for(Question question : quiz.get().getQuestions()) {
				if(response.getId() == question.getId()) {
					if(question.getRight_answer().equals(response.getResponse())) {
						sum+=1;
					}
				}
			}
			
		}
		
		return sum/totalQuestions*100;
	}

}
