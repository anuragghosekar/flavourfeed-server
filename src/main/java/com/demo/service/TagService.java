package com.demo.service;
import java.util.List;
import java.util.Set;
import com.demo.models.Recipe;
import com.demo.models.Tag;

public interface TagService {

	List<Tag> getAllTags();

	Boolean addNewTag(Tag t);

	Tag getTagById(int pid);

	Boolean updateTagById(Tag t);

	Boolean deleteTagById(int tid);

	Set<Recipe> getRecipesByTag(int tagId);

}