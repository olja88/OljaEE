package com.oljalatinovic.oljaee.view;

import com.oljalatinovic.oljaee.util.Loggable;
import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */

@Named
@SessionScoped
@Loggable
public class LocaleBean implements Serializable {

    @Produces
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    // ======================================
    // =          Business methods          =
    // ======================================

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}