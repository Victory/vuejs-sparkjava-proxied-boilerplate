package org.dfhu.thpwa.approutes;

import org.dfhu.thpwa.UserInfo;
import org.dfhu.thpwa.context.ThSession;
import org.dfhu.thpwa.routing.JsonResponse;
import org.dfhu.thpwa.routing.JsonRoute;
import org.dfhu.thpwa.routing.Route;

public class GetUserInfoRoute extends JsonRoute implements Route {

  @Override
  public String getPath() {
    return "/user-info";
  }

  @Override
  public METHOD getMethod() {
    return METHOD.GET;
  }

  @Override
  public JsonResponse getJsonResponse(ThSession session) {
    return new JsonResponse(true, "", new UserInfo());
  }
}
