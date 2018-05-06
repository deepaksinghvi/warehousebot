package com.iot.warehouse.warehousebot.repository;

import com.iot.warehouse.warehousebot.entity.Bot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by i333127 on 05/05/18.
 */
@Repository
public class BotRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  class BotRowMapper implements RowMapper<Bot> {

    @Override
    public Bot mapRow(ResultSet rs, int i) throws SQLException {
      Bot bot = new Bot(rs.getString("botId"));
      bot.setCurrLoc(rs.getString("currLoc"));
      bot.setIntermediateLoc(rs.getString("intermediateLoc"));
      bot.setEndLoc(rs.getString("endLoc"));
      return bot;
    }
  }

  public List<Bot> findAll() {
    return jdbcTemplate.query("select * from bot", new BotRowMapper());
  }

  public Bot findById(String botId) {
    return jdbcTemplate.queryForObject("select * from bot where botId=?", new Object[] {
            botId
        },
        new BeanPropertyRowMapper< Bot >(Bot.class));
  }
  public int deleteById(String botId) {
    return jdbcTemplate.update("delete from bot where botId=?", new Object[] {
        botId
    });
  }
  public int insert(Bot bot) {
    return jdbcTemplate.update("insert into bot (botId, currLoc, intermediateLoc, endLoc) " + "values(?,  ?, ?, ?)",
        new Object[] {
            bot.getBotId(), bot.getCurrLoc(), bot.getIntermediateLoc(), bot.getEndLoc()
        });
  }
  public int update(Bot bot) {
    return jdbcTemplate.update("update bot " + " set currLoc = ?, intermediateLoc = ?, endLoc = ? " + " where botId = ?",
        new Object[] {
            bot.getCurrLoc(), bot.getIntermediateLoc(), bot.getEndLoc(), bot.getBotId()
        });
  }
}
