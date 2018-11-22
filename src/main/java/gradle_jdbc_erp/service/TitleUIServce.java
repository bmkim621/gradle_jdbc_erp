package gradle_jdbc_erp.service;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dao.TitleDao;
import gradle_jdbc_erp.dao.TitleDaoImpl;
import gradle_jdbc_erp.dto.Title;

public class TitleUIServce {
	private TitleDao titleDao;

	public TitleUIServce() {
		titleDao = new TitleDaoImpl();
	}

	public List<Title> selectAll() throws SQLException{
		return titleDao.selectTitleByAll();
	}
	
	public int addTitle(Title item) throws SQLException{
		return titleDao.insertTitle(item);
	}
	
	public int updateTitle(Title item) throws SQLException{
		return titleDao.updateTitle(item);
	}
	
	public int deleteTitle(Title item) throws SQLException{
		return titleDao.deleteTitle(item);
	}
	
	public String nextTitleNo() throws SQLException{
		return titleDao.nextTitleNo();
	}
}
