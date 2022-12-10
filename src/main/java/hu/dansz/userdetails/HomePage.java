package hu.dansz.userdetails;

import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.collection.UserCollectionJDBC;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.event.IEventSource;
import org.apache.wicket.markup.html.HTML5Attributes;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.parser.filter.WicketRemoveTagHandler;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import java.util.List;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		UserCollectionJDBC userCollectionJDBC = new UserCollectionJDBC();
		List<User> users = userCollectionJDBC.getUsers();
		add(new ListView<>("persons", users) {
			@Override
			protected void populateItem(ListItem<User> item) {
				final User user = item.getModelObject();
				item.add(new Label("firstname", user.getFirstname()));
				item.add(new Label("lastname", user.getLastname()));
				item.add(new Label("email", user.getEmail()));
				item.add(new Label("cim", user.getCim()));
				item.add(new StatelessLink<Object>("userEditLink") {
					@Override
					public void onClick() {
						PageParameters pageParameters = new PageParameters();
						pageParameters.add("id", user.getId());
						setResponsePage(UserPage.class, pageParameters);
					}
				});
			}
		});
	}
}
