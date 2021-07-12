package com.localhost.quxiaoshuo.domain;

public class ChapterInfo {
	private Integer id;
	private String title;
	private Integer novelkey;
	private String filepath;
	private String content;
	private Integer words;

	@Override
	public String toString() {
		return "ChapterInfo{" +
				"id=" + id +
				", title='" + title + '\'' +
				", novelkey=" + novelkey +
				", filepath='" + filepath + '\'' +
				", content='" + content + '\'' +
				", words=" + words +
				'}';
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

	public Integer getNovelkey() {
		return novelkey;
	}

	public void setNovelkey(Integer novelkey) {
		this.novelkey = novelkey;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
	}
}
