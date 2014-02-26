package com.oljalatinovic.oljaee.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@MappedSuperclass
public abstract class CodesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)     
    private List<Users> createdBy;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)     
    private List<Users> modifiedBy;    
    
    @Temporal(TemporalType.TIMESTAMP)   
    private Date creationDate;  
    
    @Temporal(TemporalType.TIMESTAMP)   
    private Date modificationDate;      
    
    @Convert(converter=BooleanConverter.class)
    private Boolean isActive;
            
    // Implementirati pravno lice nakon sifarnika pravnih lica

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    public List<Users> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<Users> createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }    
    
    public List<Users> getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(List<Users> modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }    
}
