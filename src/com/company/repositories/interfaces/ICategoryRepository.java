package com.company.repositories.interfaces;

import com.company.models.Category;
import java.util.List;

public interface ICategoryRepository {
    boolean createCategory(String name);
    List<Category> getAllCategories();
}