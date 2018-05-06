package com.iot.warehouse.warehousebot.service;

import com.iot.warehouse.warehousebot.entity.Bot;
import com.iot.warehouse.warehousebot.entity.RFID;
import java.util.List;

/**
 * Created by i333127 on 06/05/18.
 */
public interface RFIDService {

  public List<RFID> getWareHouseRFIDs();

  public RFID getWareHouseRDID(String rfId);

  public RFID addNewRFID(RFID bot);

  public RFID updateRFID(RFID bot);

  public int deleteRFID(String rfId);
}
