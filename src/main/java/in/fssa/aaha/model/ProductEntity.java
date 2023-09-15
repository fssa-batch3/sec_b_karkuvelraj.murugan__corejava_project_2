package in.fssa.aaha.model;

public abstract class ProductEntity implements Comparable<Product> {

	private int id;
	private String name;
	private String description;
	private String size;
	private String image;
	private boolean isActive = true;
	private int category_id;
	private Price price = new Price();
	

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
		
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String  getSize() {
		return size;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String  getImage() {
		return image;
	}


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
		return "ProductEntity [id=" + id + ", name=" + name + ", description="
				+ description + ", isActive=" + isActive + ", category_id=" + category_id + ",Price+" + price+ "]";
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}
}