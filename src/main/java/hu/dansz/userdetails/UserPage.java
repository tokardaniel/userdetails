package hu.dansz.userdetails;

import hu.dansz.userdetails.POJO.Address;
import hu.dansz.userdetails.POJO.User;
import hu.dansz.userdetails.collection.AddressCollectionJDBC;
import hu.dansz.userdetails.collection.UserCollectionJDBC;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import java.util.List;


public class UserPage extends WebPage {

    private static final long serialVersionUID = 1L;

    public UserPage(PageParameters parameters) {
        super(parameters);

        StringValue id  = parameters.get("id");

        add(new UserForm("userForm", Integer.parseInt(id.toString())));
    }

    public class UserForm extends Form<Object> {
        private TextField firstnameField;
        private TextField lastnameField;
        private TextField emailField;
        private IModel<Address> drIModel;
        private ChoiceRenderer<Address> renderAddresSelect;
        private DropDownChoice<Address> dropDownChoice;
        private User user;

        public UserForm(String id, int userId) {
            super(id);

            final UserCollectionJDBC userCollectionJDBC = new UserCollectionJDBC();
            user = userCollectionJDBC.findUserById(userId);

            final AddressCollectionJDBC addressCollectionJDBC = new AddressCollectionJDBC();
            final List<Address> addresses = addressCollectionJDBC.getAddresses();

            firstnameField = new TextField("firstname", Model.of(user.getFirstname()));
            lastnameField = new TextField("lastname", Model.of(user.getLastname()));
            emailField = new TextField("email", Model.of(user.getEmail()));

            add(firstnameField);
            add(lastnameField);
            add(emailField);

            drIModel = new Model<Address>();

            renderAddresSelect = new ChoiceRenderer<Address>("value", "key") {
                @Override
                public Object getDisplayValue(Address object) {
                    return object.getCim();
                }
                @Override
                public String getIdValue(Address object, int index) {
                    return object.getId().toString();
                }
            };

            dropDownChoice = new DropDownChoice("addressSelect", drIModel, addresses, renderAddresSelect);
            add(dropDownChoice);
        }
        public final void onSubmit() {

            final UserCollectionJDBC userCollectionJDBC = new UserCollectionJDBC();

            String email = (String)emailField.getDefaultModelObject();
            Address selectedAddress = drIModel.getObject();

            if (email != null) {
                user.setEmail(email);
            } else {
                user.setEmail("");
            }

            if (selectedAddress != null) {
                user.setCim(selectedAddress.getCim());
                user.setAddress_id(selectedAddress.getId());
            }

            userCollectionJDBC.saveUser(user);
            setResponsePage(HomePage.class);
        }

    }

}
