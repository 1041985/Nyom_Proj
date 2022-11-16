package noticeboard.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("NoticeboardDAO")
public class NoticeboardDAO {

	private final String namespace = "noticeboard.model.noticeboard";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<NoticeboardBean> getAllNotice() {
		List<NoticeboardBean> lists = new ArrayList<NoticeboardBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllNotice");
		return lists;
	}

	public int getNoticeTotalCount() {
		return sqlSessionTemplate.selectOne(namespace+".GetNoticeTotalCount");
	}

	public void insertNotice(NoticeboardBean notice) {
		sqlSessionTemplate.insert(namespace+".InsertNotice", notice);
	}
	
	
	
}
