package com.demo.models;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_tag")
public class Tag {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;
	
	@NotBlank(message = "Tag name cannot be Blank")
    @Column(name = "tag_name")
    private String tagName;
    
    @OneToMany
    private Set<Recipe> RecipeTag;
    
    @ManyToOne
    private Recipe r;

	public Tag() {
		super();
	}

	public Tag(Integer tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public Set<Recipe> getRecipeTag() {
		return RecipeTag;
	}

	public void setRecipeTag(Set<Recipe> recipeTag) {
		RecipeTag = recipeTag;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + "]";
	}
 }