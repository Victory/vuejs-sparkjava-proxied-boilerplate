package org.dfhu.thpwa.routing;

import com.google.gson.Gson;
import org.dfhu.thpwa.context.ThSession;
import spark.Request;
import spark.Response;
import spark.Spark;

public abstract class JsonRoute extends RouteAdder<JsonRoute> implements Route {

  /**
   * Get an object that you can Gson.toJson()
   */
  public abstract JsonResponse getJsonResponse(ThSession session);

  @Override
  public void doPost(RouteAdder<JsonRoute> routeAdder) {
    Spark.post("/api" + getPath(),
        "application/json",
        (req, res) -> {
          res.header("Content-Type", "application/json");
          ThSession session = getSession(req, res);
          return new Gson().toJson(getJsonResponse(session));
        });
  }

  @Override
  public void doGet(RouteAdder<JsonRoute> routeAdder) {
    Spark.get("/api" + getPath(),
        "application/json",
        (req, res) -> {
          res.header("Content-Type", "application/json");
          ThSession session = getSession(req, res);
          return new Gson().toJson(getJsonResponse(session));
        });
  }

  private ThSession getSession(Request req, Response res) {
    return new ThSession(req, res);
  }
}
