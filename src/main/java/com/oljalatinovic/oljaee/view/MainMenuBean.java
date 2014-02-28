package com.oljalatinovic.oljaee.view;

import com.oljalatinovic.oljaee.entity.MainMenu;
import com.oljalatinovic.oljaee.service.MainMenuService;
import com.oljalatinovic.oljaee.util.Loggable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class MainMenuBean extends AbstractBean implements Serializable {
    
//    private final List<MainMenu> meni = new ArrayList<>();
    private List<MainMenu> meni;
    
//    @Inject
//    private MainMenuService mainService;

    public MainMenuBean() {        
//        meni.addAll(mainService.findAllMainMenus());
//        meni.add(mainService.findMainMenu("Administracija"));
        meni = new ArrayList<MainMenu>();  
          
        meni.add(new MainMenu("Administracija", "Administracija", "Administracija", null, null, new Date(), new Date(), Boolean.TRUE , null, "imgAdmin"));
        meni.add(new MainMenu("Sifarnici", "Sifarnici", "Sifarnici", null, null, new Date(), new Date(), Boolean.TRUE , null, "imgAdmin"));
    }

    public List<MainMenu> getMeni() {
        return meni;
    }
    
}
