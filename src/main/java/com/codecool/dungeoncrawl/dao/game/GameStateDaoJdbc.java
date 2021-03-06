package com.codecool.dungeoncrawl.dao.game;

import com.codecool.dungeoncrawl.dao.player.PlayerColumns;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.HashMap;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id, name_of_save) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, state.getPlayer().getId());
            statement.setString(4, state.getNameOfSave());
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
            String sql = "UPDATE game_state SET current_map = ?, saved_at = ?, name_of_save = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setString(3, state.getNameOfSave());
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

            int gameStateId = resultSet.getInt(GameStateColumns.ID.getName());
            String currentMap = resultSet.getString(GameStateColumns.CURRENT_MAP.getName());
            Timestamp savedAt = resultSet.getTimestamp(GameStateColumns.SAVED_AT.getName());
            int playerId = resultSet.getInt(GameStateColumns.PLAYER_ID.getName());
            String nameOfSave = resultSet.getString(GameStateColumns.NAME_OF_SAVE.getName());

            GameState gameState = new GameState(currentMap, new Date(savedAt.getTime()), new PlayerModel());
            gameState.setId(gameStateId);
            gameState.setNameOfSave(nameOfSave);
            gameState.setPlayerId(playerId);

            return gameState;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<Integer, String> getIdAndName(){
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT id, name_of_save FROM game_state GROUP BY game_state.id";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()){
                return null;
            }

            HashMap<Integer, String> gameSaves = new HashMap<>();

            do{
                int gameStateId = resultSet.getInt(GameStateColumns.ID.getName());
                String nameOfSave = resultSet.getString(GameStateColumns.NAME_OF_SAVE.getName());
                gameSaves.put(gameStateId, nameOfSave);
            }while (resultSet.next());

            return gameSaves;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private PlayerModel buildPlayerModel(ResultSet resultSet) throws SQLException {
        String playerName = resultSet.getString(PlayerColumns.PLAYER_NAME.getName());
        int playerHp = resultSet.getInt(PlayerColumns.HP.name());
        int x = resultSet.getInt(PlayerColumns.X.name());
        int y = resultSet.getInt(PlayerColumns.Y.name());
        int damage = resultSet.getInt(PlayerColumns.DAMAGE.name());
        String tileName = resultSet.getString(PlayerColumns.TILE_NAME.name());

        PlayerModel playerModel = new PlayerModel(playerName, x, y);
        playerModel.setDamage(damage);
        playerModel.setHp(playerHp);
        playerModel.setTileName(tileName);

        return playerModel;
    }
}
