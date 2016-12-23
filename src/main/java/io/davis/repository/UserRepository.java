package io.davis.repository;

import io.davis.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Davis on 16/12/23.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
