package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFilterAndStatsService;

@Service
public class FilterStatsProductServiceImpl implements IProductFilterAndStatsService {

    private final CRUDProductServiceImpl CRUDProductServiceImpl;

	@Autowired
	private IProductRepo prodRepo;

    FilterStatsProductServiceImpl(CRUDProductServiceImpl CRUDProductServiceImpl) {
        this.CRUDProductServiceImpl = CRUDProductServiceImpl;
    }
	
	@Override
	public ArrayList<Product> filterByPriceLessThan(float threshold) throws Exception {
		if(threshold <=0 ){
			throw new Exception("Nebūs");
		}if(prodRepo.count() == 0) {
			throw new Exception("Nu uh");
		}
		ArrayList<Product> filteredProducts = prodRepo.findByPriceLessThan(threshold);
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float calculateAVGPrice() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
