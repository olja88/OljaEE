package com.oljalatinovic.oljaee.service;

import com.oljalatinovic.oljaee.entity.Address;
import com.oljalatinovic.oljaee.entity.Country;
import com.oljalatinovic.oljaee.entity.Users;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@DataSourceDefinition (
    name="java:global/jdbc/oljaDS",
    className="org.apache.derby.jdbc.ClientDataSource",
    portNumber=1527,
    serverName="localhost",
    databaseName="oljaDB",
    user="olja88",
    password="password1"
)
@Singleton
@Startup
public class DBPopulator {
    
    private Country serbia;
    
    private Users admin;
    private Users user;
    private Users olja;
    
    @Inject
    private UserService userService;
    
    @Inject
    private CountryService countryService;

    @PostConstruct
    private void populateDB() {
        serbia = new Country(Long.valueOf("1188"), "SR","SERBIA","Serbia","SER","1");
        
        countryService.createCountry(serbia);
        
        user = new Users("User", "User", "user", "user", "user@oljaee.com", new Address("Moja ulica i broj", "A i zemlja", "44", serbia));
        admin = new Users("Admin", "Admin", "admin", "admin", "admin@oljaee.com", new Address("Takodje samo 44", "Zemlja do", "32", serbia));
        olja = new Users("Olja", "Latinović", "olja88", "password1", "olja@oljaee.com", new Address("Licau jbro55", "Dgra", "12", serbia));
        
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
