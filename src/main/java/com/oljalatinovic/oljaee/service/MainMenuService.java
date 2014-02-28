package com.oljalatinovic.oljaee.service;

import com.oljalatinovic.oljaee.entity.MainMenu;
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
public class MainMenuService implements Serializable {
    
    @Inject
    private EntityManager em;
    
    public MainMenu createMainMenu(@NotNull MainMenu mainMenu) {
        em.persist(mainMenu);
        return mainMenu;
    }

    public MainMenu findMainMenu(@NotNull String code) {
        TypedQuery<MainMenu> typedQuery = em.createNamedQuery(MainMenu.FIND_BY_CODE, MainMenu.class);
        typedQuery.setParameter("code", code);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    } 
    
    public List<MainMenu> findAllMainMenus() {
        TypedQuery<MainMenu> typedQuery = em.createNamedQuery(MainMenu.FIND_ALL, MainMenu.class);
        return typedQuery.getResultList();
    }

    public MainMenu updateMainMenu(@NotNull MainMenu mainMenu) {
        em.merge(mainMenu);
        return mainMenu;
    }

    public void removeMainMenu(@NotNull MainMenu mainMenu) {
        em.remove(em.merge(mainMenu));
    }     
    
}
