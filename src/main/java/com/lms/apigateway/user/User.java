package com.lms.apigateway.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Serializable {
	protected static final long serialVersionUID = 1L;

	protected Long id;

	protected String accessToken;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp createdTime;

	protected String fax;

	protected String mobile;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Timestamp modifiedTime;

	@Size(max = 100)
	protected String name;

	protected String password;

	protected String phone;

	protected String refreshToken;

	protected String resetToken;

	protected String emailVerificationToken;

	protected UserStatus status;

	protected Long subscriberId;

	@Size(max = 10)
	protected String surname;

	protected String username;

	protected Language language;

	protected Role role;

	public User() {
	}

	public User(String accessToken, Timestamp createdTime, String fax,  String mobile,
			 Timestamp modifiedTime,  String name, String password, String phone, String refreshToken,
			String resetToken, String emailVerificationToken,  UserStatus status,  Long subscriberId,
			String surname,  String username, Language language, Role role) {
		super();
		this.accessToken = accessToken;
		this.createdTime = createdTime;
		this.fax = fax;
		this.mobile = mobile;
		this.modifiedTime = modifiedTime;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.refreshToken = refreshToken;
		this.resetToken = resetToken;
		this.status = status;
		this.subscriberId = subscriberId;
		this.surname = surname;
		this.username = username;
		this.language = language;
		this.role = role;
		this.emailVerificationToken = emailVerificationToken;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public UserStatus getStatus() {
		return this.status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Long getSubscriberId() {
		return this.subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", accessToken=" + accessToken + ", createdTime=" + createdTime + ", fax=" + fax
				+ ", mobile=" + mobile + ", modifiedTime=" + modifiedTime + ", name=" + name + ", password=" + password
				+ ", phone=" + phone + ", refreshToken=" + refreshToken + ", resetToken=" + resetToken
				+ ", emailVerificationToken=" + emailVerificationToken + ", status=" + status + ", subscriberId="
				+ subscriberId + ", surname=" + surname + ", username=" + username + ", language=" + language
				+ ", role=" + role + "]";
	}

}
