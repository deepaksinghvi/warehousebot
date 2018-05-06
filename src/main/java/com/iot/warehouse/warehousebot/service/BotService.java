package com.iot.warehouse.warehousebot.service;

import com.iot.warehouse.warehousebot.entity.Bot;
import java.util.List;

/**
 * Created by Deepak Singhvi on 05/05/18.
 */

public interface BotService {

  public List<Bot> getWareHouseBots();

  public Bot getWareHouseBot(String botId);

  public Bot addNewBot(Bot bot);

  public Bot updateBot(Bot bot);

  public int deleteBot(String botId);

}
