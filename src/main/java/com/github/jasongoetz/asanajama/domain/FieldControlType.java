package com.github.jasongoetz.asanajama.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FieldControlType {

    TEXTAREA(2, "Text Box"),
    RICHTEXT(3, "Rich Text"),
    KEY(4, "Key"),
    ATTACHMENT(7, "File Attachment");

    private FieldControlType(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    private final Integer id;
    private final String label;

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

	private static final List<LookupPropertyDTO> FIELD_CONTROL_LIST = Arrays.asList(
            new LookupPropertyDTO(TEXTAREA.getId(), TEXTAREA.getLabel(), null, false),
            new LookupPropertyDTO(RICHTEXT.getId(), RICHTEXT.getLabel(), null, false)
    );

	public static List<LookupPropertyDTO> getFieldControlList() {
		return FIELD_CONTROL_LIST;
	}

	public static boolean isControlType(Integer controlTypeId, FieldControlType controlType) {
		return controlType == null ? (controlTypeId == null) : controlType.getId().equals(controlTypeId);
	}

    private static final Map<Integer, FieldControlType> FIELD_CONTROL_TYPE_ID_MAP = new HashMap<>(values().length);
    static {
        for(FieldControlType fieldControlTypeEnum : values()) {
            FIELD_CONTROL_TYPE_ID_MAP.put(fieldControlTypeEnum.getId(), fieldControlTypeEnum);
        }
    }

    public static FieldControlType getControlTypeForId(Integer id) {
        return FIELD_CONTROL_TYPE_ID_MAP.get(id);
    }
}
