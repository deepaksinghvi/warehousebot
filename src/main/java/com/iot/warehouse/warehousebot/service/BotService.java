package com.iot.warehouse.warehousebot.service;

import com.iot.warehouse.warehousebot.entity.Bot;
import com.iot.warehouse.warehousebot.repository.BotRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by i333127 on 05/05/18.
 */

public interface BotService {

  public List<Bot> getWareHouseBots();

  public Bot getWareHouseBot(String botId);

  public Bot addNewBot(Bot bot);

  public Bot updateBot(Bot bot);

  public int deleteBot(String botId);

}
