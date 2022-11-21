package member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("MemberDAO")
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate template;	
	
	private String namespace = "member.model.MemberBean";
	
	//로그인
	public MemberBean selectMemberById(String member_id) {
		MemberBean login = null;
		login = template.selectOne(namespace + ".SelectMemberById", member_id);
		return login;
	}
	
	//아이디찾기
	public MemberBean selectIdByNameEmail(MemberBean memberBean) {
		MemberBean findid=null;
		findid=template.selectOne(namespace + ".SelectIdByNameEmail", memberBean);
		return findid;
	}

	//비밀번호찾기
	public MemberBean selectPwByIdEmail(MemberBean memberBean) {
		MemberBean findpw=null;
		findpw=template.selectOne(namespace + ".SelectPwByIdEmail", memberBean);
		return findpw;
	}
	
	//마이페이지 비밀번호 입력
	public MemberBean selectMemberByIdPw(MemberBean memberBean) {
		MemberBean mypage=null;
		mypage=template.selectOne(namespace + ".SelectMemberByIdPw", memberBean);
		return mypage;
	}

	//내정보 수정
	public int updateMemberById(MemberBean memberBean) {
		int updatemem=template.update(namespace + ".UpdateMemberById", memberBean);
		return updatemem;
	}

}
