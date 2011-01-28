package com.example.dojo.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.NotNull;

@Entity(name = "Owner")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "socialSecurityNumber")})
public class OwnerVO implements Owner {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@NotNull
	private String socialSecurityNumber;

	@NotNull
	private String givenName;

	@NotNull
	private String surname;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Gendre gendre;

	OwnerVO() {
	}

	public OwnerVO(final Long id, final String socialSecurityNumber, final String givenName, final String surname, final Gendre gendre) {
		this.id = id;
		this.socialSecurityNumber = socialSecurityNumber;
		this.givenName = givenName;
		this.surname = surname;
		this.gendre = gendre;
	}

	public OwnerVO(final String socialSecurityNumber, final String givenName, final String surname, final Gendre gendre) {
		this(null, socialSecurityNumber, givenName, surname, gendre);
	}

	@Override
	public boolean equals(final Object obj) {
		if ( this == obj ) {
			return true;
		} else if ( !(obj instanceof OwnerVO)) {
			return false;
		}
		final OwnerVO vo = (OwnerVO) obj;
		final EqualsBuilder builder = new EqualsBuilder();
		builder.append(socialSecurityNumber, vo.socialSecurityNumber);
		builder.append(givenName, vo.givenName);
		builder.append(surname, vo.surname);
		builder.append(gendre, vo.gendre);
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		final HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(socialSecurityNumber);
		builder.append(givenName);
		builder.append(surname);
		builder.append(gendre);
		return builder.toHashCode();
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		builder.append(socialSecurityNumber);
		builder.append(givenName);
		builder.append(surname);
		builder.append(gendre);
		return builder.toString();
	}

	@Override
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	void setSocialSecurityNumber(final String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	@Override
	public String getGivenName() {
		return givenName;
	}

	void setGivenName(final String givenName) {
		this.givenName = givenName;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	void setSurname(final String surname) {
		this.surname = surname;
	}

	@Override
	public Gendre getGendre() {
		return gendre;
	}

	void setGendre(final Gendre gendre) {
		this.gendre = gendre;
	}

	@Override
	public Long getId() {
		return id;
	}


}
