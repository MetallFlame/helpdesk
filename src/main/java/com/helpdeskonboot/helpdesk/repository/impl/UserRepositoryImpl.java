package com.helpdeskonboot.helpdesk.repository.impl;


import com.helpdeskonboot.helpdesk.model.User;
import com.helpdeskonboot.helpdesk.model.UserRole;
import com.helpdeskonboot.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> getById(Long userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            return Optional.of(entityManager.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByEmailAndPassword(String email, String password) {
        try {
            return Optional.of(entityManager
                    .createQuery("from User where email = :email and password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }

    }

    @Override
    public List<User> getAllByRole(UserRole userRole) {
        return entityManager.createQuery("from User where role = :role", User.class)
                .setParameter("role", userRole)
                .getResultList();
    }


}
