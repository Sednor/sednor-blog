package com.webapp.config;

import com.webapp.dao.*;
import com.webapp.dao.impl.UserDaoImpl;
import com.webapp.dao.impl.ActivityDaoImpl;
import com.webapp.dao.impl.CategoryDaoImpl;
import com.webapp.dao.impl.PostDaoImpl;
import com.webapp.dao.impl.PostsTagsDaoImpl;
import com.webapp.dao.impl.TagDaoImpl;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by sednor-5 on 3/22/17.
 */

@Configuration
@ComponentScan
public class UserConfig {


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sednor_blog_db");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }


    @Bean
    ActivityDao getActivityDao() {
        return new ActivityDaoImpl();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public CategoryDao getCategoryDao() {
        return new CategoryDaoImpl();
    }

    @Bean
    public PostDao getPostDao() {
        return new PostDaoImpl();
    }

    @Bean
    public TagDao getTagDao(){
        return new TagDaoImpl();
    }

    @Bean
    public PostsTagsDao gePostsTagsDao(){
        return new PostsTagsDaoImpl();
    }

}
