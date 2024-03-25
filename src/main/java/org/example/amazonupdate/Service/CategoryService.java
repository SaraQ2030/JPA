package org.example.amazonupdate.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.amazonupdate.Model.Category;
import org.example.amazonupdate.Model.Merchant;
import org.example.amazonupdate.Repository.CategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private  final CategoryRepository categoryRepository;
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public boolean updateCategory(int id, Category category){
        Category c=categoryRepository.getReferenceById(id);
        if (categoryRepository.existsById(id)){
            c.setName(category.getName());
            return true;
        }
        return false;
    }
    public boolean deleteCategory(int id){
        Category c=categoryRepository.getReferenceById(id);
        if (categoryRepository.existsById(id)){
            categoryRepository.delete(c);
            return true;
        }
        return false;
    }
}
