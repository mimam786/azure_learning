package com.learn.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.question.model.Question;
import com.learn.question.model.QuestionWrapper;
import com.learn.question.model.Response;
import com.learn.question.service.QuestionService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/allquestions")
	public List<Question> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/questions")
	public Page<Question> getQuestions(@RequestParam int page, @RequestParam int size) {
		return questionService.getQuestions(page, size);
	}

	@PostMapping("/questions")
	public List<QuestionWrapper> getQuestionByIds(@RequestBody List<Integer> ids) {
		return questionService.getQuestionByIds(ids);
	}

	@GetMapping("/category")
	public List<QuestionWrapper> getQuestionsByCategory(@PathParam("category") String category) {
		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("/add")
	public String addQuestions(@RequestBody Question question) {
		questionService.addQuestion(question);
		return "Success";
	}

	@GetMapping("/generate")
	public List<Integer> generateQuestionsForQuiz(@PathParam("category") String category,
			@PathParam("numQues") Integer numQues) {
		return questionService.getQuestionIdsByCategory(category, numQues);
	}
	
	@PostMapping("/score")
	public Integer getQuizScore(@RequestBody List<Response> responses) {
		return questionService.getQuizScore(responses);
	}
}
