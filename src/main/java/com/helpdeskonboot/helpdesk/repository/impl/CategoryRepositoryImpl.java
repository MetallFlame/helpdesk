package com.helpdeskonboot.helpdesk.repository.impl;



import com.helpdeskonboot.helpdesk.model.Category;
import com.helpdeskonboot.helpdesk.repository.CategoryRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Category> getAll() {
        return entityManager.createQuery("From Category", Category.class).getResultList();
    }

    @Override
    public Optional<Category> getByName(String categoryName) {
        try {
            return Optional.of(entityManager
                    .createQuery("from Category where name = :category_name", Category.class)
                    .setParameter("category_name", categoryName)
                    .getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
