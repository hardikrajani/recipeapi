package com.hardik.abn.assessment.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.hardik.abn.assessment.model.entity.Recipe;

/**
 * The Class RecipeSpecificationsBuilder.
 */
public class RecipeSpecificationsBuilder {

	/** The params. */
	private final List<SearchCriteria> params;

	/**
	 * Instantiates a new recipe specifications builder.
	 */
	public RecipeSpecificationsBuilder() {
		params = new ArrayList<>();
	}

	/**
	 * With.
	 *
	 * @param searchCriteria the search criteria
	 * @return the recipe specifications builder
	 */
	public RecipeSpecificationsBuilder with(SearchCriteria searchCriteria) {
		return this.with(searchCriteria.getKey(), searchCriteria.getOperation(), searchCriteria.getValue());
	}

	/**
	 * With.
	 *
	 * @param key       the key
	 * @param operation the operation
	 * @param value     the value
	 * @return the recipe specifications builder
	 */
	public RecipeSpecificationsBuilder with(String key, String operation, Object value) {

		boolean isOr = false;
		if (key.contains("'")) {
			isOr = true;
			key = key.substring(1);
		}

		if (("true".equals(value) || "false".equals(value)) && value instanceof String) {
			value = Boolean.valueOf((String) value);
		}
		params.add(new SearchCriteria(isOr, key, operation, value));
		return this;
	}

	/**
	 * Builds the.
	 *
	 * @return the specification
	 */
	public Specification<Recipe> build() {
		if (params.isEmpty()) {
			return null;
		}

		List<Specification<Recipe>> specs = params.stream().map(RecipeSpecification::new).collect(Collectors.toList());

		Specification<Recipe> result = specs.get(0);

		for (int i = 1; i < params.size(); i++) {
			Specification<Recipe> specification = Specification.where(result);

			if (specification != null) {
				result = params.get(i).isOrPredicate() ? specification.or(specs.get(i))
						: specification.and(specs.get(i));
			}
		}
		return result;
	}
}
