package com.sbs.java.text_board.article.service.dto;

public class Article {
  public int id;
  public String subject;
  public String content;
  public Article(int id, String subject, String content) {
    this.id=id;
    this.subject=subject;
    this.content=content;
  }
  @Override
  public String toString(){
    return "{id :%d, subject:\"%s\",content:\"%s\"".formatted(this.id,this.subject,this.content);
  }
}
