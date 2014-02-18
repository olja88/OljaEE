package com.oljalatinovic.oljaee.view;

import com.oljalatinovic.oljaee.entity.Users;
import com.oljalatinovic.oljaee.service.UsersService;
import com.oljalatinovic.oljaee.util.Loggable;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */

@Named
@SessionScoped
@Loggable
@CatchException
public class AccountBean extends AbstractBean implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private UsersService usersService;

    @Inject
    private CredentialsBean credentials;

    @Inject
    private Conversation conversation;

    @Produces
    @LoggedIn
    private Users loggedinUser;

    @Inject
    @SessionScoped
    private transient LoginContext loginContext;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public String doLogin() throws LoginException {
        if (credentials.getLogin() == null || "".equals(credentials.getLogin())) {
            addWarningMessage("id_filled");
            return null;
        }
        if (credentials.getPassword() == null || "".equals(credentials.getPassword())) {
            addWarningMessage("pwd_filled");
            return null;
        }

// TODO       loginContext.login();
        loggedinUser = usersService.findUser(credentials.getLogin());
        return "main.faces";
    }

    public String doCreateNewAccount() {

        // Login has to be unique
        if (usersService.doesLoginAlreadyExist(credentials.getLogin())) {
            addWarningMessage("login_exists");
            return null;
        }

        // Id and password must be filled
        if ("".equals(credentials.getLogin()) || "".equals(credentials.getPassword()) || "".equals(credentials.getPassword2())) {
            addWarningMessage("id_pwd_filled");
            return null;
        } else if (!credentials.getPassword().equals(credentials.getPassword2())) {
            addWarningMessage("both_pwd_same");
            return null;
        }

        // Login and password are ok
        loggedinUser = new Users();
        loggedinUser.setUsername(credentials.getLogin());
        loggedinUser.setPassword(loggedinUser.digestPassword(credentials.getPassword()));

        return "createaccount.faces";
    }

    public String doCreateCustomer() {
        loggedinUser = usersService.createUser(loggedinUser);
        return "main.faces";
    }


    public String doLogout() {
        loggedinUser = null;
        // Stop conversation
        if (!conversation.isTransient()) {
            conversation.end();
        }
        addInformationMessage("been_loggedout");
        return "main.faces";
    }

    public String doUpdateAccount() {
        loggedinUser = usersService.updateUser(loggedinUser);
        addInformationMessage("account_updated");
        return "showaccount.faces";
    }

    public boolean isLoggedIn() {
        return loggedinUser != null;
    }

    public Users getLoggedinUser() {
        return loggedinUser;
    }

    public void setLoggedinUser(Users loggedinUser) {
        this.loggedinUser = loggedinUser;
    }
}
