package cooking.service;

import java.util.ArrayList;

import com.google.gson.Gson;

import cooking.dao.RecipeDAO;
import cooking.vo.Comment;
import cooking.vo.Recipe;

public class RecipeService {
	private RecipeDAO dao;
	private Gson gson;

	public RecipeService() {
		dao = new RecipeDAO();
		gson = new Gson();
	}

	// 레시피 리스트
	public ArrayList<Recipe> getRecipeList(int numByPage, int pageNum, String profile_cd_pk) {
		return dao.getRecipeList(numByPage, pageNum, profile_cd_pk);
	}
	// 최근 레시피 조회(default 레시피)
	public Recipe getDefaultRecipe(String profle_cd_pk) {
		return dao.getDefaultRecipe(profle_cd_pk);
	}
	// 레피시 조회
	public Recipe getRecipe(String recipe_cd_no) {
		return dao.getRecipe(recipe_cd_no);
	}

	// 레시피 조회(제이슨)
	public String getJsonRecipe(String recipe_cd_no) {
		return gson.toJson(dao.getRecipe(recipe_cd_no));
	}

	// 총 페이지의 마지막 페이지
	public int getRealLastPage(int numByPage, String profile_cd_pk) {
		int bbsCnt = dao.getRecipeCount(profile_cd_pk);
		int lastPage = 1;
		if (bbsCnt % numByPage > 0) {
			lastPage = bbsCnt / numByPage + 1;
		} else {
			lastPage = bbsCnt / numByPage;
		}
		return lastPage;
	}

	// 현재 페이지에서 보여지는 시작 페이지
	public int getFirstPage(int shownPageNum, int pageNum) {
		int firstPage = 1;
		if (pageNum % shownPageNum > 0) {
			firstPage = 1 + (pageNum / shownPageNum) * shownPageNum;
		} else {
			firstPage = shownPageNum * (pageNum / shownPageNum - 1) + 1;
		}
		return firstPage;
	}

	// 현재 페이지에서 보여지는 끝 페이지
	public int getLastPage(int shownPageNum, int realLastPage, int firstPage) {
		int lastPage = firstPage + (shownPageNum - 1);
		if (realLastPage <= lastPage) {
			lastPage = realLastPage;
		}
		return lastPage;
	}

	// 실제 끝페이지가 보여지는 끝 페이지보다 클 경우 다음 버튼 표시 == 다음페이지 존재 여부 확인
	public boolean nextPage(int lastPage, int realLastPage) {
		boolean exist = false;
		if (realLastPage > lastPage) {
			exist = true;
		}
		return exist;
	}

	// 조회수 증가
	public int updateViewCnt(String recipe_cd_no) {
		return dao.updateViewCnt(recipe_cd_no);
	}

	// 이전 페이지 존재 여부 확인
	public boolean prevPage(int firstPage, int shownPageNum) {
		boolean exist = false;
		if (firstPage > shownPageNum) {
			exist = true;
		}
		return exist;
	}
	// 레시피 삭제
	public int deleteRecipe(String recipe_cd_no) {
		return dao.deleteRecipe(recipe_cd_no);
	}
	// 댓글 리스트
	public ArrayList<Comment> getCommentList(String recipe_cd_no) {
		return dao.getCommentList(recipe_cd_no);
	}
	//댓글 입력
	public int inputComment(Comment comment) {
		return dao.inputComment(comment);
	}

	//댓글 삭제
	public int deleteComment(String comment_cd_no) {
		return dao.deleteComment(comment_cd_no);
	}

}
