package com.learn.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learn.question.dao.QuestionDao;
import com.learn.question.model.Question;
import com.learn.question.model.QuestionWrapper;
import com.learn.question.model.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<QuestionWrapper> getQuestionByIds(List<Integer> ids) {
		List<QuestionWrapper> questionWrapList = new ArrayList<QuestionWrapper>();
		List<Question> questionList = new ArrayList<Question>();
		for (Integer id : ids) {
			Question question = questionDao.findById(id).get();
			questionList.add(question);
		}

		for (Question question : questionList) {
			QuestionWrapper wrap = new QuestionWrapper();
			wrap.setId(question.getId());
			wrap.setCategory(question.getCategory());
			wrap.setCategory(question.getQuestionTitle());
			wrap.setOption1(question.getOption1());
			wrap.setOption2(question.getOption2());
			wrap.setOption3(question.getOption3());
			wrap.setOption4(question.getOption4());

			questionWrapList.add(wrap);
		}
		return questionWrapList;
	}

	public List<QuestionWrapper> getQuestionsByCategory(String category) {
		List<QuestionWrapper> questionWrapList = new ArrayList<QuestionWrapper>();
		List<Question> questions = questionDao.getQuestionsByCategory(category);
		for (Question question : questions) {
			QuestionWrapper wrap = new QuestionWrapper();
			wrap.setId(question.getId());
			wrap.setCategory(question.getCategory());
			wrap.setCategory(question.getQuestionTitle());
			wrap.setOption1(question.getOption1());
			wrap.setOption2(question.getOption2());
			wrap.setOption3(question.getOption3());
			wrap.setOption4(question.getOption4());

			questionWrapList.add(wrap);
		}
		return questionWrapList;
	}

	public void addQuestion(Question question) {
		questionDao.save(question);
	}

	public List<Integer> getQuestionIdsByCategory(String category, Integer numQues) {
		return questionDao.getQuestionIdsByCategory(category, numQues);
	}

	public Integer getQuizScore(List<Response> responses) {
		int right = 0;
		for (Response response : responses) {
			if (response.getResponse().equals(questionDao.findById(response.getId()).get().getRightAnswer())) {
				right++;
			}
				
		}
		return right;
	}

	public Page<Question> getQuestions(int page, int size) {
		return questionDao.findAll(PageRequest.of(page, size));
	}

}
