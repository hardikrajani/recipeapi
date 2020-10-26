package com.hardik.abn.assessment.repository.specification;

import java.io.Serializable;

import lombok.Data;

@Data
public class SearchCriteria implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1973003177274238863L;

	/** The key. */
	private String key;

	/** The operation. */
	private String operation;

	/** The value. */
	private transient Object value;

	/** The or predicate. */
	private boolean orPredicate;

	/**
	 * Instantiates a new search criteria.
	 *
	 * @param key       the key
	 * @param operation the operation
	 * @param value     the value
	 */
	public SearchCriteria(final String key, final String operation, final Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	/**
	 * Instantiates a new search criteria.
	 *
	 * @param orPredicate the or predicate
	 * @param key         the key
	 * @param operation   the operation
	 * @param value       the value
	 */
	public SearchCriteria(final boolean orPredicate, final String key, final String operation, final Object value) {
		super();
		this.orPredicate = orPredicate;
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

}
