package com.workevr.api.repository;

import com.workevr.api.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Author rohit
 * @Date 24/09/21
 **/
@Repository
public interface ContentRepository extends JpaRepository<Content, UUID> {
}
