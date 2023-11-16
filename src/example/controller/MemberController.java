package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Member;
import example.util.Util;

public class MemberController extends Controller {
	
	private List<Member> members;
	private int lastMemberId;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.lastMemberId = 0;
		this.sc = sc;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}

	private void doJoin() {
		
		if (isLogined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}

		lastMemberId++;
		
		String loginId = null;
		
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다");
				continue;
			}
			
			if (isLoginIdDupChk(loginId)) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("%s은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		
		String loginPw = null;
		
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력정보입니다");
				continue;
			}
			
			System.out.printf("비밀번호 확인 : ");
			String loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		
		String name = null;
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수 입력정보입니다");
				continue;
			}
			break;
		}
		
		Member member = new Member(lastMemberId, Util.getDateStr(), loginId, loginPw, name);
		
		this.members.add(member);
		
		System.out.printf("%s회원님이 가입되었습니다\n", name);
	}
	
	private void doLogin() {
		
		if (isLogined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		String loginId = null;
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			break;
		}
		
		String loginPw = null;
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}
			break;
		}
		
		Member member = getMemberByLoginId(loginId);
		
		if (member == null) {
			System.out.printf("%s(은)는 존재하지 않는 아이디입니다\n", loginId);
			return;
		}
		
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}
		
		loginedMember = member;
		
		System.out.printf("%s님 환영합니다~\n", member.name);
	}
	
	private void doLogout() {
		
		if (isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		loginedMember = null;
		System.out.println("로그아웃 되었습니다");
	}
	
	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean isLoginIdDupChk(String loginId) {
		
		Member member = getMemberByLoginId(loginId);
		
		if (member != null) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void makeTestData() {
		this.members.add(new Member(++lastMemberId, Util.getDateStr(), "test1", "test1", "user1"));
		this.members.add(new Member(++lastMemberId, Util.getDateStr(), "test2", "test2", "user2"));
		this.members.add(new Member(++lastMemberId, Util.getDateStr(), "test3", "test3", "user3"));
		System.out.println("테스트용 회원데이터가 생성되었습니다");
	}
}
