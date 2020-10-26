package com.ovopark.tao.java.juc.threadlocal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ZZTest {

      public static void main(String args[]) {
        String str = "";
        String pattern = "https://odds.500.com/fenxi/shuju-927392.shtml";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
      }

    }

