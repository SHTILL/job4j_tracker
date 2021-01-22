package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker() {
        this.init();
    }

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Item> getItemsList(ResultSet res) throws SQLException {
        List<Item> list = new ArrayList<>();
        while (res.next()) {
            list.add(new Item(res.getString("name")));
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try {
            Statement stmt = cn.createStatement();
            stmt.execute("insert into items(name) values('" + item.getName() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        try {
            Statement stmt = cn.createStatement();
            stmt.execute("update items set name = '" + item.getName() + "' where id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        try {
            Statement stmt = cn.createStatement();
            stmt.execute("delete from items where id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = null;
        try {
            Statement stmt = cn.createStatement();
            ResultSet res = stmt.executeQuery("select * from items");
            list = getItemsList(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = null;
        try {
            Statement stmt = cn.createStatement();
            ResultSet res = stmt.executeQuery("select * from items where name = '" + key + "';");
            list = getItemsList(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(String id) {
        try {
            Statement stmt = cn.createStatement();
            ResultSet res = stmt.executeQuery("select * from items where id = " + id + ";");
            if (res.next()) {
                return new Item(res.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
