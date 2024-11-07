package com.arshalif.questionplatform.service;

import com.arshalif.questionplatform.dto.QuestionRequest;
import com.arshalif.questionplatform.dto.QuestionResponse;
import com.arshalif.questionplatform.model.Answer;
import com.arshalif.questionplatform.model.Question;
import com.arshalif.questionplatform.repository.AnswerRepository;
import com.arshalif.questionplatform.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public Long insertQuestion(QuestionRequest request) {
        Question question = new Question();
        question.setText(request.getText());
        question.setType(request.getType());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question = questionRepository.save(question);

        for (String answerText : request.getAnswers()) {
            Answer answer = new Answer();
            answer.setAnswer(answerText);
            answer.setQuestion(question);
            answerRepository.save(answer);
        }

        return question.getId();
    }

    public QuestionResponse getQuestion(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) return null;


        List<String> answers = question.getAnswers().stream()
                .map(Answer::getAnswer).toList();

        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setText(question.getText());
        response.setType(question.getType());
        response.setAnswers(answers);

        return response;
    }


}
