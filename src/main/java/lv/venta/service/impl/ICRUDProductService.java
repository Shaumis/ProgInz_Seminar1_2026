package lv.venta.service.impl;

import java.util.ArrayList;

import lv.venta.model.Category;
import lv.venta.model.Product;

public interface ICRUDProductService {

	public abstract Product createNewProduct(Product newProduct)throws Exception;
	
	public abstract ArrayList<Product> retrieveAllProducts() throws Exception;
	
	public abstract Product retrieveProductbyId(int id) throws Exception;
	
	public abstract Product updateProductById(int id, float price, Category category, String description,int quantity)throws Exception;
	
	public abstract void deleteProduct(int id) throws Exception;
	
}
