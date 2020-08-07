package basepackage.core.components.crm.adapters;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class CustomerEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

}
