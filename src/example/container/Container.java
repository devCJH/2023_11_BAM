package example.container;

import example.dao.ArticleDao;
import example.dao.MemberDao;
import example.service.ArticleService;
import example.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static ArticleService articleService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}
