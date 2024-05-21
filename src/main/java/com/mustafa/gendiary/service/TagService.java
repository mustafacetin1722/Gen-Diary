package com.mustafa.gendiary.service;

import com.mustafa.gendiary.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> getAllTag();
    TagDto getTagById(Long tagId);
    String createTag(TagDto tagDto);
    String updateTag(TagDto tagDto);
    String deleteTag(Long id);
}
