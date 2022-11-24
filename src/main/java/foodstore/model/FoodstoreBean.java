package foodstore.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class FoodstoreBean {

	private int store_no;
	
	@NotBlank(message="상호명을 입력하세요")
	private String store_name;
	
	private String store_addrnum;
	
	@NotBlank(message="주소를 입력하세요")
	private String store_addr;
	
	@NotBlank(message="전화번호를 입력하세요")
	private String store_tel;
	
	@NotBlank(message="오픈시간을 선택하세요")
	private String open_hours;
	
	@NotBlank(message="마감시간을 선택하세요")
	private String close_hours;
	
	@NotBlank(message="업체 이미지를 선택하세요")
	private String store_img;
	
	@NotBlank(message="메뉴를 입력하세요")
	private String menu;
	
	@NotBlank(message="해시태그를 입력하세요")
	private String hashtag;
	private int readcount;
	
	@NotBlank(message="업체 소개글을 입력하세요")
	private String store_contents;
	
	private MultipartFile upload;
	
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
		this.store_img = upload.getOriginalFilename();
	}
	
	private String addr_num;
	private String addr_first;
	private String addr_last;
	
	
	
	public String getAddr_num() {
		return addr_num;
	}
	public void setAddr_num(String addr_num) {
		this.addr_num = addr_num;
	}
	public String getAddr_first() {
		return addr_first;
	}
	public void setAddr_first(String addr_first) {
		this.addr_first = addr_first;
	}
	public String getAddr_last() {
		return addr_last;
	}
	public void setAddr_last(String addr_last) {
		this.addr_last = addr_last;
	}
	public String getStore_addrnum() {
		return store_addrnum;
	}
	public void setStore_addrnum(String store_addrnum) {
		this.store_addrnum = store_addrnum;
	}
	
	
	
	
	public int getStore_no() {
		return store_no;
	}
	public void setStore_no(int store_no) {
		this.store_no = store_no;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_addr() {
		return store_addr;
	}
	public void setStore_addr(String store_addr) {
		this.store_addr = store_addr;
	}
	public String getStore_tel() {
		return store_tel;
	}
	public void setStore_tel(String store_tel) {
		this.store_tel = store_tel;
	}
	public String getOpen_hours() {
		return open_hours;
	}
	public void setOpen_hours(String open_hours) {
		this.open_hours = open_hours;
	}
	public String getClose_hours() {
		return close_hours;
	}
	public void setClose_hours(String close_hours) {
		this.close_hours = close_hours;
	}
	public String getStore_img() {
		return store_img;
	}
	public void setStore_img(String store_img) {
		this.store_img = store_img;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getStore_contents() {
		return store_contents;
	}
	public void setStore_contents(String store_contents) {
		this.store_contents = store_contents;
	}
	
}
