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
    @PersistenceContext(unitName = "OljaEE_1.0-SNAPSHOTPU")
    private EntityManager em;
}
