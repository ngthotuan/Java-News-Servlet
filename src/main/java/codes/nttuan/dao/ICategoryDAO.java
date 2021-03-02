package codes.nttuan.dao;

import codes.nttuan.models.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
    List<CategoryModel> findAll();

}
