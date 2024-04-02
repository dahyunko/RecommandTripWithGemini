package com.ssafy.util;

public enum DiarySize {
	LIST(20),
	NAVIGATION(10);
	
	private int diarySize;

	private DiarySize(int diarySize) {
		this.diarySize = diarySize;
	}

	public int getDiarySize() {
		return diarySize;
	}
	

}
