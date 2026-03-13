package lv.venta.service.impl;

import java.util.ArrayList;
import lv.venta.repo.IProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Category;
import lv.venta.model.Product;

@Service
public class CRUDProductServiceImpl implements ICRUDProductService{

    private final IProductRepo IProductRepo;

    CRUDProductServiceImpl(IProductRepo IProductRepo) {
        this.IProductRepo = IProductRepo;
    }
    
    @Autowired
    private IProductRepo prodRepo;

	@Override
	public Product createNewProduct(Product newProduct) throws Exception {
		if(newProduct == null) {
			throw new Exception("yayayaya");	
		}
		if(prodRepo.existsByTitle(newProduct.getTitle())) {
			throw new Exception("NU UHH");
			
		}
		
		if(newProduct.getTitle() == null || newProduct.getTitle().isEmpty() || newProduct.getCategory() == null || newProduct.getPrice() <= 0 || newProduct.getPrice() > 10000 || newProduct.getDescription() == null || newProduct.getQuantity()<0 || newProduct.getQuantity() > 100000){
		throw new Exception("NO");	
		}
		
		return null;
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() throws Exception {
		if(prodRepo.count()==0) {
			throw new Exception("Lmao");
		}
		return (ArrayList<Product>) prodRepo.findAll();
	}

	@Override
	public Product retrieveProductbyId(int id) throws Exception {
		if(id < 1) {
			throw new Exception("Nope");
		}
		if(!prodRepo.existsById(id)) {
			throw new Exception("Ha no");
		}
		return prodRepo.findById(id).get();
	}

	@Override
	public Product updateProductById(int id, float price, Category category, String description, int quantity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
