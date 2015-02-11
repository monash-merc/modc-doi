package edu.monash.merc.eddy.modc.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by simonyu on 25/08/2014.
 */
@Entity
@DiscriminatorValue("group")
public class MGroup extends MParty {

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
