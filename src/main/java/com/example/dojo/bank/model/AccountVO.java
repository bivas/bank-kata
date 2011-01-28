package com.example.dojo.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.NotNull;

@Entity(name = "Account")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"branch", "number"})})
public final class AccountVO implements Account {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@NotNull
	@OneToOne(targetEntity = OwnerVO.class, optional = false)
	private Owner owner;

	@NotNull
	private int branch;

	@NotNull
	private long number;

	@NotNull
	private double balance;

	AccountVO() {
	}

	public AccountVO(final Long id, final Owner owner, final int branch, final long number, final double balance) {
		this.id = id;
		this.owner = owner;
		this.branch = branch;
		this.number = number;
		this.balance = balance;
	}

	public AccountVO(final Owner owner, final int branch, final long number, final double balance) {
		this(null, owner, branch, number, balance);
	}

	@Override
	public boolean equals(final Object obj) {
		if ( this == obj ) {
			return true;
		} else if ( !(obj instanceof AccountVO)) {
			return false;
		}
		final AccountVO vo = (AccountVO) obj;
		final EqualsBuilder builder = new EqualsBuilder();
		builder.append(owner, vo.owner);
		builder.append(branch, vo.branch);
		builder.append(number, vo.number);
		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		final HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(owner);
		builder.append(branch);
		builder.append(number);
		return builder.toHashCode();
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);
		builder.append(owner);
		builder.append(branch);
		builder.append(number);
		return builder.toString();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Owner getOwner() {
		return owner;
	}

	void setOwner(final Owner owner) {
		this.owner = owner;
	}

	@Override
	public int getBranch() {
		return branch;
	}

	void setBranch(final int branch) {
		this.branch = branch;
	}

	@Override
	public long getNumber() {
		return number;
	}

	void setNumber(final long number) {
		this.number = number;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	void setBalance(final double balance) {
		this.balance = balance;
	}



}
