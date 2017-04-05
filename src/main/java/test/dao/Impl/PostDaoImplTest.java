package test.dao.Impl;

import com.webapp.dao.impl.PostDaoImpl;
import com.webapp.dao.rowMappers.PostRowMapper;
import com.webapp.entity.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 4/3/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostDaoImplTest {

    @InjectMocks
    private PostDaoImpl postDao;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PostRowMapper postRowMapper;

    private Post post;
    private List<Post> posts;

    @Before
    public void doSetUp(){
        post = new Post();
        post.setId(1L);
        post.setUserId(1L);
        post.setCategoryId(1L);
        post.setTitle("test");
        post.setDescription("test");


        posts = new ArrayList<>();
        posts.add(post);

    }

    @Test
    public void testDelete(){
        String sql = "DELETE FROM post WHERE id=?";
        Long val = 1L;
        postDao.delete(val);
        verify(jdbcTemplate).update(sql, val);
    }

    @Test
    public void testUpdate(){
        String sql = "UPDATE post SET title=?, description=?, category_id=?, user_id=? WHERE id=?";
        postDao.update(post);
        verify(jdbcTemplate).update(sql,post.getTitle(),post.getDescription(),
                post.getCategoryId(),post.getUserId(), post.getId());
    }

    @Test
    public void testFindCategoryPosts(){
        String sql = "SELECT * FROM post WHERE category_id="+post.getCategoryId();
        when(jdbcTemplate.query(sql, postRowMapper)).thenReturn(posts);
        Assert.assertNotNull(postDao.findCategoryPosts(1L));
    }
    @Test
    public void testFindUserPosts(){
        String sql = "SELECT * FROM post WHERE user_id="+ post.getUserId();
        when(jdbcTemplate.query(sql, postRowMapper)).thenReturn(posts);
        Assert.assertNotNull(postDao.findUserPosts(1L));
    }
    @Test
    public void testFindAll(){
        String sql = "SELECT * FROM post";
        when(jdbcTemplate.query(sql, postRowMapper)).thenReturn(posts);
        Assert.assertNotNull(postDao.findAll());
    }
//    @Test
//    public void testSave(){
//
//        String sql = "INSERT INTO post(title,description,category_id,user_id) VALUES(?,?,?,?)";
//        postDao.save(post);
//        verify(jdbcTemplate).update(sql, post.getTitle(),post.getDescription(),post.getCategoryId(),post.getUserId());
//    }
//    @Test
//    public void testFindOne(){
//        String sql = "SELECT * FROM post WHERE id=?";
//        when(jdbcTemplate.queryForObject(sql, new Object[]{1L},postRowMapper)).thenReturn(post);
//        Post newPost = postDao.findOne(1L);
//        Assert.assertNotNull(newPost);
//    }

}
