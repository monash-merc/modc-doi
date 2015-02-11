package edu.monash.merc.eddy.modc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by simonyu on 4/08/2014.
 */
@Entity
@Table(name = "muser", indexes = {@Index(name = "idx__user_unique_id", columnList = "unique_id"), @Index(name = "idx_uid_hashcode", columnList = "uid_hashcode"),
        @Index(name = "idx_first_name", columnList = "first_name"), @Index(name = "idx_last_name", columnList = "last_name"),
        @Index(name = "idx_password", columnList = "password"), @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_activate_hash_code", columnList = "activate_hash_code"), @Index(name = "reset_password_code", columnList = "reset_password_code")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "freqRegion")
public class User extends Domain {
    @Id
    @GeneratedValue(generator = "pk_generator")
    @GenericGenerator(name = "pk_generator", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "pk_gen_tab"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name ", value = "pk_next_val"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "pk_name"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "muser_id"),
                    @org.hibernate.annotations.Parameter(name = "increment_size  ", value = "5"),
                    @org.hibernate.annotations.Parameter(name = "optimizer ", value = "hilo")
            })
    @Column(name = "id", nullable = false)
    private long id;

    @Basic
    @Column(name = "unique_id", length = 100)
    private String uniqueId;

    @Basic
    @Column(name = "uid_hashcode", length = 100)
    private String uidHashCode;

    @Basic
    @Column(name = "first_name", length = 25)
    private String firstName;

    @Basic
    @Column(name = "last_name", length = 25)
    private String lastName;

    @Basic
    @Column(name = "display_name", length = 55)
    private String displayName;

    @Basic
    @Column(name = "password", length = 50)
    private String password;

    @Basic
    @Column(name = "email", length = 100)
    private String email;

    @Basic
    @Column(name = "activate_hash_code", length = 50)
    private String activateHashCode;

    @Basic
    @Column(name = "is_activated")
    private boolean isActivated;

    @Basic
    @Column(name = "is_rejected")
    private boolean isRejected;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registered_date")
    private Date registeredDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activated_date")
    private Date activatedDate;

    @Basic
    @Column(name = "reset_password_code", length = 50)
    private String resetPasswdHashCode;

    @Column(name = "user_type", columnDefinition = "integer", nullable = false)
    private int userType;

    @OneToOne(mappedBy = "user", targetEntity = Avatar.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Avatar avatar;

    @OneToOne(mappedBy = "user", targetEntity = Profile.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Profile profile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUidHashCode() {
        return uidHashCode;
    }

    public void setUidHashCode(String uidHashCode) {
        this.uidHashCode = uidHashCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        if (displayName == null) {
            displayName = firstName + " " + lastName;
        }
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivateHashCode() {
        return activateHashCode;
    }

    public void setActivateHashCode(String activateHashCode) {
        this.activateHashCode = activateHashCode;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean isRejected) {
        this.isRejected = isRejected;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getResetPasswdHashCode() {
        return resetPasswdHashCode;
    }

    public void setResetPasswdHashCode(String resetPasswdHashCode) {
        this.resetPasswdHashCode = resetPasswdHashCode;
    }
}
