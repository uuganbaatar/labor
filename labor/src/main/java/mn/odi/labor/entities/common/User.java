/* 
 * Package Name: mn.odi.scc.entities.common
 * File Name: User.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
*/
package mn.odi.labor.entities.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import mn.odi.labor.enums.RoleEnum;
import mn.odi.labor.util.UUIDUtil;

@Entity
@Audited(auditParents = BaseObject.class)
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseObject implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	private String uuid;

	@NonVisual
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "username", unique = true, length = 20)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "regNum")
	private String regNum;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "mobilephone")
	private String mobilephone;

	@Column(name = "email")
	private String email;

	@Column(name = "pictureName")
	private String pictureName;

	@Lob
	@Column(name = "pictureSource")
	private byte[] pictureSource;

	@Column(name = "lastAccessDate")
	private Date lastAccessDate;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = true)
	private Organization org;

	@ManyToOne
	@JoinColumn(name = "department_id", nullable = true)
	private Department department;

	@Column(name = "currentrole")
	private RoleEnum currentrole;

	// acegi
	@Transient
	private List<GrantedAuthority> authorities;

	@Transient
	private boolean accountNonExpired = true;

	@Transient
	private boolean accountNonLocked = true;

	@Transient
	private boolean credentialsNonExpired = true;

	@Transient
	private String confirmPassword;

	// ********************** Accessor Methods ********************** //
	public User() {
		this.uuid = UUIDUtil.getUUID();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();

		this.password = encoder.encodePassword(password, null);
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public byte[] getPictureSource() {
		return pictureSource;
	}

	public void setPictureSource(byte[] pictureSource) {
		this.pictureSource = pictureSource;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	// ********************** Common Methods ********************** //
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (!(o instanceof User)) {
			return false;
		}

		final User u = (User) o;

		if (username == null) {
			return false;
		}

		return username.equals(u.username);
	}

	public int hashCode() {
		return username.hashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("username", this.username).toString();
	}

	// ********************** Business Methods ********************** //

	// acegi
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public List<GrantedAuthority> getAuthorities() {
		if (authorities == null) {
			authorities = new ArrayList<GrantedAuthority>();

			authorities.add(new GrantedAuthorityImpl("ROLE_" + currentrole.toString().toUpperCase()));
			System.out.println("[ROLE:]" + authorities.get(0).getAuthority());
		}

		return authorities;
	}

	public List<String> getRoleNames() {
		List<String> roleNames = new ArrayList<String>();

		roleNames.add(currentrole.toString());

		return roleNames;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getFullName() {
		return lastname + "." + firstname;
	}

	@NonVisual
	public String getShortname() {
		if (lastname != null && lastname.length() > 1) {
			return lastname.substring(0, 1) + "." + firstname;
		}

		return firstname;
	}

	public RoleEnum getCurrentrole() {
		return currentrole;
	}

	public void setCurrentrole(RoleEnum currentrole) {
		this.currentrole = currentrole;
	}

	public boolean isEnabled() {
		return true;
	}

}