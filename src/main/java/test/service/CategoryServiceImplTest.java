package test.service;

import com.webapp.dao.CategoryDao;
import com.webapp.dto.CategoryDto;
import com.webapp.entity.Category;
import com.webapp.service.Impl.CategoryServiceImpl;
import com.webapp.service.Impl.PostServiceImpl;
import com.webapp.service.Interfaces.PostsTagsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by sednor-5 on 3/31/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryService;
    @Mock
    CategoryDao categoryDao;
    @Mock
    private PostServiceImpl postsService;

    private Category category;
    private CategoryDto categoryDto;
    private List<Category> categories;


    @Before
    public void doSetUp(){
        category = new Category();
        //category.setId(1L);
        category.setName("testCategory");
        category.setUserId(1L);
        category.setStatus(true);

        categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("testCategory");
        categoryDto.setUserId(1L);
        category.setStatus(true);

        categories = new ArrayList<>();
        categories.add(category);


    }

//    @Test
//    public void testSave(){
//        //when(categoryDao.save(any(Category.class))).thenReturn(category);
//        categoryService.save(categoryDto);
//        verify(categoryDao).save(category);
//    }
    @Test
    public void testFindAll(){

        when(categoryDao.findAll()).thenReturn(categories);
        Assert.assertNotNull(categoryService.findAll());
    }
    @Test
    public void testFindOne(){
        when(categoryDao.findOne(1L)).thenReturn(category);
        Assert.assertNotNull(categoryService.findOne(1L));
    }

    @Test
    public void testDelete() {
        when(categoryDao.findOne(1L)).thenReturn(category);
        categoryService.delete(1L);
        verify(categoryDao).delete(1L);
    }

    @Test
    public void testFindAllActive(){
        when(categoryDao.findAll()).thenReturn(categories);
        Assert.assertNotNull(categoryService.findAll());
    }



}
