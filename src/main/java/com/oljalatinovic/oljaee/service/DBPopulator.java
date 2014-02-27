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
    private UsersService userService;
    
    @Inject
    private CountryService countryService;

    @PostConstruct
    private void populateDB() {
        serbia = countryService.findCountry("SERBIA");
        if(serbia == null) {
           serbia = new Country("SR","SERBIA","Serbia","SER","111");
           countryService.createCountry(serbia);
        }
        
        user = userService.findUser("user");
        if(user == null) {
            user = new Users("User", "User", "user", "user", "user@oljaee.com", new Address("Moja ulica i broj", "A i grad", "44", new Country("SR","SERBIA","Serbia","SER","1")));
            userService.createUser(user);
        }
    
        admin = userService.findUser("admin");
        if(admin == null) {
            admin = new Users("Admin", "Admin", "admin", "admin", "admin@oljaee.com", new Address("Takodje samo 44", "Grad do", "32", new Country("SR","SERBIA","Serbia","SER","1")));
            userService.createUser(admin);
        }
        
        olja = userService.findUser("olja88");
        if(olja == null) {
            olja = new Users("Olja", "Latinović", "olja88", "password1", "olja@oljaee.com", new Address("Licau jbro55", "Dgra", "12", new Country("SR","SERBIA","Serbia","SER","1")));
            userService.createUser(olja);
        }
    }

    @PreDestroy
    private void clearDB() {
        
        olja = userService.findUser("Olja");
        if(olja != null)
            userService.removeUser(olja);
        user = userService.findUser("User");
        if(user != null)
            userService.removeUser(user);
        admin = userService.findUser("Admin");
        if(admin != null)
            userService.removeUser(admin);
        
        serbia = countryService.findCountry("SERBIA");
        if(serbia != null)
           countryService.removeCountry(serbia);
    }    
}
