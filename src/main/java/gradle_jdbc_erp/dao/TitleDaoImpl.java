package gradle_jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.jdbc.ConnectionProvider;
import gradle_jdbc_erp.jdbc.LogUtil;

public class TitleDaoImpl implements TitleDao {

	@Override
	public List<Title> selectTitleByAll() throws SQLException {
		List<Title> list = new ArrayList<>();
		String sql = "select titleno, titlename from title";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			while(rs.next()) {
				list.add(getTitle(rs));
			}
		} catch(SQLException e1){
			e1.printStackTrace();
		}
		return list;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		String titleNo = rs.getString("titleno");
		String titleName = rs.getString("titlename");
		
		return new Title(titleNo, titleName);
	}

	@Override
	public int insertTitle(Title item) throws SQLException {
		String sql = "insert into title values(?, ?)";
		
		int res = 0;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, item.getTitleNo());
			pstmt.setString(2, item.getTitleName());
			
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateTitle(Title item) throws SQLException {
		String sql = "update title set titlename = ? where titleno = ?";
		
		int res = 0;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, item.getTitleName());
			pstmt.setString(2, item.getTitleNo());
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteTitle(Title item) throws SQLException {
		String sql = "delete from title where titleno = ?";
		
		int res = 0;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, item.getTitleNo());
			
			LogUtil.prnLog(pstmt);
			res = pstmt.executeUpdate();
		}
		return res;
	}

	//다음번호
	@Override
	public String nextTitleNo() throws SQLException {
		String sql = "select max(titleno) as nextno from title";
		String nextStr = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("T%03d", Integer.parseInt(rs.getString("nextno").substring(1)) + 1);
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		return nextStr;
	}

}
