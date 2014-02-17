package com.oljalatinovic.oljaee.service;

import com.oljalatinovic.oljaee.entity.Users;
import com.oljalatinovic.oljaee.util.Loggable;
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
@Loggable
public class UsersService implements Serializable {
    
    @Inject
    private EntityManager em;
    
    public Users createUser(@NotNull Users user) {
        em.persist(user);
        return user;
    }

    public Users findUser(@NotNull String username) {
        TypedQuery<Users> typedQuery = em.createNamedQuery(Users.FIND_BY_USERNAME, Users.class);
        typedQuery.setParameter("username", username);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Users findUser(@NotNull String username, @NotNull String password) {
        TypedQuery<Users> typedQuery = em.createNamedQuery(Users.FIND_BY_USERNAME_PASSWORD, Users.class);
        typedQuery.setParameter("username", username);
        typedQuery.setParameter("password", password);

        return typedQuery.getSingleResult();
    }

    public List<Users> findAllUsers() {
        TypedQuery<Users> typedQuery = em.createNamedQuery(Users.FIND_ALL, Users.class);
        return typedQuery.getResultList();
    }

    public Users updateUser(@NotNull Users user) {
        em.merge(user);
        return user;
    }

    public void removeUser(@NotNull Users user) {
        em.remove(em.merge(user));
    }    
}
