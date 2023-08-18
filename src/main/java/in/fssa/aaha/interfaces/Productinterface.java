package in.fssa.aaha.interfaces;
import java.util.Set;

import in.fssa.aaha.model.Product;

import java.util.Set;

public interface Productinterface {
    Set<Product> listAllProducts();
    Set<Product> listAllProductsByCategoryId(int category_id);
    Product findProductDetailsByProductId(int productId);
    void updateProduct(int productId, String name, int categoryId);
}