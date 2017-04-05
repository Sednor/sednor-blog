package test.service;

import com.webapp.dao.TagDao;
import com.webapp.dto.TagDto;
import com.webapp.entity.PostsTags;
import com.webapp.entity.Tag;
import com.webapp.entity.User;
import com.webapp.service.Impl.TagServiceImpl;
import com.webapp.service.Interfaces.PostsTagsService;
import com.webapp.service.Interfaces.TagService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by sednor-5 on 3/31/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {

    @InjectMocks
    private TagServiceImpl tagService;

    @Mock
    private TagDao tagDao;

    @Mock
    private PostsTagsService postsTagsService;

    private Tag tag;
    private TagDto tagDto;
    private List<Tag> tags;



    @Before
    public void doSetup() {

        tag = new Tag();
        tag.setId(1L);
        tag.setName("testTag");

        tagDto = new TagDto();
        tagDto.setId(1L);
        tagDto.setName("tagDto");

        tags = new ArrayList<>();
        tags.add(tag);

    }
    @After
    public void tearDown() {

    }

    @Test
    public void testSave() {
        when(tagDao.save(any(Tag.class))).thenReturn(tag);
        Assert.assertNotNull(tagService.save(tagDto));
    }


//    @Test(expected = EmptyResultDataAccessException.class)
//    public void delete() {
//        when(tagDao.findOne(1L)).thenReturn(tag);
//        tagService.delete(1L);
//        tagDao.findOne(1L);
//
//    }
//    @Test
//    public void delete() {
//        when(tagDao.findOne(1L)).thenReturn(tag);
//        tagService.delete(1L);
//        tagDao.findOne(1L);
//
//    }

    @Test
    public void testFindAll(){

        when(tagDao.findAll()).thenReturn(tags);
        Assert.assertNotNull(tagService.findAll());
    }
    @Test
    public void testFindOne(){
        when(tagDao.findOne(1L)).thenReturn(tag);
        Assert.assertNotNull(tagService.findOne(1L));
    }
    @Test
    public void testUpdate(){
        when(tagDao.findOne(1L)).thenReturn(tag);
        tagService.update(tagDto);
        verify(tagDao, times(1)).update(tag);
    }
    @Test
    public void testDelete() {
        when(tagDao.findOne(1L)).thenReturn(tag);
        tagService.delete(1L);
        verify(tagDao).delete(1L);
    }





}
