package hu.dansz.userdetails.collection;

import hu.dansz.userdetails.POJO.Address;
import hu.dansz.userdetails.db.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressCollectionJDBC implements IAddressCollection {

    @Override
    public List<Address> getAddresses() {
        DB db = new DB();
        String sql = "SELECT id, cim FROM address";
        List<Address> addresses = new ArrayList<>();
        try {
            ResultSet resultSet = db.executeQuerySql(sql);
            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCim(resultSet.getString("cim"));
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
