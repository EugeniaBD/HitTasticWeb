package com.repository;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static Connection connection;

    private static void initiateDb() throws SQLException, ClassNotFoundException, IOException {
        createStatement().execute(
                "CREATE TABLE users (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	username TEXT,\n"
                + "	\"password\" TEXT,\n"
                + "	role TEXT"
                + ");");

        createStatement().execute(
                "CREATE TABLE point_of_interests (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	name TEXT,\n"
                + "	\"type\" TEXT,\n"
                + "	description TEXT,\n"
                + "     borough TEXT,\n"
                + "	location TEXT,\n"
                + "	\"like\" NUMERIC DEFAULT (0) NOT NULL\n"
                + ");");

        createStatement().execute(
                "CREATE TABLE comments (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	authorise BOOLEAN DEFAULT FALSE,\n"
                + "	\"text\" TEXT,\n"
                + "	created_at DATE,\n"
                + "	updated_at DATE,\n"
                + "	point_of_interest_id INTEGER NOT NULL,\n"
                + "	CONSTRAINT comments_FK FOREIGN KEY (point_of_interest_id) REFERENCES point_of_interests(id) ON DELETE CASCADE ON UPDATE CASCADE\n"
                + ");"
        );


        createStatement().execute(
                "INSERT INTO users (username, \"password\", role) VALUES\n"
                + " ('admin', 'admin123', 'Admin'),\n"
                + " ('user01', 'password1', 'User'),\n"
                + " ('user02', 'password2', 'User'),\n"
                + " ('user03', 'password3', 'User'),\n"
                + " ('user04', 'password4', 'User');");

        createStatement().execute(
                "INSERT INTO point_of_interests (name, \"type\", description, borough, location, \"like\") VALUES\n"
                + " ('Dracula Castle', 'Castle','description', 'Bran Brasov', 'Romania', 0),\n"
                + " ('Peles Castle', 'Castle','description', 'Sinaia Prahova', 'Romania', 0),\n"
                + " ('Corvin Castle', 'Castle','description', 'Hunedoara','Romania', 0),\n"
                + " ('Biertan Fortified Church', 'Church','description', 'Biertan Sibiu', 'Romania', 0),\n"
                + " ('Stavropoleos Church', 'Church','description', 'Bucharest','Romania', 0),\n"
                + " ('Merry Cemetery', 'Monument','description', 'Sapanta, Maramures', 'Romania', 0),\n"
                + " ('Palace of the Parliament', 'Monument','description', 'Bucharest', 'Romania', 0),\n"
                + " ('Sibiu Lutheran Cathedral', 'Church','description', 'Sibiu', 'Romania', 0),\n"
                + " ('Transfagarasan Highway', 'Monument','description', 'Arges County', 'Romania', 0),\n"
                + " ('Voronet Monastery', 'Church','description', 'Gura Humorului, Suceava', 'Romania', 0),\n"
                + " ('Bucharest University', 'School','description', 'Bucharest', 'Romania', 0),\n"
                + " ('Babes-Bolyai University', 'School','description', 'Cluj-Napoca', 'Romania', 0),\n"
                + " ('Caru cu Bere', 'Restaurant', 'description','Bucharest', 'Romania', 0),\n"
                + " ('Hanul lui Manuc', 'Restaurant','description', 'Bucharest', 'Romania', 0),\n"
                + " ('Therme Bucharest', 'Recreation','description', 'Bucharest', 'Romania', 0),\n"
                + " ('Casa Capsa', 'Hotel','description', 'Bucharest', 'Romania', 0),\n"
                + " ('Hotel Continental', 'Hotel','description', 'Timisoara', 'Romania', 0),\n"
                + " ('Espresso Studio', 'Coffee_Shop','description', 'Timisoara', 'Romania', 0),\n"
                + " ('La Mama', 'Restaurant','description', 'Cluj-Napoca', 'Romania', 0);"
        );

        createStatement().execute(
                "INSERT INTO comments (id, authorise, text, created_at, updated_at, point_of_interest_id) VALUES\n"
                + "	 (1,0, 'Nice', '2024-02-01', '2024-01-05', 1),\n"
                + "	 (2,0, 'Good one', '2024-02-02', '2024-01-03', 1),\n"
                + "	 (3,0, 'Very Nice', '2024-02-07', '2024-01-10', 2);");

        System.out.println("database created");

    }

    public static void createStatement(String select__from_users_u_where_uusername___an) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        if (connection == null) {
            Class.forName("org.sqlite.JDBC");
            File file = new File("C:\\Users\\eugen\\OneDrive\\Documents\\NetBeansProjects\\Hitastic (with updates)\\HitTaskticWeb (6)\\HitTaskticWeb\\hitastic.db");
            boolean isInitiate = false;
            System.out.printf("is file exists: %s\n", file.exists());
            if (!file.exists()) {
                isInitiate = file.createNewFile();
                System.out.printf("creating new file: %s\n", isInitiate);
            }
            System.out.println(file.getAbsoluteFile());
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", file.getAbsolutePath()));
            if (isInitiate) {
                initiateDb();
            }
            System.out.println("connection created");
        }
        return connection;
    }

    public static Statement createStatement() throws SQLException, ClassNotFoundException, IOException {
        return getConnection().createStatement();
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException, ClassNotFoundException, IOException {
        return getConnection().prepareStatement(sql);
    }

    public static PreparedStatement prepareInsertStatement(String sql) throws SQLException, ClassNotFoundException, IOException {
        return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

}
