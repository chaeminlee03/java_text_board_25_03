package com.sbs.java.text_board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
  private static void makeArticleTestData(List<Article> articles){
    IntStream.rangeClosed(1,3).forEach(i->articles.add(new Article(i,"제목"+i,"내용"+i)));
  }
  public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);
    List<Article> articles= new ArrayList<>();
    makeArticleTestData(articles);

    int lastArticleId=articles.get(articles.size()-1).id;
    while(true){
      System.out.printf("명령어) ");
      String cmd= sc.nextLine();
      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = sc.nextLine();
        System.out.print("내용 : ");
        String content = sc.nextLine();
        int id = ++lastArticleId;
        Article article=new Article(id,subject,content);
        articles.add(article);
        System.out.printf("%d번 게시물이 등록 되었습니다.\n",article.id);
      }
      else if(cmd.equals("/usr/article/list")) {
        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }
        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");
        // 역순 출력
        for(int i = articles.size() - 1; i >= 0; i--) {
          System.out.printf("%d | %s\n", articles.get(i).id, articles.get(i).subject);
        }
      }
      else if(cmd.equals("exit")) {
        System.out.println("게시판 프로그램을 종료합니다.");
        break;
      }
      else {
        System.out.println("명령어를 잘 못 입력하였습니다.");
      }
            }
    System.out.println("== 자바 텍스트 게시판 종료==");
    sc.close();
    }
              }
