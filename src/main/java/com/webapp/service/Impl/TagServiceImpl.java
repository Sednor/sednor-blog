package com.webapp.service.Impl;

import com.webapp.dto.TagDto;
import com.webapp.dao.TagDao;
import com.webapp.entity.Tag;
import com.webapp.service.Interfaces.PostsTagsService;
import com.webapp.service.Interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sednor-5 on 3/28/17.
 */
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagDao tagsDao;

    @Autowired
    PostsTagsService postsTagsService;

    @Override
    public TagDto save(TagDto tagDto) {

        Tag tag = new Tag();
        convert(tagDto, tag);
        tag = tagsDao.save(tag);
        tagDto.setId(tag.getId());
        return tagDto;

    }

    @Override
    public List<TagDto> findAll() {

        List<Tag> list = tagsDao.findAll();
        return list.stream().map(tags -> {
            TagDto tagDto = new TagDto();
            convert(tags, tagDto);
            //
            tagDto.setPosts(postsTagsService.getPosts(tagDto.getId()));
            return tagDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Tag tag = tagsDao.findOne(id);
        if (tag == null) {
            //my exception
            throw new EmptyResultDataAccessException(0);
        }

        postsTagsService.deleteWithTagId(id);
        tagsDao.delete(id);

    }

    @Override
    public void update(TagDto tagDto) {

        Tag tag = tagsDao.findOne(tagDto.getId());
        if (tag == null) {
            return;
        }
        convert(tagDto, tag);
        tagsDao.update(tag);
    }

    @Override
    public TagDto findOne(Long id) {
        TagDto tagDto = new TagDto();
        Tag tag = tagsDao.findOne(id);
        convert(tag, tagDto);
        //
        tagDto.setPosts(postsTagsService.getPosts(tagDto.getId()));
        return tagDto;
    }

    private void convert(TagDto tagDto, Tag tag) {
        //tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
    }

    private void convert(Tag tag, TagDto tagDto) {
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
    }
}
