package com.sbs.java.text_board.article.service.controller;

import com.sbs.java.text_board.base.Rq;
import com.sbs.java.text_board.container.Container;
import com.sbs.java.text_board.article.service.dto.Article;
import com.sbs.java.text_board.article.service.ArticleService;
import com.sbs.java.text_board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleController {
  private ArticleService articleService;
  public  ArticleController(){
    articleService=Container.articleService;
  }
  public void showList(Rq rq) {
    List<Article> articles = articleService.findAll();
    if (articleService.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }
    Map<String,String> params=rq.getParams();
    //검색기능 시작
    //articles:현재 정렬되지 않은 1-100개의 게시물 리스트
    List<Article> filteredArticles= articles;
    if(params.containsKey("searchKeyword")){
      String searchKeyword=params.get("searchKeyword");
      filteredArticles=new ArrayList<>();
      for(Article article: articles)
      {
        if(article.subject.contains(searchKeyword)|article.content.contains(searchKeyword))
          filteredArticles.add(article);
      }
    }
    //검색기능 끝
    //정렬로직 시작
    boolean orderByIdDesc=true;
    if(params.containsKey("orderBy")&&params.get("orderBy").equals("idAsc"))
    {
      orderByIdDesc=false;
    }
    List<Article> sortedArticles=filteredArticles;
    if(orderByIdDesc){
      sortedArticles= Util.reverseList(sortedArticles);
    }
    //정렬로직끝
    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목");
    // 역순 출력
    sortedArticles.forEach(article -> System.out.printf("%d | %s \n",article.id,article.subject));
  }
  public void showDetail(Rq rq) {
    if (articleService.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }
    Map<String, String> params = rq.getParams();
    if (!params.containsKey("id")) {
      System.out.println("id값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }
    Article article =articleService.findById(id);
    if (article==null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }
    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.subject);
    System.out.printf("내용 : %s\n", article.content);
  }
  public void doModify(Rq rq) {
    if (articleService.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }
    Map<String, String> params = rq.getParams();
    if (!params.containsKey("id")) {
      System.out.println("id값을 입력해주세요.");
      return;
    }
    int id = 0;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }
    Article article =articleService.findById(id);
    if (article==null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }
    String subject = "";
    String content = "";
    System.out.println("== 게시물 수정 ==");
    while(true){
      System.out.print("제목 : ");
      subject = Container.scanner.nextLine();
      if(subject.trim().isEmpty())
      {
        System.out.println("제목을 입력해주세요");
        continue;
      }
      break;
    }
    while(true){
      System.out.print("내용 : ");
      content = Container.scanner.nextLine();
      if(content.trim().isEmpty())
      {
        System.out.println("내용을 입력해주세요");
        continue;
      }
      break;
    }
    articleService.modify(id,subject,content);
  }
  public void doDelete(Rq rq) {
    if (articleService.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }
    Map<String, String> params = rq.getParams();
    if (!params.containsKey("id")) {
      System.out.println("id값을 입력해주세요.");
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }
    Article article =articleService.findById(id);
    if (article==null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }
    articleService.remove(id);
    System.out.printf("%d번 게시물이 삭제되었습니다.\n",id);
  }
  public void doWrite() {
    String subject = "";
    String content = "";
    System.out.println("== 게시물 작성 ==");
    while(true){
      System.out.print("제목 : ");
      subject = Container.scanner.nextLine();
      if(subject.trim().isEmpty())
      {
        System.out.println("제목을 입력해주세요");
        continue;
      }
      break;
    }
    while(true){
      System.out.print("내용 : ");
      content = Container.scanner.nextLine();
      if(content.trim().isEmpty())
      {
        System.out.println("내용을 입력해주세요");
        continue;
      }
      break;
    }
    int id=articleService.save(subject,content);
    System.out.printf("%d번 게시물이 등록 되었습니다.\n", id);
  }
}
