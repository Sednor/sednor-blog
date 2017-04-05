package com.webapp.service.Interfaces;

import com.webapp.dto.TagDto;

import java.util.List;

/**
 * Created by sednor-5 on 3/28/17.
 */
public interface TagService {

    TagDto save(TagDto tagDto);
    List<TagDto> findAll();
    void delete(Long id);
    void update(TagDto tagDto);
    TagDto findOne(Long id);

}
