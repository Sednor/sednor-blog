package test.service;

import com.webapp.dao.PostsTagsDao;
import com.webapp.dto.PostsTagsDto;
import com.webapp.entity.Post;
import com.webapp.entity.PostsTags;
import com.webapp.entity.Tag;
import com.webapp.service.Impl.PostsTagsServiceImpl;
import com.webapp.service.Interfaces.PostService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 4/3/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class PostsTagsServiceImplTest {

    @InjectMocks
    PostsTagsServiceImpl postsTagsService;

    @Mock
    PostsTagsDao postsTagsDao;

    private PostsTags postsTags;
    private List<Post> posts;
    private List<Tag> tags;

    @Before
    public void doSetUp(){

        postsTags = new PostsTags();
        postsTags.setTagsId(1L);
        postsTags.setPostId(1L);

        Post post = new Post();
        posts = new ArrayList<>();
        posts.add(post);

        Tag tag = new Tag();
        tags = new ArrayList<>();
        tags.add(tag);

    }

    @Test
    public void testSave() {
        postsTagsService.save(postsTags);
        verify(postsTagsDao).save(postsTags);
    }

    @Test
    public void testDelete(){
        postsTagsService.delete(1L);
        verify(postsTagsDao).delete(1L);
    }

    @Test
    public void testDeleteWithPostId(){
        postsTagsService.deleteWithPostId(1L);
        verify(postsTagsDao).deleteWithPostId(1L);
    }
    @Test
    public void testDeleteWithTagId(){
        postsTagsService.deleteWithTagId(1L);
        verify(postsTagsDao).deleteWithTagId(1L);
    }
    @Test
    public void testGetPosts(){
        when(postsTagsDao.getPosts(1L)).thenReturn(posts);
        Assert.assertNotNull(postsTagsService.getPosts(1L));
    }

    @Test
    public void testGetTags(){
        when(postsTagsDao.getTags(1L)).thenReturn(tags);
        Assert.assertNotNull(postsTagsService.getTags(1L));
    }


}
