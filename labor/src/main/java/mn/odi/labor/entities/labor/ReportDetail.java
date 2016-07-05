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

import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.enums.ReportDetailType;
import mn.odi.labor.util.UUIDUtil;

@Entity
@Table(name = "report_detail")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited(auditParents = BaseObject.class)
public class ReportDetail extends BaseObject {

	public static final long serialVersionUID = 1L;

	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	public Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	public String uuid;

	@ManyToOne
	@JoinColumn(name = "general_type")
	public GeneralType generalType;

	@Column(name = "job_type")
	public JobTypeEnum jobType;

	@ManyToOne
	@JoinColumn(name = "job")
	public Job job;

	@ManyToOne
	@JoinColumn(name = "report_status_id")
	public ReportStatus reportStatusId;

	@Column(name = "detail_type")
	public ReportDetailType detailType;

	@Column(name = "value")
	public Integer value;

	public ReportDetail() {
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

	public GeneralType getGeneralType() {
		return generalType;
	}

	public void setProfessionType(GeneralType generalType) {
		this.generalType = generalType;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	// ********************** Common Methods ********************** //

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setGeneralType(GeneralType generalType) {
		this.generalType = generalType;
	}

	public ReportStatus getReportStatusId() {
		return reportStatusId;
	}

	public void setReportStatusId(ReportStatus reportStatusId) {
		this.reportStatusId = reportStatusId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("uuid", this.uuid).toString();
	}

	public ReportDetailType getDetailType() {
		return detailType;
	}

	public void setDetailType(ReportDetailType detailType) {
		this.detailType = detailType;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!(o instanceof ReportDetail)) {
			return false;
		}

		final ReportDetail u = (ReportDetail) o;

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
