package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (id, current_map, saved_at, player_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(GameState state) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "UPDATE game_state SET current_map = ?, saved_at = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM game_state JOIN player p on p.id = game_state.player_id WHERE game_state.id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()){
                return null;
            }

            int gameStateId = resultSet.getInt(1);
            String currentMap = resultSet.getString(2);
            Timestamp savedAt = resultSet.getTimestamp(3);
            int playerId = resultSet.getInt(4);

            String playerName = resultSet.getString(5);
            int playerHp = resultSet.getInt(6);
            int x = resultSet.getInt(7);
            int y = resultSet.getInt(8);
            int damage = resultSet.getInt(9);
            String tileName = resultSet.getString(10);

            PlayerModel playerModel = new PlayerModel(playerName, x, y);
            playerModel.setDamage(damage);
            playerModel.setHp(playerHp);
            playerModel.setTileName(tileName);


            GameState gameState = new GameState(currentMap, new Date(savedAt.getTime()), playerModel);
            gameState.setId(gameStateId);

            return gameState;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll() {
        return null;
    }
}
