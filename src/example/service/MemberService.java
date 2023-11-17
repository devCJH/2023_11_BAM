package example.service;

import example.container.Container;
import example.dao.MemberDao;
import example.dto.Member;

public class MemberService {

private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}
	
	public int getLastId() {
		return memberDao.getLastId();
	}

	public boolean isLoginIdDupChk(String loginId) {
		return memberDao.isLoginIdDupChk(loginId);
	}

	public void doJoin(Member member) {
		memberDao.doJoin(member);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	
	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}
}
