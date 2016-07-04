package mn.odi.labor.entities.labor;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.CompanyTrend;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.util.UUIDUtil;

@Entity
@Table(name = "job")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited(auditParents = BaseObject.class)
public class Job extends BaseObject {

	public static final long serialVersionUID = 1L;

	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	public Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	public String uuid;

	@Column(name = "name")
	public String jobName;

	@Column(name = "isNew")
	public Boolean isNew;

	@Column(name = "jobDate")
	public Date jobDate;

	@ManyToOne
	@JoinColumn(name = "fundingsource_id", nullable = true)
	public AjiliinBairHurungu fundingSource;

	@ManyToOne
	@JoinColumn(name = "generaltype_id", nullable = true)
	public GeneralType generalType;

	@ManyToOne
	@JoinColumn(name = "company_trend_id", nullable = true)
	public CompanyTrend companyTrendId;

	@Column(name = "jobType")
	public JobTypeEnum jobType;

	@ManyToOne
	@JoinColumn(name = "org_id", nullable = true)
	public Organization org;

	public Job() {
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public AjiliinBairHurungu getFundingSource() {
		return fundingSource;
	}

	public void setFundingSource(AjiliinBairHurungu fundingSource) {
		this.fundingSource = fundingSource;
	}

	public GeneralType getGeneralType() {
		return generalType;
	}

	public void setGeneralType(GeneralType generalType) {
		this.generalType = generalType;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public Organization getOrgId() {
		return org;
	}

	public void setOrgId(Organization org) {
		this.org = org;
	}

	public String getJobDateFormated() {
		return new SimpleDateFormat("yyyy-MM-dd").format(jobDate);
	}

	public CompanyTrend getCompanyTrendId() {
		return companyTrendId;
	}

	public void setCompanyTrendId(CompanyTrend companyTrendId) {
		this.companyTrendId = companyTrendId;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
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

		final Job u = (Job) o;

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
}
