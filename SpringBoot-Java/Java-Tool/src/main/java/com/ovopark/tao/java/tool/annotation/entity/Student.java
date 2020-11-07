package com.ovopark.tao.java.tool.annotation.entity;

import com.ovopark.tao.java.tool.annotation.Format;
import com.ovopark.tao.java.tool.annotation.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.java.SimpleFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Label()
  private String name;

  @Label("出生日期")
  @Format(pattern = "yyyy/MM/dd")
  private Date born;

  @Label("分数")
  private double score;

  public Student(Date born, double score) {
    this.born = born;
    this.score = score;
  }

  public static void main(String[] args) throws ParseException {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Student s = new Student( sdf.parse("1998-04-24"),80.9d);
//    System.out.println(s.toString());

    String a = "  adsfdsak      kkks";
    a = a.trim();
    System.out.println(a.substring(0,a.indexOf(" ")));
  }
}
