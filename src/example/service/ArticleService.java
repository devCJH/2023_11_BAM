package example.service;

import java.util.List;

import example.container.Container;
import example.dao.ArticleDao;
import example.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public int getLastId() {
		return articleDao.getLastId();
	}

	public void doWrite(Article article) {
		articleDao.doWrite(article);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void doModify(Article foundArticle, String title, String body) {
		articleDao.doModify(foundArticle, title, body);
	}

	public void doDelete(Article foundArticle) {
		articleDao.doDelete(foundArticle);
	}
}
