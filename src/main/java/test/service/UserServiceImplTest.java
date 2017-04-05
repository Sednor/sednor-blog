package test.service;

import com.webapp.dao.UserDao;
import com.webapp.dto.UserDto;
import com.webapp.entity.User;
import com.webapp.service.Impl.UserServiseImpl;
import com.webapp.service.Interfaces.ActivityService;
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
 * Created by sednor-5 on 3/31/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiseImpl userServise;

    @Mock
    UserDao userDao;
    @Mock
    ActivityService activityService;
    @Mock
    PostService postService;

    private User user;
    private UserDto userDto;
    private List<User> users;

    @Before
    public void doSetUp(){

        user = new User();
        user.setId(1L);
        user.setUserName("testUser");
        user.setPassword("111");
        user.setStatus(true);

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUserName("testUserDto");
        userDto.setPassword("111");
        user.setStatus(true);

        users = new ArrayList<>();
        users.add(user);

    }

    @Test
    public void testSave() {
        when(userDao.save(any(User.class))).thenReturn(user);
        Assert.assertNotNull(userServise.save(userDto));
    }

    @Test
    public void testFindOne(){

        when(userDao.findOne(1L)).thenReturn(user);
        Assert.assertNotNull(userServise.findOne(1L));
    }
    @Test
    public void testFindAll(){

        when(userDao.findAll()).thenReturn(users);
        Assert.assertNotNull(userServise.findAll());
    }

    @Test
    public void testFindAllActiveUsers(){
        when(userDao.findAllActiveUsers()).thenReturn(users);
        Assert.assertNotNull(userServise.findAll());
    }

    @Test
    public void testUpdate(){
        when(userDao.findOne(1L)).thenReturn(user);
        userServise.update(userDto);
        verify(userDao, times(1)).update(user);
    }
    @Test
    public void testDelete() {
        when(userDao.findOne(1L)).thenReturn(user);
        userServise.delete(1L);
        verify(userDao).delete(1L);
    }



}
