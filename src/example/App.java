package example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Article;
import example.dto.Member;
import example.util.Util;

public class App {
	
	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	private int lastMemberId;
	
	public App() {
		 this.articles = new ArrayList<>();
		 this.members = new ArrayList<>();
		 this.lastArticleId = 0;
		 this.lastMemberId = 0;
	}
	
	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

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
			
			if (cmd.equals("member join")) {
				
				lastMemberId++;
				
				System.out.printf("아이디 : ");
				String loginId = sc.nextLine();
				System.out.printf("비밀번호 : ");
				String loginPw = sc.nextLine();
				System.out.printf("비밀번호 확인 : ");
				String loginPwChk = sc.nextLine();
				System.out.printf("이름 : ");
				String name = sc.nextLine();
				
				Member member = new Member(lastMemberId, Util.getDateStr(), loginId, loginPw, name);
				
				this.members.add(member);
				
				System.out.printf("%s회원님이 가입되었습니다\n", name);
				
			} else if (cmd.equals("article write")) {

				lastArticleId++;

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(lastArticleId, Util.getDateStr(), title, body);

				this.articles.add(article);

				System.out.println(lastArticleId + "번 게시물이 생성되었습니다");
			} else if (cmd.startsWith("article list")) {

				if (this.articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
					continue;
				}
				
				String searchKeyword = cmd.substring("article list".length()).trim();

				List<Article> printArticles = this.articles;
				
				if (searchKeyword.length() > 0) {
					
					System.out.println("검색어 : " + searchKeyword);
					
					printArticles = new ArrayList<>();
					
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							printArticles.add(article);
						}
					}
					
					if (printArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}
				
				System.out.println("번호	/	제목	/		작성일");
				for (int i = printArticles.size() - 1; i >= 0; i--) {
					Article article = printArticles.get(i);
					System.out.printf("%d	/	%s	/	%s\n", article.id, article.title, article.regDate);
				}

			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성일 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물을 수정했습니다\n", id);

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				this.articles.remove(foundArticle);
				System.out.printf("%d번 게시물을 삭제했습니다\n", id);

			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}
	
	private Article getArticleById(int id) {
		for (Article article : this.articles) {
			if (article.id == id) {
				return article;
			}
		}
		return null;
	}

	private void makeTestData() {
		this.articles.add(new Article(++lastArticleId, Util.getDateStr(), "제목1", "내용1"));
		this.articles.add(new Article(++lastArticleId, Util.getDateStr(), "제목2", "내용2"));
		this.articles.add(new Article(++lastArticleId, Util.getDateStr(), "제목3", "내용3"));
		System.out.println("테스트용 게시물이 생성되었습니다");
	}
}
