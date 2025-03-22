package com.sbs.java.text_board.container;

import com.sbs.java.text_board.controller.ArticleController;
import com.sbs.java.text_board.service.ArticleService;

import java.util.Scanner;

public class Container {
  public static Scanner scanner;
  public static ArticleController articleController;
  public static ArticleService articleService;
  static{ //프로그램 실행시 딱한번만 실행
          scanner=new Scanner(System.in);
          articleController=new ArticleController();
          articleService = new ArticleService();
  }
}
