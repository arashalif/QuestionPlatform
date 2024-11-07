package com.arshalif.questionplatform.dto;

import java.util.Map;

public class VoteResponse {
    private Map<String, Integer> votes;
    private Boolean correct;

    public Map<String, Integer> getVotes() {
        return votes;
    }

    public void setVotes(Map<String, Integer> votes) {
        this.votes = votes;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
