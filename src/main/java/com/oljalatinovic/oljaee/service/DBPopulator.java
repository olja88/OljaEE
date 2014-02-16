package com.oljalatinovic.oljaee.service;

import com.oljalatinovic.oljaee.entity.Address;
import com.oljalatinovic.oljaee.entity.Country;
import com.oljalatinovic.oljaee.entity.User;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Singleton
@Startup
public class DBPopulator {
    
    private Country serbia;
    
    private User admin;
    private User user;
    private User olja;
    
    @Inject
    private UserService userService;
    
    @Inject
    private CountryService countryService;

    @PostConstruct
    private void populateDB() {
        serbia = new Country(Long.valueOf("1188"), "SR","SERBIA","Serbia","SER","1");
        
        countryService.createCountry(serbia);
        
        user = new User("User", "User", "user", "user", "user@petstore.org", new Address("Petstore", "Land", "666", serbia));
        admin = new User("Admin", "Admin", "admin", "admin", "admin@petstore.org", new Address("Petstore", "Land", "666", serbia));
        olja = new User("Marc", "Fleury", "marc", "marc", "marc@jboss.org", new Address("65 Ritherdon Road", "Los Angeles", "56421", serbia));
        
        userService.createUser(user);
        userService.createUser(admin);
        userService.createUser(olja);
    }

    @PreDestroy
    private void clearDB() {
        userService.removeUser(olja);
        userService.removeUser(user);
        userService.removeUser(admin);
        
        countryService.removeCountry(serbia);
    }    
}
