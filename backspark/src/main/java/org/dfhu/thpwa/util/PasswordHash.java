package org.dfhu.thpwa.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;


public class PasswordHash {
  private static final String DELIMITER = "#";
  private static final String CUR_VERSION = "V1";
  private final SecureRandom rand = new SecureRandom();

  public boolean match(String plainText, String hash) {
    String[] bits = hash.split(DELIMITER);

    return matchHashStringV1(plainText, bits[1], bits[2]);
  }

  public String getHashString(String plain) {
    return getHashStringV1(plain);
  }

  private boolean matchHashStringV1(String plain, String salt, String bcryptHash) {
    int bcryptMaxPasswordLength = 50;
    String result = plain + salt;
    result = DigestUtils.sha256Hex(result)
        .substring(0, bcryptMaxPasswordLength);

    return BCrypt.checkpw(result, bcryptHash);
  }

  private String getHashStringV1(String plain) {
    int saltLength = 16;
    int bcryptMaxPasswordLength = 50;
    int rounds = 12;

    String salt =
        DigestUtils.sha256Hex(rand.nextInt() + "")
            .substring(0, saltLength);

    String result = plain + salt;
    result = DigestUtils.sha256Hex(result)
        .substring(0, bcryptMaxPasswordLength);

    result = BCrypt.hashpw(result, BCrypt.gensalt(rounds));
    return CUR_VERSION + DELIMITER + salt + DELIMITER + result;
  }
}
