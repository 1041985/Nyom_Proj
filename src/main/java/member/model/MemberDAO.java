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
	public MemberBean selectPwByIdEmail(String member_id) {
		MemberBean findpw=null;
		findpw=template.selectOne(namespace + ".SelectPwByIdEmail", member_id);
		return findpw;
	}
	
	//비밀번호찾기 메일로 임시비밀번호 발급
	public int updatePw(MemberBean memberBean) throws Exception {
		int updatepw=template.update(namespace + ".UpdatePw", memberBean);
		return updatepw;
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
	
	//회원가입
	public int insertMember(MemberBean memberBean) {
		int insertmem=template.insert(namespace + ".InsertMember", memberBean);
		return insertmem;
	}
	
	//회원탈퇴
	public int deleteMember(String member_id) {
		int deletemem=template.delete(namespace + ".DeleteMember", member_id);
		return deletemem;
	}
	
	//아이디 중복체크
	public int checkId(String member_id) {
		int idcheck=template.selectOne(namespace + ".CheckId", member_id);
		return idcheck;
	}
}
