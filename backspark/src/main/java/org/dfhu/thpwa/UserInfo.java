package org.dfhu.thpwa;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {
  public boolean isLoggedIn = false;
  public String userName = "Guest";
  public Set<String> groups = new HashSet<>();
}
