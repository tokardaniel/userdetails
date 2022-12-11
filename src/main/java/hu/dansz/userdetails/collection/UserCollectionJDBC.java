package hu.dansz.userdetails.collection;

import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.db.DB;

import java.sql.PreparedStatement;
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
        String sql = "SELECT users.id, users.firstname, users.lastname, users.email, users.address_id, address.cim " +
                "FROM users LEFT JOIN address ON users.address_id=address.id WHERE users.id=? LIMIT 1";

        try (PreparedStatement statement = db.getPreparedStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setCim(resultSet.getString("cim"));
                user.setAddress_id(resultSet.getInt("address_id"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(User user) {
        DB db = new DB();
        String sql = "UPDATE users SET email=?, address_id=? WHERE id=?";

        try (PreparedStatement statement = db.getPreparedStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setInt(2, user.getAddress_id());
            statement.setInt(3, user.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
