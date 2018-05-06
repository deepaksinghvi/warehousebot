package com.iot.warehouse.warehousebot.service;

import com.iot.warehouse.warehousebot.entity.Bot;
import com.iot.warehouse.warehousebot.entity.RFID;
import com.iot.warehouse.warehousebot.repository.BotRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by i333127 on 05/05/18.
 */

@Service
public class BotServiceImpl implements BotService {

  @Autowired
  private BotRepository botRepository;

  @Override
  public List<Bot> getWareHouseBots() {
    return botRepository.findAllBot();
  }

  @Override
  public Bot getWareHouseBot(String botId) {
    Bot bot = botRepository.findBotById(botId);
    if (null == bot) {
      throw new RuntimeException(String.format("Bot %s does not exists", botId));
    }
    return bot;
  }

  @Override
  public Bot addNewBot(Bot bot) {
    int result = botRepository.insertBot(bot);
    if (result == 1) {
      return bot;
    } else {
      throw new RuntimeException("Bot creation unsuccessful");
    }
  }

  @Override
  public Bot updateBot(Bot bot) {
    int result = botRepository.updateBot(bot);
    if (result == 1) {
      return bot;
    } else {
      throw new RuntimeException("Bot update unsuccessful");
    }
  }

  @Override
  public int deleteBot(String botId) {
    return botRepository.deleteBotById(botId);
  }
}
