package io.davis.service;

import io.davis.entity.User;
import io.davis.model.UserDraft;
import io.davis.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Davis on 16/12/23.
 */
@Service
public class UserService {
  /**
   * The Repository.
   */
  @Autowired
  private UserRepository repository;

  /**
   * Create user.
   *
   * @param draft the draft
   * @return the user
   */
  public User createUser(UserDraft draft) {
    User user = new User();
    user.setName(draft.getName());
    user.setAge(draft.getAge());
    User savedUser = repository.save(user);
    return savedUser;
  }

  /**
   * Gets user.
   *
   * @param id the id
   * @return the user
   */
  public User getUser(String id) {
    return repository.findOne(id);
  }

  /**
   * Gets all users.
   *
   * @return the all users
   */
  public List<User> getAllUsers() {
    return repository.findAll();
  }
}
