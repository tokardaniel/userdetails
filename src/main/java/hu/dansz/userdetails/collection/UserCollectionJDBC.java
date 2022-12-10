package hu.dansz.userdetails.collection;

import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.db.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserCollectionJDBC implements IUserCollection {
    @Override
    public List<User> getUsers() {
        DB db = new DB();
        String sql = "SELECT users.id, users.firstname, users.lastname, users.email, address.cim " +
                "FROM users LEFT JOIN address ON users.address_id=address.id";
        try {
            ResultSet resultSet =  db.executeQuerySql(sql);
            List<User> users = new LinkedList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setCim(resultSet.getString("cim"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserById(int id) {
        DB db = new DB();
        String sql = String.format("SELECT users.id, users.firstname, users.lastname, users.email, address.cim " +
                "FROM users LEFT JOIN address ON users.address_id=address.id WHERE users.id=%s LIMIT 1", id);
        try {
            ResultSet resultSet = db.executeQuerySql(sql);
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setCim(resultSet.getString("cim"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        DB db = new DB();
        String sql = String.format("UPDATE users SET email='%s', address_id=%s WHERE id=%s",
                user.getEmail(), user.getAddress_id(), user.getId());
        try {
            db.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
