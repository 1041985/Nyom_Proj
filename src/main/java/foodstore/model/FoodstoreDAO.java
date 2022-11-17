package foodstore.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("FoodstoreDAO")
public class FoodstoreDAO {

	private String namespace="foodstore.model.foodstore";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<FoodstoreBean> getAllFoodStore() {
		List<FoodstoreBean> lists = new ArrayList<FoodstoreBean>();
		lists = sqlSessionTemplate.selectList(namespace+".GetAllFoodStore");

		return lists;
	}

	public int insertfoodstore(FoodstoreBean foodstore) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".Insertfoodstore",foodstore);
		System.out.println("insertfoodstore 성공 : "+cnt);
		return cnt;
	}



}
