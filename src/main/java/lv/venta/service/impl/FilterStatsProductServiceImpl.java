package lv.venta.service.impl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Category;
import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFilterAndStatsService;

@Service
public class FilterStatsProductServiceImpl implements IProductFilterAndStatsService {

	@Autowired
	private IProductRepo prodRepo;

	@Override
	public ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception {
		if(threshold <= 0) {
			throw new Exception("Ievadītais cenas slieksnis nav korekts");
		}
		
		if(prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}
		
		ArrayList<Product> filteredProducts = prodRepo.findByPriceLessThan(threshold);
		if(filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts, kura cena ir mazāka par "+threshold+ " eur");
		}
		return filteredProducts;
		
	}

	@Override
	public ArrayList<Product> filterByCategory(Category category) throws Exception {
		if(category == null) {
			throw new Exception("Ievades dati nav korekti");
		}
		
		if(prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}
	
		ArrayList<Product> filteredProducts = prodRepo.findByCategory(category);
		
		if(filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts " + category + " kategorijā");
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByKeyword(String keyword) throws Exception {
		if(keyword==null || keyword.isEmpty()) {
			throw new Exception("Ievades dati nav korekti");
		}
		
		if(prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}
		ArrayList<Product> filteredProducts = 
				prodRepo.findByTitleContainingOrDescriptionContaining(keyword,keyword);
		
		if(filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts, kura nosaukums vai apraksts satur" + keyword);
		}
		
		return filteredProducts;
	}

	@Override
	public float calculateAVGPrice() throws Exception {
		if(prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar aprēķināt");
		}
		
		float avgPrice = prodRepo.calculateAVGPriceFromDB();
		
		return avgPrice;
	}

}
