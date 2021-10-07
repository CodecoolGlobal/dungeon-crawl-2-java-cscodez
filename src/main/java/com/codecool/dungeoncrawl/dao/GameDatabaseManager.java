package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.dao.game.GameStateDao;
import com.codecool.dungeoncrawl.dao.game.GameStateDaoJdbc;
import com.codecool.dungeoncrawl.dao.player.PlayerDao;
import com.codecool.dungeoncrawl.dao.player.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameDao = new GameStateDaoJdbc(dataSource);
    }

    public PlayerModel savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);
        return model;
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DATA_BASE");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public GameState makeGameState(PlayerModel playerModel, String saveAs, String gameMap) {
        Timestamp date = new Timestamp(System.currentTimeMillis());

        GameState gameState = new GameState(gameMap, new Date(date.getTime()), playerModel);
        gameState.setNameOfSave(saveAs);
        return gameState;
    }

    public void saveGameState(GameState gameState) {
        gameDao.add(gameState);
    }
}
