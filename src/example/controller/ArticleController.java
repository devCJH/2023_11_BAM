package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.container.Container;
import example.dto.Article;
import example.util.Util;

public class ArticleController extends Controller {
	
	private String cmd;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.cmd = null;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		
		this.cmd = cmd;
		
		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}
	
	private void doWrite() {
		
		int lastArticleId = Container.articleDao.getLastId();
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(lastArticleId, Util.getDateStr(), loginedMember.id, title, body);

		Container.articleDao.doWrite(article);

		System.out.println(lastArticleId + "번 게시물이 생성되었습니다");
	}
	
	private void showList() {
			
		List<Article> articles = Container.articleDao.getArticles();
		
		if (articles.size() == 0) {
			System.out.println("게시물이 존재하지 않습니다");
			return;
		}
		
		String searchKeyword = cmd.substring("article list".length()).trim();

		List<Article> printArticles = articles;
		
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
				return;
			}
		}
		
		System.out.println("번호	/		작성일		/	제목	/	작성자");
		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);

			String writerName = Container.memberDao.getWriterName(article.memberId);
			
			System.out.printf("%d	/	%s	/	%s	/	%s\n", article.id, article.regDate, article.title, writerName);
		}
	}

	private void showDetail() {
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = Container.articleDao.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		String writerName = Container.memberDao.getWriterName(foundArticle.memberId);
		
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성일 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
	}
	
	private void doModify() {
		
		if (isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = Container.articleDao.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("%d번 게시물에 대한 권한이 없습니다\n", id);
			return;
		}
		
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		Container.articleDao.doModify(foundArticle, title, body);

		System.out.printf("%d번 게시물을 수정했습니다\n", id);
	}
	
	private void doDelete() {
		
		if (isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = Container.articleDao.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("%d번 게시물에 대한 권한이 없습니다\n", id);
			return;
		}
		
		Container.articleDao.doDelete(foundArticle);
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);
	}
	
	@Override
	public void makeTestData() {
		Container.articleDao.doWrite(new Article(Container.articleDao.getLastId(), Util.getDateStr(), 2, "제목1", "내용1"));
		Container.articleDao.doWrite(new Article(Container.articleDao.getLastId(), Util.getDateStr(), 3, "제목2", "내용2"));
		Container.articleDao.doWrite(new Article(Container.articleDao.getLastId(), Util.getDateStr(), 2, "제목3", "내용3"));
		System.out.println("테스트용 게시물이 생성되었습니다");
	}
}
