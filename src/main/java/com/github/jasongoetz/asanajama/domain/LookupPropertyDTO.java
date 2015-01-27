package com.github.jasongoetz.asanajama.domain;

public class LookupPropertyDTO {

	private Integer id;

	private String name;

	private boolean isDefault;

	private String color;

	private Integer sortOrder;

	public LookupPropertyDTO() {
		super();
	}

	public LookupPropertyDTO(Integer id) {
		this.id = id;
	}

	public LookupPropertyDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public LookupPropertyDTO(Integer id, String name, String color, boolean isDefault) {
		this(id, name, color, isDefault, 0);
	}

	public LookupPropertyDTO(Integer id, String name, String color, boolean isDefault, Integer sortOrder) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.isDefault = isDefault;
		this.sortOrder = sortOrder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return "LookupPropertyDTO [color=" + color + ", id=" + id + ", isDefault=" + isDefault + ", name=" + name + ", sortOrder=" + sortOrder + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LookupPropertyDTO other = (LookupPropertyDTO) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		}
		else if (!color.equals(other.color))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (isDefault != other.isDefault)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}
}
