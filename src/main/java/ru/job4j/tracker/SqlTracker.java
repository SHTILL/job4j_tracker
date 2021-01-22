package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class SqlTracker implements Store {
    private Connection cn;

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextInt() + (int)System.currentTimeMillis());
    }

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
            Item item = new Item(res.getString("name"));
            item.setId(res.getString("id"));
            list.add(item);
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
        try (PreparedStatement stmt = cn.prepareStatement("insert into items(id,name) values(?,?);")) {
            String id = this.generateId();
            item.setId(id);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, item.getName());
            if (stmt.executeUpdate() == 0)
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement stmt = cn.prepareStatement("update items set name = ? where id = ?;")) {
            stmt.setString(1, item.getName());
            stmt.setInt(2, Integer.parseInt(id));
            if (stmt.executeUpdate() == 0)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        try (PreparedStatement stmt = cn.prepareStatement("delete from items where id = ?;")) {
            stmt.setInt(1, Integer.parseInt(id));
            if (stmt.executeUpdate() == 0)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = null;
        try (Statement stmt = cn.createStatement()) {
            ResultSet res = stmt.executeQuery("select * from items;");
            list = getItemsList(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = null;
        try (PreparedStatement stmt = cn.prepareStatement("select * from items where name = ?;")) {
            stmt.setString(1, key);
            ResultSet res = stmt.executeQuery();
            list = getItemsList(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(String id) {
        try (PreparedStatement stmt = cn.prepareStatement("select * from items where id = ?;")) {
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                Item item = new Item(res.getString("name"));
                item.setId(res.getString("id"));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
