package com.hardik.abn.assessment.repository.specification;

import lombok.Data;

@Data
public class SearchCriteria {
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
