package org.dfhu.thpwa.routing;

import static spark.Spark.halt;

public class Halting {
  public static void haltNotFound() {
    halt(404, "{\"success\":false}");
  }

  public static void haltNotImplemented() {
    halt(501, "{\"success\":false}");
  }
}
