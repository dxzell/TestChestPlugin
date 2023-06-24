package com.dxzell.testchest.Database;

import com.dxzell.testchest.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public void connect() throws SQLException { //database connection
        connection = DriverManager.getConnection(
                "jdbc:postgresql://" + Config.getHost() + ":" + Config.getPort() + "/" + Config.getDatabase(),
                Config.getUsername(),
                Config.getPassword()
        );

        //if not exist already -> create tables
        Queries.createApolloLootTable();
        Queries.createPoseidonLootTable();
        Queries.createHeroLootTable();
        Queries.createPlayerDataTable();
        Queries.createWinnings();
    }

    public static boolean isConnected() {
        return connection != null;
    }

    public void disconnect() { //disconnect connection
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
