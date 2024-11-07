package com.arshalif.questionplatform.service;

import com.arshalif.questionplatform.dto.QuestionRequest;
import com.arshalif.questionplatform.dto.QuestionResponse;
import com.arshalif.questionplatform.dto.VoteRequest;
import com.arshalif.questionplatform.dto.VoteResponse;
import com.arshalif.questionplatform.model.Answer;
import com.arshalif.questionplatform.model.Question;
import com.arshalif.questionplatform.model.Vote;
import com.arshalif.questionplatform.repository.AnswerRepository;
import com.arshalif.questionplatform.repository.QuestionRepository;
import com.arshalif.questionplatform.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private VoteRepository voteRepository;

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

    @Transactional
    public VoteResponse vote(VoteRequest request) {
        Vote vote = new Vote();
        vote.setQuestion(questionRepository.getOne(request.getQuestionId()));
        vote.setAnswer(request.getAnswer());
        voteRepository.save(vote);

        List<Vote> votes = voteRepository.findByQuestionId(request.getQuestionId());

        Map<String, Integer> votesPerAnswer = new HashMap<>();
        for (Vote v : votes) {
            votesPerAnswer.put(v.getAnswer(), votesPerAnswer.getOrDefault(v.getAnswer(), 0) + 1);
        }

        Question question = questionRepository.getOne(request.getQuestionId());
        Boolean isCorrect = "Trivia".equals(question.getType()) ? request.getAnswer().equals(question.getCorrectAnswer()) : null;

        VoteResponse response = new VoteResponse();
        response.setVotes(votesPerAnswer);
        response.setCorrect(isCorrect);

        return response;
    }


}
