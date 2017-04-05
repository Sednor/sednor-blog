package test.service;

import com.webapp.dao.PostDao;
import com.webapp.dao.UserDao;
import com.webapp.dto.PostDto;
import com.webapp.dto.TagDto;
import com.webapp.entity.Post;
import com.webapp.entity.Tag;
import com.webapp.entity.User;
import com.webapp.service.Impl.ActivityServiceImpl;
import com.webapp.service.Impl.PostServiceImpl;
import com.webapp.service.Impl.PostsTagsServiceImpl;
import com.webapp.service.Impl.TagServiceImpl;
import com.webapp.service.Interfaces.PostService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 4/3/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl postService;
    @Mock
    PostDao postDao;

    @Mock
    UserDao userDao;

    @Mock
    ActivityServiceImpl activityService;

    @Mock
    PostsTagsServiceImpl postsTagsService;

    @Mock
    TagServiceImpl tagService;

    private Post post;
    private PostDto postDto;
    private User user;
    private TagDto tag;
    private List<TagDto> tagList;
    private List<Post> posts;

    @Before
    public void doSetUp(){

        tag = new TagDto();
        tag.setId(1L);

        tagList = new ArrayList<>();
        tagList.add(tag);

        post = new Post();
        post.setUserId(1L);
        post.setDescription("test");
        post.setTitle("test");
        post.setCategoryId(1L);
        post.setId(1L);

        posts = new ArrayList<>();
        posts.add(post);

        postDto = new PostDto();
        postDto.setUserId(1L);
        postDto.setDescription("test");
        postDto.setTitle("test");
        postDto.setCategoryId(1L);
        postDto.setId(1L);
        postDto.setTagsList(tagList);

        user = new User();
        user.setStatus(true);
        user.setId(1L);


    }

    @Test
    public void testSave(){
        when(userDao.findOne(1L)).thenReturn(user);
        when(postDao.save(any(Post.class))).thenReturn(post);
        when(tagService.findAll()).thenReturn(tagList);
        Assert.assertNotNull(postService.save(postDto));
    }

    @Test
    public void testFindAll(){
        when(postDao.findAll()).thenReturn(posts);
        Assert.assertNotNull(postService.findAll());
    }

    @Test
    public void testFindOne(){
        when(postDao.findOne(1L)).thenReturn(post);
        Assert.assertNotNull(postService.findOne(1L));
    }

    @Test
    public void testFindUserPost(){
        when(postDao.findUserPosts(1L)).thenReturn(posts);
        Assert.assertNotNull(postService.findUserPosts(1L));
    }

    @Test
    public void testFindCategoryPosts(){
        when(postDao.findCategoryPosts(1L)).thenReturn(posts);
        Assert.assertNotNull(postService.findCategoryPosts(1L));
    }

    @Test
    public void testUpdate(){
        when(postDao.findOne(1L)).thenReturn(post);
        postService.update(postDto);
        verify(postDao, times(1)).update(post);
    }
    @Test
    public void testDelete() {
        when(postDao.findOne(1L)).thenReturn(post);
        postService.delete(1L);
        verify(postDao).delete(1L);
    }
}
