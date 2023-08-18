package in.fssa.aaha.model;

public abstract class ProductEntity implements Comparable<Product> {

	private int id;
	private String name;
	private boolean isActive = true;
	private String description;
	private int category_id;


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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", description=" +description + ", isActive=" + isActive + ", category_id=" + category_id + "]";
	}
	@Override
	public int compareTo(Product o) {
		
		return 0;
	}
}