package com.arshalif.questionplatform.repository;

import com.arshalif.questionplatform.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote,Long>{
    List<Vote> findByQuestionId(Long questionId);
}
