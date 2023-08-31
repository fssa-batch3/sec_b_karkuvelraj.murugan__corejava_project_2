package in.fssa.aaha.interfaces;
import in.fssa.aaha.exception.DAOException;
import in.fssa.aaha.exception.ValidationException;
import in.fssa.aaha.model.Product;

public interface ProductInterface extends Base<Product> {

	public abstract int create(Product newUser) throws Exception;

	public abstract Product findById(int id) throws ValidationException, DAOException;

}