package com.hardik.abn.assessment.repository.specification;

import java.io.Serializable;

import lombok.Data;

@Data
public class SearchCriteria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1973003177274238863L;

	private String key;
	private String operation;
	private Object value;
	private boolean orPredicate;

	public SearchCriteria(final String key, final String operation, final Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public SearchCriteria(final boolean orPredicate, final String key, final String operation, final Object value) {
		super();
		this.orPredicate = orPredicate;
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

}
