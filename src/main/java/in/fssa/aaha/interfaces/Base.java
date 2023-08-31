package in.fssa.aaha.interfaces;

import java.util.List;

import in.fssa.aaha.model.Product;

public interface Base<T> {

	public abstract List<T> ListAllProducts();

	public abstract void delete(int id) throws Exception;

	public abstract void update(int id, T newT) throws Exception;

	

}