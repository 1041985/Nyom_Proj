package noticeboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("NoticeboardDAO")
public class NoticeboardDAO {

	private final String namespace = "noticeboard.model.noticeboard";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<NoticeboardBean> getAllNotice(Paging pageInfo, Map<String, String> map) {
		List<NoticeboardBean> lists = new ArrayList<NoticeboardBean>();
		RowBounds rowbounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit()); 
		lists = sqlSessionTemplate.selectList(namespace+".GetAllNotice", map, rowbounds);
		return lists;
	}

	public int getNoticeTotalCount(Map<String, String> map) {
		return sqlSessionTemplate.selectOne(namespace+".GetNoticeTotalCount" , map);
	}

	public void insertNotice(NoticeboardBean notice) {
		sqlSessionTemplate.insert(namespace+".InsertNotice", notice);
	}

	public NoticeboardBean getNoticeDetail(int no) {
		NoticeboardBean notice = sqlSessionTemplate.selectOne(namespace+".GetNoticeDetail", no);
		return notice;
	}

	public void readCountNotice(int no) {
		sqlSessionTemplate.update(namespace+".ReadCountNotice", no);
	}

	public void updateNotice(NoticeboardBean notice) {
		sqlSessionTemplate.update(namespace+".UpdateNotice", notice);
	}

	public void deleteNotice(int no) {
		sqlSessionTemplate.delete(namespace+".DeleteNotice", no);
	}
	
	
	
}
