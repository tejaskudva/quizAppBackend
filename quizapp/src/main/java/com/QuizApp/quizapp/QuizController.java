package com.QuizApp.quizapp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam("title") String title, @RequestParam("category") String category, @RequestParam("noOfQuestions") Integer number) {
		
		quizService.createQuiz(category, number, title);
		
		return new ResponseEntity<>("hello, a quiz has been created on " + category + " with number of questions: " + number, HttpStatus.CREATED);
	}
	
	@GetMapping("allQuiz")
	public ResponseEntity<List<Quiz>> getAllQuiz(){
		
		return new ResponseEntity<>(quizService.getAllQuiz(), HttpStatus.OK);
	}
	
	@GetMapping("getQuizQuestions/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
		
		return new ResponseEntity<>(quizService.getQuiz(id), HttpStatus.OK);
	}
	
	@PostMapping("/getScore/{id}")
	public ResponseEntity<String> getScore(@PathVariable Integer id, @RequestBody List<QuizResponse> responses){
		
		return new ResponseEntity<>(quizService.getScore(id, responses) + "%", HttpStatus.OK);
	}

}
