package com.ssafy.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;
import com.ssafy.diary.dto.DiaryDto;
import com.ssafy.util.DBUtil;

public class DiaryDaoImpl implements DiaryDao {
	
	private static DiaryDao diaryDao;
	private DBUtil dbUtil;
	
	private DiaryDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static DiaryDao getDiaryDao() {
		if(diaryDao == null)
			diaryDao = new DiaryDaoImpl();
		return diaryDao;
	}

	@Override
	public List<DiaryDto> listDiary(Map<String, Object> map) throws Exception {
		List<DiaryDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String key = (String) map.get("key");
		String word = (String) map.get("word");
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select diary_no, user_id, title, color, image, content, hit, register_time\n");
			sql.append("from diary\n");
			if(!key.isEmpty() && word.isEmpty()) {
				if(key.equals("subject"))
					sql.append("where title like concat('%', ?, '%')\n");
				else
					sql.append("where").append(key).append("= ? \n");
			}
			sql.append("order by diary_no desc\n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if(!key.isEmpty() && !word.isEmpty()) {
				pstmt.setString(++idx, word);
			}
			pstmt.setInt(++idx, (int) map.get("start"));
			pstmt.setInt(++idx, (int)map.get("listsize"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DiaryDto diaryDto = new DiaryDto();
				diaryDto.setDiaryNo(rs.getInt("diary_no"));
				diaryDto.setUserId(rs.getString("user_id"));
				diaryDto.setTitle(rs.getString("title"));
				diaryDto.setColor(rs.getString("color"));
				diaryDto.setDiaryImg(rs.getString("image"));
				diaryDto.setContent(rs.getString("content"));
				diaryDto.setHit(rs.getInt("hit"));
				diaryDto.setRegisterTime(rs.getString("register_time"));
				
				list.add(diaryDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int getTotalDiaryCount(Map<String, Object> map) throws Exception {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String key = (String) map.get("key");
		String word = (String) map.get("word");
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(diary_no) cnt\n");
			sql.append("from diary\n");
			if(!key.isEmpty()&&!word.isEmpty()) {
				if(key.equals("subject"))
					sql.append("where subject like concat('%', ?, '%')\n");
				else
					sql.append("where ").append(key).append(" = ?\n");
			}
			pstmt = conn.prepareStatement(sql.toString());
			if(!key.isEmpty()&&!word.isEmpty())
				pstmt.setString(1, word);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
			
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return cnt;
	}
	
	@Override
	public void writeDiary(DiaryDto diaryDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into diary (user_id, title, color, image, content) \n");
			sql.append("values (?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, diaryDto.getUserId());
			pstmt.setString(2, diaryDto.getTitle());
			pstmt.setString(3, diaryDto.getColor());
			pstmt.setString(4, diaryDto.getDiaryImg());
			pstmt.setString(5, diaryDto.getContent());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public DiaryDto getDiary(int diaryNo) throws Exception {
		DiaryDto diaryDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select diary_no, user_id, title, color, image, content, hit, register_time\n");
			sql.append("from diary \n");
			sql.append("where diary_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, diaryNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				diaryDto = new DiaryDto();
				diaryDto.setDiaryNo(rs.getInt("diary_no"));
				diaryDto.setUserId(rs.getString("user_id"));
				diaryDto.setTitle(rs.getString("title"));
				diaryDto.setColor(rs.getString("color"));
				diaryDto.setDiaryImg(rs.getString("image"));
				diaryDto.setContent(rs.getString("content"));
				diaryDto.setHit(rs.getInt("hit"));
				diaryDto.setRegisterTime(rs.getString("register_time"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return diaryDto;
	}
	
	@Override
	public void updateHit(int diaryNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update diary \n");
			sql.append("set hit = hit + 1\n");
			sql.append("where diary_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, diaryNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void modifyDiary(DiaryDto diaryDto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update diary\n");
			sql.append("set title = ?, color = ?, content = ?\n");
			sql.append("where diary_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, diaryDto.getTitle());
			pstmt.setString(2, diaryDto.getColor());
			pstmt.setString(3, diaryDto.getContent());
			pstmt.setInt(4, diaryDto.getDiaryNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteDiary(int diaryNo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from diary\n");
			sql.append("where diary_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, diaryNo);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}



}
