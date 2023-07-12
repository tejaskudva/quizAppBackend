package com.QuizApp.quizapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionClass questionService;
	
	@GetMapping("/allQuestions")
	public List<Question> getAllQuestions() {
		
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestions(@PathVariable String category){
		
		return new ResponseEntity<>(questionService.findByCategory(category), HttpStatus.OK);		
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		
		try {			
			questionService.addQuestion(question);
			return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
					
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to Added", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping("/updateQuestionTitle")
	public ResponseEntity<String> updateQuestionTitle(@RequestParam("id") Integer id, @RequestParam("questionTitle") String questionTitle) {
		
		try {
			questionService.updateQuestionTitle(id, questionTitle);
			return new ResponseEntity<>("Successfully Updated Question Title", HttpStatus.CREATED);
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to update Question Title", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
