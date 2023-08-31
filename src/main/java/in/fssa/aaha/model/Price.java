package in.fssa.aaha.model;

import java.sql.Timestamp;

public class Price {

	private int id;
	private int price;
	private int product_id;
	private Timestamp start_date;
	private Timestamp end_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	
	
	@Override
	public String toString() {
		return "ProductPrice [id=" + id + ", price=" + price + ", product_id=" + product_id + ", start_date="
				+ start_date + ", end_date=" + end_date + "]";
	}
	
	int compareTo(Price o) {

		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() > o.getId()) {
				return 1;
			} else {
				return -1;
			}
//			 return ( this.getProduct_id() > o.getProduct_id()) ? 1: -1;
		}
	}
}
