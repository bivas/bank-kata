package com.example.dojo.bank.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.example.dojo.bank.model.Entity;

public abstract class AbstractDao<T extends Entity> {


	private final HibernateTemplate hibernateTemplate;

	protected abstract Class<? extends T> getPersistentClass();

	protected AbstractDao(final HibernateTemplate hibernateTemplate) {
		Assert.notNull(hibernateTemplate, "hibernateTemplate is requierd");
		this.hibernateTemplate = hibernateTemplate;
	}

	protected Session getCurrentSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	protected T findEntityById(final Long id) {
		return hibernateTemplate.load(getPersistentClass(), id);
	}

	protected T saveOrUpdate(final T entity) {
		if (!isNew(entity)) {
			hibernateTemplate.evict(entity);
		}
		hibernateTemplate.saveOrUpdate(entity);
		return entity;
	}

	private boolean isNew(final T entity) {
		return entity.getId() == null;
	}

	protected List<T> findByCriteria(final Criterion... criterions) {
		final Criteria criteria = internalCreateCriteria(criterions);
		@SuppressWarnings("unchecked")
		final List<T> returnList = criteria.list();
		return returnList;
	}

	protected T findUniqueByCriteria(final Criterion... criterions) {
		final Criteria criteria = internalCreateCriteria(criterions);
		return getPersistentClass().cast(criteria.uniqueResult());
	}

	private Criteria internalCreateCriteria(final Criterion... criterions) {
		Assert.noNullElements(criterions, "criterions shouldn't contain null elements");
		final Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
		for (final Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

}
