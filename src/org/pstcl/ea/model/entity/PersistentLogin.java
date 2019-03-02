package org.pstcl.ea.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable {
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;
	@Id
	private String series;
	@Column(name = "TOKEN", unique = true, nullable = false)
	private String token;
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	public Date getLast_used() {
		return this.last_used;
	}

	public String getSeries() {
		return this.series;
	}

	public String getToken() {
		return this.token;
	}

	public String getUsername() {
		return this.username;
	}

	public void setLast_used(final Date last_used) {
		this.last_used = last_used;
	}

	public void setSeries(final String series) {
		this.series = series;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public void setUsername(final String username) {
		this.username = username;
	}
}