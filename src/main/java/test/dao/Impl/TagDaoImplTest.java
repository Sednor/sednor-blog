package test.dao.Impl;

import com.webapp.config.UserConfig;
import com.webapp.dao.TagDao;
import com.webapp.entity.Tag;
import com.webapp.service.Interfaces.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sednor-5 on 3/30/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserConfig.class})
public class TagDaoImplTest {

    @Autowired
    TagDao tagDao;


    @Test
    public void testFindOne() {

        Tag testTag = new Tag();
        testTag.setName("humster");
        Long idHumster = tagDao.save(testTag).getId();

        Tag tag = tagDao.findOne(idHumster);
        Assert.assertNotNull(tag);
        Assert.assertEquals("humster", tag.getName());
        tagDao.delete(idHumster);
    }

    @Test
    public void testFindAll() {
        List<Tag> tags = tagDao.findAll();
        Assert.assertNotNull(tags);
        Assert.assertEquals(4, tags.size());

    }

    @Test
    public void testSave(){
        Tag testTag = new Tag();
        testTag.setName("hamster");
        Tag tag = tagDao.save(testTag);
        Assert.assertNotNull(tag);
        Assert.assertEquals(testTag.getName(),tag.getName());
        tagDao.delete(tag.getId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete(){
        Tag testTag = new Tag();
        testTag.setName("bull");
        Tag tag = tagDao.save(testTag);
        tagDao.delete(tag.getId());
        tagDao.findOne(tag.getId());
    }

    @Test
    public void testUpdate(){
        Tag testTag = new Tag();
        testTag.setName("bull");
        Tag tag = tagDao.save(testTag);
        Long bullId = tag.getId();
        tag.setName("cow");
        tagDao.update(tag);
        Assert.assertEquals("cow",tagDao.findOne(bullId).getName());
        tagDao.delete(bullId);
    }





}
