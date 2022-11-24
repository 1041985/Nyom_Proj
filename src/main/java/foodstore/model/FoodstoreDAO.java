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

	//상세보기, 수정폼 띄우기, 삭제할때 번호가져오기
	public FoodstoreBean selectFoodstore(String store_no) {
		
		FoodstoreBean fsbean;
		
		fsbean = sqlSessionTemplate.selectOne(namespace+".SelectFoodstore", store_no);
		System.out.println("fsb.getStore_no() :"+ fsbean.getStore_no());
		return fsbean;
	}

	
	//업체 수정하기
	public int updateFoodstore(FoodstoreBean foodstore) {
		
		int cnt= -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateFoodstore", foodstore);
		
		System.out.println("updateFoodstore: "+cnt);
		
		return cnt;
	}

	public int deleteFoodstore(String store_no) {
		
		int cnt =-1;
		cnt = sqlSessionTemplate.delete(namespace+".DeleteFoodstore",store_no);
		System.out.println("deleteFoodstore: "+cnt);
		
		return cnt;
	}



}
