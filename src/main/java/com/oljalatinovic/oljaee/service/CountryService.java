package com.oljalatinovic.oljaee.service;

import com.oljalatinovic.oljaee.entity.Country;
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
public class CountryService implements Serializable {
    @Inject
    private EntityManager em;
    
    public Country createCountry(@NotNull Country country) {
        em.persist(country);
        return country;
    }

    public Country findCountry(@NotNull String name) {
        TypedQuery<Country> typedQuery = em.createNamedQuery(Country.FIND_BY_NAME, Country.class);
        typedQuery.setParameter("name", name);

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Country> findAllCountries() {
        TypedQuery<Country> typedQuery = em.createNamedQuery(Country.FIND_ALL, Country.class);
        return typedQuery.getResultList();
    }

    public Country updateCountry(@NotNull Country country) {
        em.merge(country);
        return country;
    }

    public void removeCountry(@NotNull Country country) {
        em.remove(em.merge(country));
    }        
}
