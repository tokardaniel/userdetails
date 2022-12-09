package hu.dansz.userdetails;

import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.collection.IUserCollection;
import hu.dansz.userdetails.collection.UserCollectionJDBC;
import hu.dansz.userdetails.db.DB;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		UserCollectionJDBC userCollectionJDBC = new UserCollectionJDBC();
		List<User> users = userCollectionJDBC.getUsers();
		add(new ListView<User>("persons", users) {
			@Override
			protected void populateItem(ListItem<User> item) {
				final User user = item.getModelObject();
				item.add(new Label("firstname", user.getFirstname()));
				item.add(new Label("lastname", user.getLastname()));
				item.add(new Label("email", user.getEmail()));
				item.add(new Label("cim", user.getCim()));
			}
		});
		// TODO Add your page's components here

	}
}
