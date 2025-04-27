package com.example.demo.authUsers.auth.repository;

import com.example.demo.authUsers.auth.model.EntityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends CrudRepository<EntityUser, Long> {
}
