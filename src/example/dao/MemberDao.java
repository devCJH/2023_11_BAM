package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.dto.Member;

public class MemberDao extends Dao {
	private List<Member> members;
	
	public MemberDao () {
		this.members = new ArrayList<>();
	}

	public int getLastMemberId() {
		return this.lastId + 1;
	}
	
	public void doJoin(Member member) {
		this.members.add(member);
		this.lastId++;
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
