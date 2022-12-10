package hu.dansz.userdetails;

import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.collection.UserCollectionJDBC;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import java.util.List;
import java.util.Set;

public class UserPage extends WebPage {

    private static final long serialVersionUID = 1L;

    public UserPage(PageParameters parameters) {
        super(parameters);

        StringValue id  = parameters.get("id");

        UserCollectionJDBC userCollectionJDBC = new UserCollectionJDBC();
        User user = userCollectionJDBC.findUserById(Integer.parseInt(id.toString()));

        add(new Label("firstname", user.getFirstname()));
        add(new Label("lastname", user.getLastname()));
    }

}
