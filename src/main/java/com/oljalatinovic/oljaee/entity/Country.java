package com.oljalatinovic.oljaee.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olja Latinović <oljalatinovic88@gmail.com>
 */
@Entity
@Cacheable
@NamedQueries({
    @NamedQuery(name = Country.FIND_BY_NAME, query = "SELECT c FROM Country c WHERE c.name = :name"),
    @NamedQuery(name = Country.FIND_ALL, query = "SELECT c FROM Country c")
})
@XmlRootElement
public class Country extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 2, max = 2)
    private String isoCode;
    @Column(nullable = false, length = 80)
    @NotNull
    @Size(min = 2, max = 80)
    private String name;
    @Column(nullable = false, length = 80)
    @NotNull
    @Size(min = 2, max = 80)
    private String printableName;
    @Column(length = 3)
    @Size(min = 3, max = 3)
    private String iso3;
    @Column(length = 3)
    @Size(min = 3, max = 3)
    private String numcode;
    
    public static final String FIND_BY_NAME = "Country.findByName";
    public static final String FIND_ALL = "Country.findAll";    

    public Country() {
    }

    public Country(String isoCode, String name, String printableName) {
        this.isoCode = isoCode;
        this.name = name;
        this.printableName = printableName;
    }

    public Country(String isoCode, String name, String printableName, String iso3, String numcode) {
        this.isoCode = isoCode;
        this.name = name;
        this.printableName = printableName;
        this.iso3 = iso3;
        this.numcode = numcode;
    }

    
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrintableName() {
        return printableName;
    }

    public void setPrintableName(String printableName) {
        this.printableName = printableName;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getNumcode() {
        return numcode;
    }

    public void setNumcode(String numcode) {
        this.numcode = numcode;
    }

}
