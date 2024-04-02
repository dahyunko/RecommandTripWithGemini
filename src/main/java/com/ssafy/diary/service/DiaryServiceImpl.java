package com.ssafy.diary.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.diary.dao.DiaryDao;
import com.ssafy.diary.dao.DiaryDaoImpl;
import com.ssafy.diary.dto.DiaryDto;
import com.ssafy.util.DiarySize;
import com.ssafy.util.PageNavigation;

public class DiaryServiceImpl implements DiaryService {
	
	private static DiaryService diaryService = new DiaryServiceImpl();
	
	private DiaryDao diaryDao;
	
	private DiaryServiceImpl() {		
		diaryDao = DiaryDaoImpl.getDiaryDao();
	}
	
	public static DiaryService getDiaryService(){
		return diaryService;
	}
	
	
	

	@Override
	public List<DiaryDto> listDiary(Map<String, Object> map) throws Exception {
		int pgno = (int) map.get("pgno");
		int listSize = DiarySize.LIST.getDiarySize();
		int start = (pgno-1)*listSize;
		map.put("start", start);
		map.put("listsize", listSize);
		return diaryDao.listDiary(map);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, Object> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		
		int currentPage = (int) map.get("pgno");
		int naviSize = DiarySize.NAVIGATION.getDiarySize();
		int listSize = DiarySize.LIST.getDiarySize();
		
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = diaryDao.getTotalDiaryCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / listSize + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		
		return pageNavigation;
	}
	
	@Override
	public void writeDiary(DiaryDto diaryDto) throws Exception {
		diaryDao.writeDiary(diaryDto);
	}

	@Override
	public DiaryDto getDiary(int diaryNo) throws Exception {
		return diaryDao.getDiary(diaryNo);
	}

	@Override
	public void updateHit(int diaryNo) throws Exception {
		diaryDao.updateHit(diaryNo);
	}
	
	@Override
	public void modifyDiary(DiaryDto diaryDto) throws Exception {
		diaryDao.modifyDiary(diaryDto);
	}

	@Override
	public void deleteDiary(int diaryNo) throws Exception {
		diaryDao.deleteDiary(diaryNo);
	}


}
