package com.iot.warehouse.warehousebot.entity;

/**
 * Created by i333127 on 06/05/18.
 */
public class RFID {

  private String rfId;

  private String locId;

  public RFID() {}

  public RFID(String rfId){
    this.rfId = rfId;
  }

  public void RFIF(String rfId, String locId) {
    this.rfId = rfId;
    this.locId = locId;
  }

  public String getRfId() {
    return rfId;
  }

  public void setRfId(String rfId) {
    this.rfId = rfId;
  }

  public String getLocId() {
    return locId;
  }

  public void setLocId(String locId) {
    this.locId = locId;
  }

  @Override
  public String toString() {
    return "RFID{" +
        "rfId='" + rfId + '\'' +
        ", locId='" + locId + '\'' +
        '}';
  }
}
