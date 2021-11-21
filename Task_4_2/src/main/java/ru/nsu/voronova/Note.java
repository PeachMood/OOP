package ru.nsu.voronova;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note implements Comparable<Note> {
  private final String title;
  private final String content;
  private final Date creationDate;

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
    this.creationDate = new Date();
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  @Override
  public String toString() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    String date = formatter.format(creationDate);
    return "Title: \"" + title + "\"\n" +
            "Date of creation: " + date + "\n" +
            "Note: \"" + content + "\"";
  }

  @Override
  public int compareTo(Note anotherNote) {
    Date anotherDate = anotherNote.creationDate;
    if (creationDate.after(anotherDate))
      return 1;
    if (creationDate.before(anotherDate))
      return -1;
    return 0;
  }
}
