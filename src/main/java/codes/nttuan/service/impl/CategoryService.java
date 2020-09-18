package codes.nttuan.service.impl;

import codes.nttuan.dao.ICategoryDAO;
import codes.nttuan.dao.impl.CategoryDAO;
import codes.nttuan.models.CategoryModel;
import codes.nttuan.service.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    //@Inject
    private ICategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }

}
