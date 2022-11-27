package com.ms.telegram.daos.repos;

import com.ms.telegram.daos.entities.Session;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Cacheable("sessions")
    Optional<Session> getByUserID(Long userID);
}
