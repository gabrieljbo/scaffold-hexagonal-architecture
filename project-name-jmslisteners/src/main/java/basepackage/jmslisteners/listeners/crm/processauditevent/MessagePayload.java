package basepackage.jmslisteners.listeners.crm.processauditevent;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessagePayload implements Serializable {

    private static final long serialVersionUID = -3686768709658239401L;

    private String kind;
    private String description;

}
