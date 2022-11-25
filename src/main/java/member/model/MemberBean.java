package member.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class MemberBean {
	
	private int member_no; //회원 번호
	@Pattern(regexp="^[a-z]+[a-z0-9]{7,14}$", message="영문으로 시작해서 영문 또는 숫자 7~14자로 입력하세요.")
	private String member_id; //회원 아이디
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{7,14}$", message="영문과 숫자를 포함해서 7~14자로 입력하세요.")
	private String member_pw; //회원 비밀번호
	@Pattern(regexp="^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ]*$", message="한글 또는 영문으로 입력하세요.")
	private String member_name; //회원 이름
	@NotBlank(message="이메일을 입력하세요.")
	private String member_email; //회원 이메일
	@Pattern(regexp="^\\d{11}$", message="숫자만 11자로 입력하세요.")

	private String member_hp; //회원 전화번호
	private String member_date; //회원 가입일
	
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_hp() {
		return member_hp;
	}
	public void setMember_hp(String member_hp) {
		this.member_hp = member_hp;
	}
	public String getMember_date() {
		return member_date;
	}
	public void setMember_date(String member_date) {
		this.member_date = member_date;
	}
	
}
