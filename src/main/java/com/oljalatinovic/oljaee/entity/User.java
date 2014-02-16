package com.oljalatinovic.oljaee.entity;

import com.oljalatinovic.oljaee.validator.Email;
import com.oljalatinovic.oljaee.validator.Login;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = User.FIND_BY_USERNAME_PASSWORD, query = "SELECT u FROM User u WHERE c.username = :username AND u.password = :password"),
    @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u")
})
@XmlRootElement
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    @Login
    private String username;
    @Column(nullable = false, length = 256)
    @NotNull
    @Size(min = 1, max = 256)
    private String password;
    @Column(nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String firstname;
    @Column(nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String lastname;
    private String telephone;

    @Embedded
    @Valid
    private Address homeAddress = new Address();
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Transient
    private Integer age;

    @NotNull
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String loginToken; // TODO: make collection later

    @ElementCollection(targetClass = Group.class, fetch = EAGER)
    @Enumerated(STRING)
    @CollectionTable(name = "user_group")
    @Column(name = "group_name")
    private List<Group> groups = new ArrayList<>();

    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_USERNAME_PASSWORD = "User.findByUsernameAndPassword";
    public static final String FIND_ALL = "User.findAll";

    public User() {
    }

    public User(String firstname, String lastname, String username, String plainTextPassword, String email, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = digestPassword(plainTextPassword);
        this.email = email;
        this.homeAddress = address;
        this.dateOfBirth = new Date();
    }

     public String digestPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return new BASE64Encoder().encode(passwordDigest);
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password", e);
        }
    }   
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }    

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<String> getRoles() {
        Set<String> roles = new HashSet<>();

        for (Group group : getGroups()) {
            for (Role role : group.getRoles()) {
                roles.add(role.name());
            }
        }

        return new ArrayList<>(roles);
    }

}
