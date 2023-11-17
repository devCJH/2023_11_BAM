package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.dto.Member;

public class MemberDao {
	private List<Member> members;
	
	public MemberDao () {
		this.members = new ArrayList<>();
	}

	public void doJoin(Member member) {
		this.members.add(member);
	}
	
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public boolean isLoginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);
		if (member != null) {
			return true;
		}
		return false;
	}
}
