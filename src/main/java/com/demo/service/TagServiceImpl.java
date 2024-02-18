package com.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.TagDao;
import com.demo.models.Recipe;
import com.demo.models.Tag;


@Service
public class TagServiceImpl implements TagService {

	@Autowired
	 private TagDao tdao;

	public List<Tag> getAllTags() {
		return tdao.findAll();
	}

	@Override
	public Boolean deleteTagById(int tid) {
		if (tdao.existsById(tid)) {
			tdao.deleteById(tid);
			return true;
		}
		return false;	
	}

	@Override
	public Boolean updateTagById(Tag t) {
		Optional<Tag> op=tdao.findById(t.getTagId());
		if(op.isPresent()) {
			Tag t1=op.get();
			t1.setTagName(t.getTagName());
			t1.setRecipeTag(t.getRecipeTag());
			if(tdao.save(t1)!=null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean addNewTag(Tag t) {
		try {
	        tdao.save(t);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}

	@Override
	public Tag getTagById(int tid) {
		Optional<Tag> op=tdao.findById(tid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}

	@Override
	public Set<Recipe> getRecipesByTag(int tagId) {
		Tag tag = tdao.findById(tagId).orElse(null);
	    if (tag != null) {
	        return tag.getRecipeTag();
	    } else {
	        return null;
	    }
	}
}