package test.dao.Impl;

import com.webapp.dao.impl.ActivityDaoImpl;
import com.webapp.dao.rowMappers.ActivityRowMapper;
import com.webapp.entity.Activity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 4/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ActivityDaoImplTest {

    @InjectMocks
    ActivityDaoImpl activityDao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    ActivityRowMapper activityRowMapper;



    private Activity activity;
    private List<Activity> activities;

    @Before
    public void doSetUp(){
        LocalDateTime date = LocalDateTime.now();
        activity = new Activity();
        activity.setId(1L);
        activity.setUserId(1L);
        activity.setDate(date);
        activity.setStatus("test");

        activities = new ArrayList<>();
        activities.add(activity);
        activityRowMapper = new ActivityRowMapper();

    }

    @Test
    public void testFindAll(){
        String sql = "SELECT * FROM activity";
        when(jdbcTemplate.query(sql, activityRowMapper)).thenReturn(activities);
        Assert.assertNotNull(activityDao.findAll());
    }

    @Test
    public void testFindOne(){
        String sql = "SELECT * FROM activity WHERE user_id=";
        when(jdbcTemplate.query("SELECT * FROM activity WHERE user_id=1L" , activityRowMapper)).thenReturn(activities);
        Assert.assertNotNull(activityDao.findOne(1L));
    }
    @Test
    public void testSave(){

        String sql = "INSERT INTO activity (status, date, user_id) VALUES (?,?,?);";
        activityDao.save(activity);
        verify(jdbcTemplate).update(sql, activity.getStatus(), Timestamp.valueOf(activity.getDate()), activity.getUserId());
    }


}
