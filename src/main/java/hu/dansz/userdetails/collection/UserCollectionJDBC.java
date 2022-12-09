package hu.dansz.userdetails.collection;

import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.db.DB;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserCollectionJDBC implements IUserCollection {
    @Override
    public List<User> getUsers() {
        DB db = new DB();
        try {
            ResultSet resultSet =  db.executeQuerySql("SELECT firstname, lastname FROM users");
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
