package com.hardik.abn.assessment.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.hardik.abn.assessment.model.entity.Recipe;

public class RecipeSpecificationsBuilder {

    private final List<SearchCriteria> params;
    
    public RecipeSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public RecipeSpecificationsBuilder with(SearchCriteria searchCriteria) {
    	return this.with(searchCriteria.getKey(), searchCriteria.getOperation(), searchCriteria.getValue());
    }
    public RecipeSpecificationsBuilder with(String key, String operation, Object value) {
    	System.out.println(key + " : " + value);
    	boolean isOr = false;
    	if(key.contains("'")) {
    		isOr = true;
    		key = key.substring(1);
    	}
    		
    	if (("true".equals(value) || "false".equals(value)) && value instanceof String) {
    		value = Boolean.valueOf((String)value);
    	}
        params.add(new SearchCriteria(isOr, key, operation, value));
        return this;
    }
 
    public Specification<Recipe> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<Recipe>> specs = params.stream()
          .map(RecipeSpecification::new)
          .collect(Collectors.toList());
        
        Specification<Recipe> result = specs.get(0);
 
        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
              .isOrPredicate()
                ? Specification.where(result)
                  .or(specs.get(i))
                : Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}
