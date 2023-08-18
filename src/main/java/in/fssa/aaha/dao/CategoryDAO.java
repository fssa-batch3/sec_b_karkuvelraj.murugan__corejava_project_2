package in.fssa.aaha.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import in.fssa.aaha.interfaces.Categoryinterface;
import in.fssa.aaha.util.ConnectionUtil;
import in.fssa.aaha.model.Category;





	
public class CategoryDAO implements Categoryinterface{
           
	
	 /**
	  * 
	  */
	public void create(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO category (name) values(?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getName());
			
			ps.executeUpdate();
			
			System.out.println("Category created Successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	public Set<Category> listAllCategroyByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateName(int id, String categoryName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
