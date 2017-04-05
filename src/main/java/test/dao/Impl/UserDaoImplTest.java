package test.dao.Impl;

import com.webapp.config.UserConfig;
import com.webapp.dao.UserDao;
import com.webapp.entity.Tag;
import com.webapp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sednor-5 on 3/30/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserConfig.class})
public class UserDaoImplTest {


    @Autowired
    UserDao userDao;

    @Test
    public void testSave(){
        User testUser = new User();
        testUser.setUserName("testUser");
        testUser.setPassword("123");
        User user = userDao.save(testUser);
        Assert.assertNotNull(user);
        Assert.assertEquals(testUser.getUserName(),user.getUserName());
        userDao.delete(user.getId());
    }

    @Test
    public void testFindAll() {
        List<User> tags = userDao.findAll();
        Assert.assertNotNull(tags);
        //Assert.assertEquals(2, tags.size());
    }
    @Test
    public void testDelete(){
        User testUser = new User();
        testUser.setUserName("testUser");
        testUser.setPassword("123");
        User user = userDao.save(testUser);
        Long userId = user.getId();
        userDao.delete(userId);
        Assert.assertEquals(false, userDao.findOne(userId).isStatus());
    }

    @Test
    public void testFindOne() {

        Tag testTag = new Tag();
        User userTest = new User();
        userTest.setPassword("123");
        userTest.setUserName("userTest");
        Long idUser = userDao.save(userTest).getId();
        User user = userDao.findOne(idUser);
        Assert.assertNotNull(user);
        Assert.assertEquals("userTest", user.getUserName());
        userDao.delete(idUser);
    }

    @Test
    public void testFindAllActiveUsers(){

        List<User> users = userDao.findAllActiveUsers();
        Assert.assertNotNull(users);
        Assert.assertEquals(true,users.get(0).isStatus());

    }
    @Test
    public void testUpdate(){

        User testUser = new User();
        testUser.setPassword("1111");
        testUser.setUserName("testUpdateUser");
        User user = userDao.save(testUser);
        Long userId = user.getId();
        user.setUserName("newName");
        userDao.update(user);
        Assert.assertEquals("newName",userDao.findOne(userId).getUserName());
        userDao.delete(userId);

    }



}
