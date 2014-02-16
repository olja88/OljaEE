package com.oljalatinovic.oljaee.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
public class DBProducer {
    @Produces
    @PersistenceContext(unitName = "applicationPetstorePU")
    private EntityManager em;
}
