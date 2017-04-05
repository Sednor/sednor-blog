package test.dao.Impl;

import com.webapp.config.UserConfig;
import com.webapp.dao.CategoryDao;
import com.webapp.dao.impl.CategoryDaoImpl;
import com.webapp.dao.rowMappers.CategoryRowMapper;
import com.webapp.entity.Category;
import com.webapp.entity.Tag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 3/30/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryDaoImplTest {

    @InjectMocks
    private CategoryDaoImpl categoryDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private CategoryRowMapper categoryRowMapper;

    private Category category;

    private List<Category> categories;


    @Before
    public void doSetUp() {

        category = new Category();
        category.setId(1L);
        category.setUserId(1L);
        category.setStatus(true);
        category.setName("test");

        categories = new ArrayList<>();
        categories.add(category);

    }

    @Test
    public void testFindAll() {
        String sql = "SELECT * FROM category";
        when(jdbcTemplate.query(sql, categoryRowMapper)).thenReturn(categories);
        Assert.assertNotNull(categoryDao.findAll());
    }

    @Test
    public void testFindAllActive() {
        String sql = "SELECT * FROM category WHERE status=true";
        when(jdbcTemplate.query(sql, categoryRowMapper)).thenReturn(categories);
        Assert.assertNotNull(categoryDao.findAllActive());
    }

//    @Test
//    public void testFindOne() {
//        String sql = "SELECT * FROM category WHERE id=?";
//        when(jdbcTemplate.queryForObject(sql, new Object[]{1L}, categoryRowMapper)).thenReturn(category);
//        Category newCategory = categoryDao.findOne(1L);
//        Assert.assertNotNull(newCategory);
//
//    }

    @Test
    public void testSave() {
        String sql = "INSERT INTO category (name, user_id, status) VALUES (?,?,?)";
        categoryDao.save(category);
        verify(jdbcTemplate).update(sql, category.getName(),category.getUserId(),category.getStatus());
    }
    @Test
    public void testDelete(){
        String sql = "UPDATE category SET status=FALSE WHERE id=?";
        categoryDao.delete(1L);
        verify(jdbcTemplate).update(sql, 1L);
    }
}
