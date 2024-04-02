package com.ssafy.diary.service;

import java.util.List;
import java.util.Map;

import com.ssafy.diary.dto.DiaryDto;
import com.ssafy.util.PageNavigation;

public interface DiaryService {

	List<DiaryDto> listDiary(Map<String, Object> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, Object> map) throws Exception;

	void writeDiary(DiaryDto diaryDto) throws Exception;

	DiaryDto getDiary(int diaryNo) throws Exception;

	void updateHit(int diaryNo) throws Exception;

	void modifyDiary(DiaryDto diaryDto) throws Exception;

	void deleteDiary(int diaryNo) throws Exception;
}
