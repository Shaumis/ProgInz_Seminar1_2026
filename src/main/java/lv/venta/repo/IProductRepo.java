package lv.venta.repo;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Category;
import lv.venta.model.Product;
public interface IProductRepo extends CrudRepository<Product, Integer>{

	//te automatiski izveidosies vaicājums: SELECT * FROM PRODUCT_TABLE WHERE TITLE=title, bet atgriezīs true, ja būs kādi dati
	public abstract boolean existsByTitle(String title);

	
	//SELECT * FROM product_table WHERE category = <padota kategorija>;
	public abstract ArrayList<Product> findByCategory(Category category);

	//SELECT * FROM product_table WHERE  price < <padotais slieknis>;
	public abstract ArrayList<Product> findByPriceLessThan(float threshold);

	//SELECT * FROM product_table WHERE title LIKE '%<keyword>%' OR description LIKE '%<keyword>%';
	public abstract ArrayList<Product> findByTitleContainingOrDescriptionContaining(String keyword, String keyword2);

	@Query(nativeQuery = true, value = "SELECT AVG(price) FROM product_table;")
	public abstract float calculateAVGPriceFromDB();

}
