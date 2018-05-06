package com.iot.warehouse.warehousebot.repository;

import com.iot.warehouse.warehousebot.entity.Bot;
import com.iot.warehouse.warehousebot.entity.RFID;
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

  class RFIDRowMapper implements RowMapper<RFID> {

    @Override
    public RFID mapRow(ResultSet rs, int i) throws SQLException {
      RFID bot = new RFID(rs.getString("rfid"));
      bot.setLocId(rs.getString("locId"));
      return bot;
    }
  }

  public List<Bot> findAllBot() {
    return jdbcTemplate.query("select * from bot", new BotRowMapper());
  }

  public Bot findBotById(String botId) {
    return jdbcTemplate.queryForObject("select * from bot where botId=?", new Object[] {
            botId
        },
        new BeanPropertyRowMapper< Bot >(Bot.class));
  }
  public int deleteBotById(String botId) {
    return jdbcTemplate.update("delete from bot where botId=?", new Object[] {
        botId
    });
  }
  public int insertBot(Bot bot) {
    return jdbcTemplate.update("insert into bot (botId, currLoc, intermediateLoc, endLoc) " + "values(?,  ?, ?, ?)",
        new Object[] {
            bot.getBotId(), bot.getCurrLoc(), bot.getIntermediateLoc(), bot.getEndLoc()
        });
  }
  public int updateBot(Bot bot) {
    return jdbcTemplate.update("update bot " + " set currLoc = ?, intermediateLoc = ?, endLoc = ? " + " where botId = ?",
        new Object[] {
            bot.getCurrLoc(), bot.getIntermediateLoc(), bot.getEndLoc(), bot.getBotId()
        });
  }

  public List<RFID> findAllRFID() {
    return jdbcTemplate.query("select * from rfidtable", new RFIDRowMapper());
  }

  public RFID findRFIDById(String rfId) {
    return jdbcTemplate.queryForObject("select * from rfidtable where rfId=?", new Object[] {
            rfId
        },
        new BeanPropertyRowMapper< RFID >(RFID.class));
  }

  public int deleteRFIDById(String rfId) {
    return jdbcTemplate.update("delete from rfidtable where rfId=?", new Object[] {
        rfId
    });
  }
  public int insertRFID(RFID rfid) {
    return jdbcTemplate.update("insert into rfidtable (rfId, locId) " + "values(?,  ?)",
        new Object[] {
            rfid.getRfId(), rfid.getLocId()
        });
  }

  public int updateRFID(RFID rfid) {
    return jdbcTemplate.update("update rfidtable " + " set locId = ?" + " where rfId = ?",
        new Object[] {
            rfid.getLocId(), rfid.getRfId()
        });
  }
}
