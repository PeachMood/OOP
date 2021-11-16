package ru.nsu.voronova;

import java.util.Date;

public class Note {
  private final String title;
  private final String content;
  private final Date creationData;

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
    this.creationData = new Date();
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Date getCreationData() {
    return creationData;
  }
}
