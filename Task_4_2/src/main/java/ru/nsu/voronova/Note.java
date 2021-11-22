package ru.nsu.voronova;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
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
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }

    if (!(object instanceof Note note)) {
      return false;
    }

    String title = note.getTitle();
    String content = note.getContent();
    Date creationDate = note.getCreationDate();
    return this.title.equals(title) && this.content.equals(content) && this.creationDate.equals(creationDate);
  }
}
