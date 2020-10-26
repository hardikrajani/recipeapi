package com.hardik.abn.assessment.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hardik.abn.assessment.exception.RecipeAttributeNotFoundException;
import com.hardik.abn.assessment.model.entity.Recipe;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecipeSpecification implements Specification<Recipe> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3783700882212171019L;

	/** The criteria. */
	private SearchCriteria criteria;

	/**
	 * To predicate.
	 *
	 * @param root    the root
	 * @param query   the query
	 * @param builder the builder
	 * @return the predicate
	 */
	@Override
	public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		try {
			if (criteria.getOperation().equalsIgnoreCase(">")) {
				return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()),
						criteria.getValue().toString());
			} else if (criteria.getOperation().equalsIgnoreCase("<")) {
				return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
			} else if (criteria.getOperation().equalsIgnoreCase(":")) {
				if (root.get(criteria.getKey()).getJavaType() == String.class) {
					return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
				} else {
					return builder.equal(root.get(criteria.getKey()), criteria.getValue());
				}
			}
		} catch (IllegalArgumentException e) {
			throw new RecipeAttributeNotFoundException(e.getMessage());
		}
		return null;
	}

}
