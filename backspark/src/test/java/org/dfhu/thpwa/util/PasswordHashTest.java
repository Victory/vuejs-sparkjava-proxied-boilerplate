package org.dfhu.thpwa.util;

import static org.junit.Assert.assertTrue;

import java.security.SecureRandom;

import org.junit.Test;


public class PasswordHashTest {
  SecureRandom rnd = new SecureRandom();

  @Test
  public void testHashIsInCorrectFormat() {
    PasswordHash passwordHash = new PasswordHash();
    String pw = rnd.nextInt() + "";
    String hashString = passwordHash.getHashString(pw);
    String[] bits = hashString.split("#");
    assertTrue(bits.length == 3);
  }

  @Test
  public void testHashUnHash() {
    PasswordHash passwordHash = new PasswordHash();
    String pw = rnd.nextInt() + "";
    String hashString = passwordHash.getHashString(pw);
    boolean match = passwordHash.match(pw, hashString);

    String msg = "Did not match with password: " + pw + " " + hashString;
    assertTrue(msg, match);
  }
}