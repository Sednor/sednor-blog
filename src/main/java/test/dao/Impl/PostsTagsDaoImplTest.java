package test.dao.Impl;

import com.webapp.dao.impl.PostsTagsDaoImpl;
import com.webapp.dao.rowMappers.PostRowMapper;
import com.webapp.dao.rowMappers.TagRowMapper;
import com.webapp.entity.Post;
import com.webapp.entity.PostsTags;
import com.webapp.entity.Tag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 4/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class PostsTagsDaoImplTest {

    @InjectMocks
    private PostsTagsDaoImpl postsTagsDao;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private PostRowMapper postRowMapper;
    @Mock
    private TagRowMapper tagRowMapper;

    @Mock
    KeyHolder keyHolder;

    private PostsTags postsTags;
    private List<Post> postList;
    private List<Tag> tagList;

    @Before
    public void doSetUp() {
        postsTags = new PostsTags();
        postsTags.setId(1L);
        postsTags.setTagsId(1L);
        postsTags.setPostId(1L);

        Post post = new Post();
        Tag tag = new Tag();

        postList = new ArrayList<>();
        postList.add(post);

        tagList = new ArrayList<>();
        tagList.add(tag);

    }

    @Test
    public void testDelete() {
        String sql = "DELETE FROM posts_tags WHERE id=?";
        Long val = 1L;
        postsTagsDao.delete(val);
        verify(jdbcTemplate).update(sql, val);
    }

    @Test
    public void testDeleteWithPostId() {
        String sql = "DELETE FROM posts_tags WHERE post_id=?";
        Long val = 1L;
        postsTagsDao.deleteWithPostId(val);
        verify(jdbcTemplate).update(sql, val);
    }

    @Test
    public void testDeleteWithTagId() {
        String sql = "DELETE FROM posts_tags WHERE tags_id=?";
        Long val = 1L;
        postsTagsDao.deleteWithTagId(val);
        verify(jdbcTemplate).update(sql, val);
    }

    /*@Test
    public void testSave() {
        String sql = "INSERT INTO posts_tags(tags_id,post_id) VALUES(?,?)";
       when(keyHolder.getKey()).thenReturn(((Number) 1L));
//        Mockito.when(jdbcTemplate.update(Mockito.any(PreparedStatementCreator.class), Mockito.any(KeyHolder.class))).thenReturn(1);
        postsTagsDao.save(postsTags);
        verify(jdbcTemplate).update(sql, postsTags.getTagsId(), postsTags.getPostId());
    }*/

    @Test
    public void testGetPosts() {

        String sql = "SELECT post.id, post.title, post.description, post.category_id, post.user_id" +
                " FROM post INNER JOIN posts_tags ON post.id=posts_tags.post_id INNER JOIN " +
                "tag ON posts_tags.tags_id=tag.id WHERE tag.id=?";
        when(jdbcTemplate.query(sql, new Object[]{1L}, postRowMapper)).thenReturn(postList);
        Assert.assertNotNull(postsTagsDao.getPosts(1L));

    }
    @Test
    public void testGetTags() {

        String sql = "SELECT tag.id, tag.name FROM " +
                "tag INNER JOIN posts_tags ON tags.id=posts_tags.tags_id INNER JOIN " +
                "post ON posts_tags.post_id=post.id WHERE post.id=?";
        when(jdbcTemplate.query(sql, new Object[]{1L}, tagRowMapper)).thenReturn(tagList);
        Assert.assertNotNull(postsTagsDao.getTags(1L));

    }



}
