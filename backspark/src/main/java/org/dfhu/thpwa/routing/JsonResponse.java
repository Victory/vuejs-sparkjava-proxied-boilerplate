package org.dfhu.thpwa.routing;

public class JsonResponse {

  private final static Object EMPTY_DATA = new Object();
  private final boolean success;
  private final String msg;
  private final Object data;

  public JsonResponse(boolean success, String msg) {
    this.success = success;
    this.msg = msg;
    this.data = EMPTY_DATA;
  }

  public JsonResponse(boolean success, String msg, Object data) {
    this.success = success;
    this.msg = msg;
    this.data = data;
  }
}
