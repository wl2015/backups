package com.cn.freemall.basic.exception;

/**
 * 数据解析错误产生的异常
 */
public class ValidateFailException extends IllegalArgumentException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ValidateFailException(String message) {
    super(new String("数据解析失败, 描述: [ " + message + " ]"));
  }

  /**
   * @param causeExp
   *          异常对象
   * @param str
   *          被解析的数据
   */
  public ValidateFailException(String message, Exception causeExp) {
    super(new String("数据解析失败, 描述: [ " + message + " ]"), causeExp);
  }

}
