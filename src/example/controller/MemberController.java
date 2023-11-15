package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Member;
import example.util.Util;

public class MemberController {
	
	private List<Member> members;
	private int lastMemberId;
	private Scanner sc;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.lastMemberId = 0;
		this.sc = sc;
	}
	
	public void doAction(String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}
	
	public void doJoin() {

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
	
	private boolean isLoginIdDupChk(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return true;
			}
		}
		return false;
	}
}
