package com.oljalatinovic.oljaee.service;

import com.oljalatinovic.oljaee.entity.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Stateless
public class UserService implements Serializable {
    
    @Inject
    private EntityManager em;
    
    public User createUser(@NotNull User user) {
        em.persist(user);
        return user;
    }

    public User findUser(@NotNull String username) {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        typedQuery.setParameter("username", username);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findUser(@NotNull String username, @NotNull String password) {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.FIND_BY_USERNAME_PASSWORD, User.class);
        typedQuery.setParameter("username", username);
        typedQuery.setParameter("password", password);

        return typedQuery.getSingleResult();
    }

    public List<User> findAllUsers() {
        TypedQuery<User> typedQuery = em.createNamedQuery(User.FIND_ALL, User.class);
        return typedQuery.getResultList();
    }

    public User updateUser(@NotNull User user) {
        em.merge(user);
        return user;
    }

    public void removeUser(@NotNull User user) {
        em.remove(em.merge(user));
    }    
}
