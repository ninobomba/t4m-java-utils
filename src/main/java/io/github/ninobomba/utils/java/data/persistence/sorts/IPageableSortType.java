package io.github.ninobomba.utils.java.data.persistence.sorts;

public interface IPageableSortType {

    String ASC = "ASC";
    String DESC = "DESC";
    String NULLS = "NULLS";

    String CREATED_BY = "CREATED_BY";
    String CREATED_AT = "CREATED_AT";
    String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
    String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";

    String LAST_MODIFIED_DATE = "LAST_MODIFIED_DATE";
    String CREATED_DATE = "CREATED_DATE";
}