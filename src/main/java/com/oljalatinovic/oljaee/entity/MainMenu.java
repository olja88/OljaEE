package com.oljalatinovic.oljaee.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = MainMenu.FIND_BY_CODE, query = "SELECT m FROM MainMenu m WHERE m.code = :code"),
    @NamedQuery(name = MainMenu.FIND_ALL, query = "SELECT m FROM MainMenu m")
})
@XmlRootElement
public class MainMenu extends CodesEntity {
// Veza sam sa sobom - hijerarhijsko meni

    public static final String FIND_BY_CODE = "MainMenu.findByCode";
    public static final String FIND_ALL = "MainMenu.findAll";

    public MainMenu() {
    }

    public MainMenu(String code, String description, String descriptionLong, Users createdBy, Users modifiedBy, Date creationDate, Date modificationDate, Boolean isActive, Long parentId, String imgPath) {
        this.code = code;
        this.description = description;
        this.descriptionLong = descriptionLong;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modificationDate = modificationDate;
        this.isActive = isActive;
        this.parentId = parentId;
        this.imgPath = imgPath;
    }

    private Long parentId;

    private String imgPath;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
