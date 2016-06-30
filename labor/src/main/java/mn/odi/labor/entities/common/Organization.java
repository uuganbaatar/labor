/* 
 * Package Name: mn.odi.scc.entities.common
 * File Name: Organization.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Ð¨Ð¸Ð½Ñ�Ñ�Ñ€ Ò¯Ò¯Ñ�Ð³Ñ�Ð².
 * 
 * 2014/04/23 1.0.0 			Tserentogtokh.D				    Ó¨Ó©Ñ€Ñ‡Ð¸Ð»Ó©Ð»Ñ‚ Ð¾Ñ€ÑƒÑƒÐ»Ð°Ð².
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
*/

package mn.odi.labor.entities.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

import mn.odi.labor.enums.OrgTypeEnum;
import mn.odi.labor.util.UUIDUtil;

@Entity
@Table(name = "organization")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited(auditParents = BaseObject.class)
public class Organization extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2430043464624551384L;

	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	protected Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	protected String uuid;

	@Column(name = "name")
	protected String name;

	@Column(name = "code")
	protected String code;

	@Column(name = "reg_num")
	protected String regNum;

	@Column(name = "mobile_phone")
	protected String mobilePhone;

	@Column(name = "phone")
	protected String phone;

	@Column(name = "fax")
	protected String fax;

	@Column(name = "web")
	protected String web;

	@Column(name = "org_type")
	protected OrgTypeEnum orgType;

	@ManyToOne
	@JoinColumn(name = "sum_id")
	private SumDuureg sumId;

	public Organization() {
		this.uuid = UUIDUtil.getUUID();
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getRegNum() {
		return regNum;
	}

	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}

	public SumDuureg getSumId() {
		return sumId;
	}

	public void setSumId(SumDuureg sumId) {
		this.sumId = sumId;
	}

	public OrgTypeEnum getOrgType() {
		return orgType;
	}

	public void setOrgType(OrgTypeEnum orgType) {
		this.orgType = orgType;
	}

	// ********************** Common Methods ********************** //

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof Organization)) {
			return false;
		}

		final Organization u = (Organization) o;

		if (u.getId() == null) {
			return false;
		}

		if (this.getId() == null) {
			return false;
		}

		return getId().equals(u.getId());
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("uuid", this.uuid).toString();
	}

}