package com.github.jasongoetz.asanajama.domain;


import java.util.*;

public enum FieldDataTypeEnum {

    INTEGER(1, "Integer"),
    STRING(2, "Text Field"),
    TEXT(3, "Text Box", FieldControlType.TEXTAREA, FieldControlType.RICHTEXT),
    LOOKUP(4, "Pick List", true),
    DATE(5, "Date"),
    USER(6, "User", true),
    RELEASE(7, "Release", true, false),
    BOOLEAN(8, "Flag", true),
    GROUP(9, "GROUP", true),
    MULTI_LOOKUP(11, "Multi Select"),
    DOCUMENT_TYPE_ITEM_LOOKUP(12, "Item of Type", false, false),
    URL_STRING(13, "URL"),
    // selects a document type, as opposed to an item of a document type, which is DOCUMENT_TYPE_ITEM_LOOKUP_TYPE
    DOCUMENT_TYPE(14, "Item Type", true),
    PROJECT(15, "Project", true),
    STEPS(16, "Steps"),
    TIME(17, "Time"),
    TEST_RUN_STATUS(18, "Test Run Status", true, false),
    DOCUMENT_TYPE_CATEGORY_ITEM_LOOKUP(19, "Item of Category"),
    TEST_CASE_STATUS(20, "Test Case Status", true, false),
    ACTIONS(21, "Actions"),
    ROLLUP(22, "Rollup", false, true, true, false, false),
	RELATIVE_DATE_RANGE(23, "Relative Date Range"),
    CALCULATED(24, "Calculated", false, true, true, false, false)
    ;

    private FieldDataTypeEnum(Integer id, String label, FieldControlType... fieldControlTypes) {
        this(id, label, false, fieldControlTypes);
    }

    private FieldDataTypeEnum(Integer id, String label, boolean chartable, FieldControlType... fieldControlTypes) {
        this(id, label, chartable, false, false, true, true, fieldControlTypes);
    }

    private FieldDataTypeEnum(Integer id, String label, boolean chartable, boolean allowSynchronize, FieldControlType... fieldControlTypes) {
        this(id, label, chartable, false, false, true, allowSynchronize, fieldControlTypes);
    }

    private FieldDataTypeEnum(Integer id, String label, boolean chartable, boolean forceReadOnly, boolean forceNotRequired, boolean allowTriggerSuspect, boolean allowSynchronize, FieldControlType... validControlTypes) {
        this.id = id;
        this.label = label;
        this.chartable = chartable;
        this.validControlTypes = Arrays.asList(validControlTypes);
        this.forceReadOnly = forceReadOnly;
        this.forceNotRequired = forceNotRequired;
        this.allowTriggerSuspect = allowTriggerSuspect;
        this.allowSynchronize = allowSynchronize;
    }

    private final Integer id;
    private final String label;
    private final List<FieldControlType> validControlTypes;

    private final boolean chartable,
                          forceReadOnly,
                          forceNotRequired,
                          allowTriggerSuspect,
                          allowSynchronize;

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isChartable() {
        return chartable;
    }

    public List<FieldControlType> getValidControlTypes() {
        return validControlTypes;
    }

    public boolean isForceReadOnly() {
        return forceReadOnly;
    }

    public boolean isForceNotRequired() {
        return forceNotRequired;
    }

    public boolean isAllowTriggerSuspect() {
        return allowTriggerSuspect;
    }

    public boolean isAllowSynchronize() {
        return allowSynchronize;
    }

    public static boolean isFieldDataType(Integer dataTypeId, FieldDataTypeEnum fieldDataType) {
        return fieldDataType == null ? (dataTypeId == null) : fieldDataType.getId().equals(dataTypeId);
    }

    public static final List<FieldDataTypeEnum> CHARTABLE_FIELD_TYPES;
    private static final Map<Integer, FieldDataTypeEnum> FIELD_DATA_TYPE_ID_MAP = new HashMap<>(values().length);
    static {
        List<FieldDataTypeEnum> chartableFieldTypes = new ArrayList<>();
        for(FieldDataTypeEnum fieldDataTypeEnum : values()) {
            FIELD_DATA_TYPE_ID_MAP.put(fieldDataTypeEnum.getId(), fieldDataTypeEnum);
            if(fieldDataTypeEnum.isChartable()) {
                chartableFieldTypes.add(fieldDataTypeEnum);
            }
        }
        CHARTABLE_FIELD_TYPES = chartableFieldTypes;
    }

    public static FieldDataTypeEnum getDataTypeForId(Integer id) {
        return FIELD_DATA_TYPE_ID_MAP.get(id);
    }

    private static final List<Integer> PICK_LIST_TYPES = Arrays.asList(LOOKUP.getId(), MULTI_LOOKUP.getId(), RELEASE.getId());
    public static boolean isPicklistType(Integer id) {
        return PICK_LIST_TYPES.contains(id);
    }
}

