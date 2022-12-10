package hu.dansz.userdetails.POJO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String cim;
}
