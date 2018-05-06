package com.iot.warehouse.warehousebot;

import com.iot.warehouse.warehousebot.entity.Bot;
import com.iot.warehouse.warehousebot.entity.RFID;
import com.iot.warehouse.warehousebot.service.BotService;
import com.iot.warehouse.warehousebot.service.RFIDService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i333127 on 05/05/18.
 */

@RestController
public class WarehousebotController {

  @Autowired
  private BotService botService;

  @Autowired
  private RFIDService rfidService;

  @RequestMapping(method = RequestMethod.GET, value = "bot/{botId}")
  public ResponseEntity<Bot> getBot(@PathVariable("botId") String botId) {
    Bot response = botService.getWareHouseBot(botId);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/bot/getbots")
  public ResponseEntity<List<Bot>> getBots() {
    List<Bot> response = botService.getWareHouseBots();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/bot")
  public ResponseEntity<Bot> addNewBolt(@RequestBody Bot bot) {
    Bot response = botService.addNewBot(bot);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/bot")
  public ResponseEntity<Bot> updateBot(@RequestBody Bot bot) {
    Bot response = botService.updateBot(bot);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/bot/{botId}")
  public ResponseEntity<Integer> removeBot(@PathVariable("botId") String botId) {
    Integer result = botService.deleteBot(botId);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @RequestMapping(method = RequestMethod.GET, value = "rfid/{rfId}")
  public ResponseEntity<RFID> getRFID(@PathVariable("rfId") String rfId) {
    RFID response = rfidService.getWareHouseRDID(rfId);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


  @RequestMapping(method = RequestMethod.GET, value = "rfid/getrfids")
  public ResponseEntity<List<RFID>> getRFIDs() {
    List<RFID> response = rfidService.getWareHouseRFIDs();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/rfid")
  public ResponseEntity<RFID> addNewRFID(@RequestBody RFID rfid) {
    RFID response = rfidService.addNewRFID(rfid);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/rfid")
  public ResponseEntity<RFID> updateRFID(@RequestBody RFID rfid) {
    RFID response = rfidService.updateRFID(rfid);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/rfid/{rfId}")
  public ResponseEntity<Integer> removeRFID(@PathVariable("rfId") String rfId) {
    Integer result = rfidService.deleteRFID(rfId);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
