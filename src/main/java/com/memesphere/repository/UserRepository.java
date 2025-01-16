package com.memesphere.repository;

import com.memesphere.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findBySocialId(Long socialId);
    Optional<User> findByAccessToken(String accessToken);
    Optional<User> findById(String id);
}
