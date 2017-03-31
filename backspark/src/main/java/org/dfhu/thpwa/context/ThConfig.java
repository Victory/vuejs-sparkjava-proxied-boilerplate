package org.dfhu.thpwa.context;

public class ThConfig {
  private final boolean isDev;
  private final String mongoUri;

  public ThConfig(boolean isDev, String mongoUri) {
    this.isDev = isDev;
    this.mongoUri = mongoUri;
  }

  public String getMongoUri() {
    return mongoUri;
  }
}
