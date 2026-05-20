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
		if (prodRepo.count() == 0) {
			throw new Exception("Nu uh");
		}
		if (threshold <= 0) {
			throw new Exception("Nebūs");
		}

		ArrayList<Product> filteredProducts = prodRepo.findByPriceLessThan(threshold);
		if (filteredProducts.isEmpty()) {
			throw new Exception("ne");
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByCategory(Category category) throws Exception {
		if (category == null) {
			throw new Exception("Ievades dati nav korekti");
		}

		if (prodRepo.count() == 0) {
			throw new Exception("DB nav produktu, tāpēc neko nevar filtrēt");
		}

		ArrayList<Product> filteredProducts = prodRepo.findByCategory(category);

		if (filteredProducts.isEmpty()) {
			throw new Exception("Nav neviens produkts " + category + " kategorijā");
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByKeyword(String keyword) throws Exception {
		if (keyword == null || keyword.isEmpty()) {
			throw new Exception("Nu uh");
		}
		if (prodRepo.count() == 0) {
			throw new Exception("Nu uh");
		}
		ArrayList<Product> filteredKeyword = prodRepo
				.findByTitleContainingOrDescriptionContainingOrCategoryContaining(keyword, keyword, keyword);
		if (filteredKeyword.isEmpty()) {
			throw new Exception("lmao");
		}
		return filteredKeyword;
	}

	@Override
	public float calculateAVGPrice() throws Exception {
		if (prodRepo.count() == 0) {
			throw new Exception("Nu uh");
		}
		float avgPrice = prodRepo.calculateAVGPriceFromDB();
		return avgPrice;
	}

}
