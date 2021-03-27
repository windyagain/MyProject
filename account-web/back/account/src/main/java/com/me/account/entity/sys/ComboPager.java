package com.me.account.entity.sys;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ComboPager
  implements Serializable
{
  private long serialVersionUID = 1124L;
  private Pager pager = null;
  private List rs = null; //存放拿出来的页面数据 list集合
  private Map mp = null;  // 存放一些计算的其他数据

  public Pager getPager() { return this.pager; }

  public void setPager(Pager pager) {
    this.pager = pager; }

  public List getRs() {
    return this.rs; }

  public void setRs(List rs) {
    this.rs = rs;
  }

  public Map getMp() {
    return mp;
  }

  public void setMp(Map mp) {
    this.mp = mp;
  }
}