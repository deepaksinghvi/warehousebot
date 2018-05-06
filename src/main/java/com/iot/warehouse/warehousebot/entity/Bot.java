package com.iot.warehouse.warehousebot.entity;


import java.util.UUID;

/**
 * Created by i333127 on 05/05/18.
 */

public class Bot {
  private String botId;
  private String currLoc;
  private String intermediateLoc;
  private String endLoc;

  public Bot(){
    this(UUID.randomUUID().toString());
  }

  public Bot(String botId){
    this.botId = botId;
  }
  public Bot(String botId, String currLoc, String intermediateLoc, String endLoc) {
    this.botId = botId;
    this.currLoc = currLoc;
    this.intermediateLoc = intermediateLoc;
    this.endLoc = endLoc;
  }

  public String getBotId() {
    return botId;
  }

  public void setBotId(String botId) {
    this.botId = botId;
  }

  public String getCurrLoc() {
    return currLoc;
  }

  public void setCurrLoc(String currLoc) {
    this.currLoc = currLoc;
  }

  public String getIntermediateLoc() {
    return intermediateLoc;
  }

  public void setIntermediateLoc(String intermediateLoc) {
    this.intermediateLoc = intermediateLoc;
  }

  public String getEndLoc() {
    return endLoc;
  }

  public void setEndLoc(String endLoc) {
    this.endLoc = endLoc;
  }


  @Override
  public String toString() {
    return "Bot{" +
        "botId='" + botId + '\'' +
        ", currLoc='" + currLoc + '\'' +
        ", intermediateLoc='" + intermediateLoc + '\'' +
        ", endLoc='" + endLoc + '\'' +
        '}';
  }
}
