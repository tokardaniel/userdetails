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
        String sql = "SELECT users.firstname, users.lastname, users.email, address.cim " +
                "FROM users LEFT JOIN address ON users.address_id=address.id";
        try {
            ResultSet resultSet =  db.executeQuerySql(sql);
            List<User> users = new LinkedList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
