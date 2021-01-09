package me.mobcoins.managers;

import me.mobcoins.database.DatabaseConnector;
import me.mobcoins.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class UserManager {
    private final DatabaseConnector databaseConector;
    private final ArrayList<User> userArrayList;

    public UserManager(DatabaseConnector databaseConnector) {
        this.databaseConector = databaseConnector;
        userArrayList = new ArrayList<>();
        createTables();
    }

    private final String TABLE = "mobcoins";
    private final String INSERT = "INSERT INTO " + TABLE + " (uuid, coins) VALUES (?, ?);";
    private final String SELECT = "SELECT * FROM " + TABLE + " WHERE uuid = ?;";

    private void createTables() {
        try (PreparedStatement ps = connection().prepareStatement("CREATE TABLE IF NOT EXISTS " + TABLE + "("
                + "`uuid` VARCHAR(16), "
                + "`coins` INTEGER);")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUUID(UUID uuid){
        for (User user : userArrayList)
            if(user.getUuid().equals(uuid))return user;

        return null;
    }

    public void saveUser(User user) {
        try (PreparedStatement ps = connection().prepareStatement(INSERT)) {
            ps.setString(1, user.getUuid().toString());
            ps.setInt(2, user.getCoins());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(User user) {
        try (PreparedStatement ps = connection().prepareStatement("DELETE FROM " + TABLE + " WHERE uuid = ?;")) {
            ps.setString(1, user.getUuid().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadUser(UUID uuid) {
        try {
            PreparedStatement statement = connection().prepareStatement(SELECT);
            statement.setString(1, uuid.toString());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                userArrayList.add(new User(
                        uuid,
                        result.getInt("coins")
                ));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection connection() {
        return databaseConector.getConnection();
    }
}
