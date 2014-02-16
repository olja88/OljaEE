package com.oljalatinovic.oljaee.entity;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static com.oljalatinovic.oljaee.entity.Role.TEST_SERVLET_ACCESS;
import static com.oljalatinovic.oljaee.entity.Role.USER_MANAGEMENT;

import java.util.List;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
public enum Group {

    USERS, ADMINISTRATORS(TEST_SERVLET_ACCESS, USER_MANAGEMENT);

    private List<Role> roles;

    private Group(Role... roles) {
        this.roles = unmodifiableList(asList(roles));
    }

    public List<Role> getRoles() {
        return roles;
    }

}
