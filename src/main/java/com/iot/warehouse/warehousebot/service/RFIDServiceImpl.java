package com.iot.warehouse.warehousebot.service;

import com.iot.warehouse.warehousebot.entity.Bot;
import com.iot.warehouse.warehousebot.entity.RFID;
import com.iot.warehouse.warehousebot.repository.BotRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by i333127 on 06/05/18.
 */
@Service
public class RFIDServiceImpl implements RFIDService {

  @Autowired
  private BotRepository botRepository;

  @Override
  public List<RFID> getWareHouseRFIDs() {
    return botRepository.findAllRFID();
  }

  @Override
  public RFID getWareHouseRDID(String rfId) {
    RFID rfIDObj = botRepository.findRFIDById(rfId);
    if (null == rfIDObj) {
      throw new RuntimeException(String.format("RFID %s does not exists", rfId));
    }
    return rfIDObj;
  }

  @Override
  public RFID addNewRFID(RFID rfid) {
    int result = botRepository.insertRFID(rfid);
    if (result == 1) {
      return rfid;
    } else {
      throw new RuntimeException("RFID creation unsuccessful");
    }
  }

  @Override
  public RFID updateRFID(RFID rfid) {
    int result = botRepository.updateRFID(rfid);
    if (result == 1) {
      return rfid;
    } else {
      throw new RuntimeException("RFID update unsuccessful");
    }
  }

  @Override
  public int deleteRFID(String rfId) {
    return botRepository.deleteRFIDById(rfId);
  }
}
