package com.example.demo.authUsers.repository;

import com.example.demo.authUsers.model.EntityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends CrudRepository<EntityUser, Long> {
}
