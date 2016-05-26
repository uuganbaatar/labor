package mn.odi.labor.entities.labor;

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

import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.enums.EduLevelEnum;
import mn.odi.labor.enums.EmploymentEnum;
import mn.odi.labor.enums.GenderEnum;
import mn.odi.labor.enums.YesNoEnum;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited(auditParents = BaseObject.class)
public class Employee extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	private String uuid;

	@Column(name = "regNumber")
	private String regNumber;

	@Column(name = "familyName")
	private String familyName;

	@Column(name = "surName")
	private String surName;

	@Column(name = "empName")
	private String empName;

	@Column(name = "gender")
	private GenderEnum gender;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "eduLevel")
	private EduLevelEnum eduLevel;

	@Column(name = "profession")
	private String profession;

	@Column(name = "employment")
	private EmploymentEnum employment;

	@ManyToOne
	@JoinColumn(name = "job_id", nullable = true)
	private Job job;
	
	@Column(name = "newJob")
	private YesNoEnum newJob;

	@ManyToOne
	@JoinColumn(name = "fundingsource_id", nullable = true)
	private FundingSource fundingSource;
	
	@Column(name = "currentJob")
	private String currentJob;

	@Column(name = "uamatCode")
	private String uamatCode;
	
	@Column(name = "movement")
	private String movement;
	
	@Column(name = "firedReason")
	private String firedReason;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = true)
	private Organization org;
	

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

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public EduLevelEnum getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(EduLevelEnum eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public EmploymentEnum getEmployment() {
		return employment;
	}

	public void setEmployment(EmploymentEnum employment) {
		this.employment = employment;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public YesNoEnum getNewJob() {
		return newJob;
	}

	public void setNewJob(YesNoEnum newJob) {
		this.newJob = newJob;
	}

	public FundingSource getFundingSource() {
		return fundingSource;
	}

	public void setFundingSource(FundingSource fundingSource) {
		this.fundingSource = fundingSource;
	}

	public String getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(String currentJob) {
		this.currentJob = currentJob;
	}

	public String getUamatCode() {
		return uamatCode;
	}

	public void setUamatCode(String uamatCode) {
		this.uamatCode = uamatCode;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public String getFiredReason() {
		return firedReason;
	}

	public void setFiredReason(String firedReason) {
		this.firedReason = firedReason;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getFullName(){
		return this.surName + " " + this.empName;
	}
	
	// ********************** Common Methods ********************** //

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("uuid", this.uuid).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof Job)) {
			return false;
		}

		final Employee u = (Employee) o;

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
		return getUuid().hashCode();
	}
}
