package com.sbs.java.text_board.base;
import com.sbs.java.text_board.util.Util;

import java.util.Map;

public class Rq {
    private String url;
    private Map<String,String> params;
    private String urlPath;
    public Rq(String url){
      this.url=url;
      this.params= Util.getParamsFromUrl(this.url);
      this.urlPath=Util.getPathFromUrl(this.url);
    }
    public Map<String,String> getParams(){
      return this.params;
    }
    public String getUrlPath(){
    return this.urlPath;
  }
}
