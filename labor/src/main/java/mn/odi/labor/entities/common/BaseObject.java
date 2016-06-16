/* 
 * Package Name: mn.odi.scc.entities.common
 * File Name: BaseObject.java
 * 
 * Created By: Tserentogtokh.D
 * Created Date: 2014/04/14
 * 
 * History
 * ------------------------------------------------------------------------------
 * Date							Programmer						Description
 * ------------------------------------------------------------------------------
 * 2014/04/14 1.0.0 			Tserentogtokh.D				    Шинээр үүсгэв.
 * 2014/04/23 1.0.0 			Tserentogtokh.D				    isActive нэмсэн.
 * ------------------------------------------------------------------------------
 * 
 * ALL RIGHTS RESERVED COPYRIGHT (C) 2014 Od Innovation CO.,LTD SOFTWARE DIVSION
 */
package mn.odi.labor.entities.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tapestry5.beaneditor.NonVisual;

@MappedSuperclass
public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract String toString();

	public abstract boolean equals(Object o);

	public abstract int hashCode();

	@Column(name = "created_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id")
	private User createdBy;

	@NonVisual
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@NonVisual
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_by_id")
	private User modifiedBy;

	@NonVisual
	@Column(name = "deleted_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedDate;

	@NonVisual
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deleted_by_id")
	private User deletedBy;

	@NonVisual
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_by_he_org_id")
	private Organization modifiedOrg;

	@NonVisual
	@Column(name = "is_active")
	private Boolean isActive = true;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public User getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(User deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Organization getModifiedOrg() {
		return modifiedOrg;
	}

	public void setModifiedOrg(Organization modifiedOrg) {
		this.modifiedOrg = modifiedOrg;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getCreatedDateFormated() {
		return new SimpleDateFormat("yyyy-MM-dd").format(createdDate);
	}
	
	public String getModifiedDateFormated() {
		return new SimpleDateFormat("yyyy-MM-dd").format(modifiedDate);
	}
}
