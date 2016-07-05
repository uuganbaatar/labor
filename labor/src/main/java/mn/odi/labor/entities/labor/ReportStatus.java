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
import mn.odi.labor.enums.ReportStatusEnum;
import mn.odi.labor.util.UUIDUtil;

@Entity
@Table(name = "report_status")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited(auditParents = BaseObject.class)
public class ReportStatus extends BaseObject {

	public static final long serialVersionUID = 1L;

	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	public Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	public String uuid;

	@ManyToOne
	@JoinColumn(name = "report_id")
	public Report reportId;

	@Column(name = "reportStatus")
	public ReportStatusEnum reportStatus;

	@Column(name = "year")
	public Integer year;

	@Column(name = "month")
	public Integer month;

	@ManyToOne
	@JoinColumn(name = "org_id")
	public Organization orgId;

	public ReportStatus() {
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

	// ********************** Common Methods ********************** //

	public ReportStatusEnum getReportStatus() {
		return reportStatus;
	}

	public Report getReportId() {
		return reportId;
	}

	public void setReportId(Report reportId) {
		this.reportId = reportId;
	}

	public void setReportStatus(ReportStatusEnum reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Organization getOrgId() {
		return orgId;
	}

	public void setOrgId(Organization orgId) {
		this.orgId = orgId;
	}

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

		if (!(o instanceof ReportStatus)) {
			return false;
		}

		final ReportStatus u = (ReportStatus) o;

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
