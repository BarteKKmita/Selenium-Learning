package com.learning.listeners;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class ListenerTest {

  @Test
  void test1() {
    System.out.println("Doing first test.");
  }


  @Test
  void test2() {
    System.out.println("Doing second test.");
  }


  @Test
  void test3() {
    System.out.println("Doing third test.");
  }

}
