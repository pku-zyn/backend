package com.zyn.ticketorder.repository;

import com.zyn.ticketorder.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // JpaRepository 已经提供了 findAll() 方法
}
