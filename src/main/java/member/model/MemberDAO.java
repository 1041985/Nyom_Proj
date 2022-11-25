package member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("MemberDAO")
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate template;	
	
	private String namespace = "member.model.MemberBean";
	
	//�α���
	public MemberBean selectMemberById(String member_id) {
		MemberBean login = null;
		login = template.selectOne(namespace + ".SelectMemberById", member_id);
		return login;
	}
	
	//���̵�ã��
	public MemberBean selectIdByNameEmail(MemberBean memberBean) {
		MemberBean findid=null;
		findid=template.selectOne(namespace + ".SelectIdByNameEmail", memberBean);
		return findid;
	}

	//��й�ȣã��
	public MemberBean selectPwByIdEmail(String member_id) {
		MemberBean findpw=null;
		findpw=template.selectOne(namespace + ".SelectPwByIdEmail", member_id);
		return findpw;
	}
	
	//��й�ȣã�� ���Ϸ� �ӽú�й�ȣ �߱�
	public int updatePw(MemberBean memberBean) throws Exception {
		int updatepw=template.update(namespace + ".UpdatePw", memberBean);
		return updatepw;
	}
	
	//���������� ��й�ȣ �Է�
	public MemberBean selectMemberByIdPw(MemberBean memberBean) {
		MemberBean mypage=null;
		mypage=template.selectOne(namespace + ".SelectMemberByIdPw", memberBean);
		return mypage;
	}

	//������ ����
	public int updateMemberById(MemberBean memberBean) {
		int updatemem=template.update(namespace + ".UpdateMemberById", memberBean);
		return updatemem;
	}
	
	//ȸ������
	public int insertMember(MemberBean memberBean) {
		int insertmem=template.insert(namespace + ".InsertMember", memberBean);
		return insertmem;
	}
	
	//ȸ��Ż��
	public int deleteMember(String member_id) {
		int deletemem=template.delete(namespace + ".DeleteMember", member_id);
		return deletemem;
	}
	
	//���̵� �ߺ�üũ
	public int checkId(String member_id) {
		int idcheck=template.selectOne(namespace + ".CheckId", member_id);
		return idcheck;
	}
}
