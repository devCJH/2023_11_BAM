package example;

import java.util.Scanner;

import example.controller.ArticleController;
import example.controller.MemberController;

public class App {
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			if (controllerName.equals("member")) {
				memberController.doAction(methodName);
			} else if (controllerName.equals("article")) {
				articleController.doAction(methodName);
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
			
//			if (cmd.equals("member join")) {
//				memberController.doJoin();
//			} else if (cmd.equals("article write")) {
//				articleController.doWrite();
//			} else if (cmd.startsWith("article list")) {
//				articleController.showList(cmd);
//			} else if (cmd.startsWith("article detail ")) {
//				articleController.showDetail(cmd);
//			} else if (cmd.startsWith("article modify ")) {
//				articleController.doModify(cmd);
//			} else if (cmd.startsWith("article delete ")) {
//				articleController.doDelete(cmd);
//			} else {
//				System.out.println("존재하지 않는 명령어입니다");
//			}
		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}
}
