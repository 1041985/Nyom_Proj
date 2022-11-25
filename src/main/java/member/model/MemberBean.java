package member.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class MemberBean {
	
	private int member_no; //È¸¿ø ¹øÈ£
	@Pattern(regexp="^[a-z]+[a-z0-9]{7,14}$", message="¿µ¹®À¸·Î ½ÃÀÛÇØ¼­ ¿µ¹® ¶Ç´Â ¼ıÀÚ 7~14ÀÚ·Î ÀÔ·ÂÇÏ¼¼¿ä.")
	private String member_id; //È¸¿ø ¾ÆÀÌµğ
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{7,14}$", message="¿µ¹®°ú ¼ıÀÚ¸¦ Æ÷ÇÔÇØ¼­ 7~14ÀÚ·Î ÀÔ·ÂÇÏ¼¼¿ä.")
	private String member_pw; //È¸¿ø ºñ¹Ğ¹øÈ£
	@Pattern(regexp="^[a-zA-Z¤¡-ÆR][a-zA-Z¤¡-ÆR ]*$", message="ÇÑ±Û ¶Ç´Â ¿µ¹®À¸·Î ÀÔ·ÂÇÏ¼¼¿ä.")
	private String member_name; //È¸¿ø ÀÌ¸§
	@NotBlank(message="ÀÌ¸ŞÀÏÀ» ÀÔ·ÂÇÏ¼¼¿ä.")
	private String member_email; //È¸¿ø ÀÌ¸ŞÀÏ
	@Pattern(regexp="^\\d{11}$", message="¼ıÀÚ¸¸ 11ÀÚ·Î ÀÔ·ÂÇÏ¼¼¿ä.")
	private String member_hp; //È¸¿ø ÀüÈ­¹øÈ£
	private String member_date; //È¸¿ø °¡ÀÔÀÏ
	
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
