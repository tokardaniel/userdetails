package hu.dansz.userdetails;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;


public class UserPage extends WebPage {

    private static final long serialVersionUID = 1L;

    public UserPage(PageParameters parameters) {
        super(parameters);

        StringValue id  = parameters.get("id");

        add(new UserForm("userForm", Integer.parseInt(id.toString())));
    }

}
