package com.ssafy.diary.dto;

public class DiaryDto {
	private int diaryNo;
	private String userId;
	private String diaryImg;
	private String color;
	private String title;
	private String content;
	private int hit;
	private String registerTime;
	
	public DiaryDto() {}

	public DiaryDto(int diaryNo, String userId, String diaryImg, String color, String title, String content, int hit,
			String registerTime) {
		super();
		this.diaryNo = diaryNo;
		this.userId = userId;
		this.diaryImg = diaryImg;
		this.color = color;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
	}

	public int getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDiaryImg() {
		return diaryImg;
	}

	public void setDiaryImg(String diaryImg) {
		this.diaryImg = diaryImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "DiaryDto [diaryNo=" + diaryNo + ", userId=" + userId + ", diaryImg=" + diaryImg + ", color=" + color
				+ ", title=" + title + ", content=" + content + ", hit=" + hit + ", registerTime=" + registerTime + "]";
	};
	
	
	

}
