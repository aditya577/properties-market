package com.user.user.repository;

import com.user.user.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Page<Users> findAll(Pageable pageable);
    Optional<Users> findById(Integer id);
}
