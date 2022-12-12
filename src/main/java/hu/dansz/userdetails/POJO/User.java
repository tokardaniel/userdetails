package hu.dansz.userdetails.POJO;

import lombok.Data;

import java.io.Serializable;

@Data

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    Integer id;
    String firstname;
    String lastname;
    String email;
    String cim;
    Integer address_id;
}
