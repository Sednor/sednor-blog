package test.service;

import com.webapp.dao.ActivityDao;
import com.webapp.dto.ActivityDto;
import com.webapp.entity.Activity;
import com.webapp.service.Impl.ActivityServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sednor-5 on 4/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceImplTest {

    @InjectMocks
    ActivityServiceImpl activityService;

    @Mock
    ActivityDao activityDao;

    private Activity activity;
    private ActivityDto activityDto;
    private List<Activity> activityList;

    @Before
    public void doSetUp() {
        LocalDateTime date = LocalDateTime.now();

        activityDto = new ActivityDto();
        activityDto.setDate(date);
        activityDto.setStatus("test");
        activityDto.setUserId(1L);
        activityDto.setId(1L);

        activity = new Activity();
        activity.setDate(date);
        activity.setStatus("test");
        activity.setUserId(1L);
        activity.setId(1L);

        activityList = new ArrayList<>();
        activityList.add(activity);
    }

//    @Test
//    public void testSave() {
//        activity.setId(null);
//        activityService.save(activityDto);
//        verify(activityDao).save(activity);
//    }

    @Test
    public void testFindAll() {
        when(activityDao.findAll()).thenReturn(activityList);
        Assert.assertNotNull(activityService.findAll());
    }

    @Test
    public void findOne() {
        when(activityDao.findOne(1L)).thenReturn(activityList);
        Assert.assertNotNull(activityService.findOne(1L));
        verify(activityDao).findOne(1L);
    }


}
