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
	//회원의 위시리스트 가져오기
	public List<WishBean> selectWishByMember_no(int member_no) {
		List<WishBean> list = null;
		list = sqlSessionTemplate.selectList(namespace+".SelectWishByMember_no", member_no);
		return list;
	}
	//하나의 업체정보 가져오기
	public FoodstoreBean getStoreByStore_no(int store_no) {
		FoodstoreBean fb = sqlSessionTemplate.selectOne(namespace+".GetStoreByStore_no", store_no);
		return fb;
	}
	//하트 삭제하기
	public void deleteWish(WishBean wish) {
		sqlSessionTemplate.delete(namespace+".DeleteWish", wish);
	}

}



