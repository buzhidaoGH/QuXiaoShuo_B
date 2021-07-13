package com.localhost.quxiaoshuo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NovelInfo {
	private Integer id;
	private String title;
	private Long novelkey;
	private String author;
	private String image;
	private Long chapters;
	private String url;
	private int isexist;
	private Date update;
	private int status;
	private String description;
	private String category;
	private Long hits;

	@Override
	public String toString() {
		return "NovelInfo{" +
				"id=" + id +
				", title='" + title + '\'' +
				", novelkey=" + novelkey +
				", author='" + author + '\'' +
				", image='" + image + '\'' +
				", chapters=" + chapters +
				", url='" + url + '\'' +
				", isexist=" + isexist +
				", update=" + update +
				", status=" + status +
				", description='" + description + '\'' +
				", category='" + category + '\'' +
				", hits=" + hits +
				'}';
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getNovelkey() {
		return novelkey;
	}

	public void setNovelkey(Long novelkey) {
		this.novelkey = novelkey;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getChapters() {
		return chapters;
	}

	public void setChapters(Long chapters) {
		this.chapters = chapters;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsexist() {
		return isexist;
	}

	public void setIsexist(int isexist) {
		this.isexist = isexist;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
