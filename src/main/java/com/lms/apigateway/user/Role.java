package com.lms.apigateway.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Role implements Serializable {
	protected static final long serialVersionUID = 1L;

	protected Long id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp createdTime;

	protected String description;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	protected Timestamp modifiedTime;

	@NotNull
	protected String name;

	protected long usersCount;

	protected List<Permission> permissions;

	public Role() {
	}

	public Role(Timestamp createdTime, String description, @NotNull Timestamp modifiedTime, @NotNull String name) {
		super();
		this.createdTime = createdTime;
		this.description = description;
		this.modifiedTime = modifiedTime;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getModifiedTime() {
		return this.modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public long getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(long usersCount) {
		this.usersCount = usersCount;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", createdTime=" + createdTime + ", description=" + description + ", modifiedTime="
				+ modifiedTime + ", name=" + name + ", usersCount=" + usersCount + ", permissions=" + permissions + "]";
	}

}