package com.arshalif.questionplatform.repository;

import com.arshalif.questionplatform.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long>{ }
