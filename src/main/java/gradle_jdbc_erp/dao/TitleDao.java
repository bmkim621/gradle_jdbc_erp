package gradle_jdbc_erp.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dto.Title;

public interface TitleDao {
	List<Title> selectTitleByAll() throws SQLException;
	
	int insertTitle(Title item) throws SQLException;
	
	int updateTitle(Title item) throws SQLException;
	
	int deleteTitle(Title item) throws SQLException;
	
	String nextTitleNo() throws SQLException;
}
