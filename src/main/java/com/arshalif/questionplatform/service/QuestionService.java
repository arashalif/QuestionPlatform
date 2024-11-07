package com.arshalif.questionplatform.service;

import com.arshalif.questionplatform.dto.QuestionRequest;
import com.arshalif.questionplatform.model.Answer;
import com.arshalif.questionplatform.model.Question;
import com.arshalif.questionplatform.repository.AnswerRepository;
import com.arshalif.questionplatform.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
