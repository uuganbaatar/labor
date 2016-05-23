package mn.odi.labor.entities.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.hibernate.envers.Audited;

import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.util.UUIDUtil;

@Entity
@Table(name = "lavlah_garsan")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited(auditParents = BaseObject.class)
public class LavlahGarsan extends BaseObject {

	private static final long serialVersionUID = 1L;

	@Id
	@NonVisual
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid", length = 32, insertable = true, updatable = false)
	private String uuid;

	@Column(name = "name")
	private String name;

	public LavlahGarsan() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

		if (!(o instanceof LavlahGarsan)) {
			return false;
		}

		final LavlahGarsan u = (LavlahGarsan) o;

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

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("uuid", this.uuid).toString();
	}
}