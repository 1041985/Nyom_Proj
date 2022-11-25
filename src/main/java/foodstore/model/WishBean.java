package foodstore.model;

public class WishBean {
	
	private int wish_no;
	private int member_no; //회원(누가쓴건지) fk
	private int store_no; //맛집(찜한 업체들) 

	public int getWish_no() {
		return wish_no;
	}
	public void setWish_no(int wish_no) {
		this.wish_no = wish_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getStore_no() {
		return store_no;
	}
	public void setStore_no(int store_no) {
		this.store_no = store_no;
	}
	
	
}
