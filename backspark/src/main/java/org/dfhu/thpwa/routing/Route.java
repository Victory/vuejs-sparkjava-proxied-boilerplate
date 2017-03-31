package org.dfhu.thpwa.routing;


public interface Route {
  /**
   * Get the url pattern for this route
   */
  String getPath();

  /**
   * get the HTTP request method
   */
  METHOD getMethod();

  /**
   * Add the route to spark
   */
  void addRoute();

  enum METHOD {
    GET,
    POST
  }
}
