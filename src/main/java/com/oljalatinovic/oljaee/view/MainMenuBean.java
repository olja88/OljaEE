package com.oljalatinovic.oljaee.view;

import com.oljalatinovic.oljaee.entity.MainMenu;
import com.oljalatinovic.oljaee.service.MainMenuService;
import com.oljalatinovic.oljaee.util.Loggable;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Named
@SessionScoped
@Loggable
@CatchException
public class MainMenuBean  extends AbstractBean implements Serializable {
    
    private final List<MainMenu> meni;
    
    @Inject
    private MainMenuService mainService;

    public MainMenuBean() {        
        meni  = mainService.findAllMainMenus();
    }

    public List<MainMenu> getMeni() {
        return meni;
    }
    
}
