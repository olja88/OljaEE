package com.oljalatinovic.oljaee.view;

import com.oljalatinovic.oljaee.util.Loggable;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.layout.LayoutUnit;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Named
@SessionScoped
@Loggable
@CatchException
public class MainController extends Controller implements Serializable {

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unit Closed", "Position:'" + ((LayoutUnit) event.getComponent()).getPosition() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, ((LayoutUnit) event.getComponent()).getPosition() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    public void handleResize(ResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, ((LayoutUnit) event.getComponent()).getPosition() + " resized", "Width:" + event.getWidth() + ", Height:" + event.getHeight());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
