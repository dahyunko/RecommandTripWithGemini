package com.ssafy.diary.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.diary.dto.DiaryDto;

public interface DiaryDao {
	List<DiaryDto> listDiary(Map<String, Object> map) throws Exception;
	int getTotalDiaryCount(Map<String, Object> map) throws Exception;
	void writeDiary(DiaryDto diaryDto) throws Exception;
	DiaryDto getDiary(int diaryNo) throws Exception;
	void updateHit(int diaryNo) throws Exception;
	void modifyDiary(DiaryDto diaryDto) throws Exception;
	void deleteDiary(int diaryNo) throws Exception;
}
