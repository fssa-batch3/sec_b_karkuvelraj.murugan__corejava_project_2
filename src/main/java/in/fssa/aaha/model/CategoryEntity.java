package in.fssa.aaha.model;

public abstract class CategoryEntity implements Comparable<Category> {

	private int id;
	private String name;
	private boolean isActive = true;
	private int category_type_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCategory_type_id() {
		return category_type_id;
	}

	public void setCategory_type_id(int category_type_id) {
		this.category_type_id = category_type_id;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", isActive=" + isActive + ", category_type_id="
				+ category_type_id + "]";
	}

	public int compareTo(Category o) {
		if (this.id == o.getId()) {
			return 0;
		} else if (this.id > o.getId()) {
			return 1;
		} else {
			return -1;
		}
	}

}