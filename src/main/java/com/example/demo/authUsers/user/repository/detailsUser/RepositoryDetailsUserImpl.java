package com.example.demo.authUsers.user.repository.detailsUser;

import com.example.demo.authUsers.user.model.EntityUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryDetailsUserImpl implements  RepositoryDetailsUser{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public EntityUser UserName(String name) {
        try{
            String query = "select u from EntityUser u where u.nameUser = :name";
            return entityManager.createQuery(query, EntityUser.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch (NoResultException e){
                throw new UsernameNotFoundException("in the repository details user not found the name, error: "+e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("in the repository details user error: "+e.getMessage());
        }

    }
}
