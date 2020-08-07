package basepackage.jmslisteners.common.model;

import java.io.Serializable;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message<T> implements Serializable {

    private static final long serialVersionUID = -6613307365653287392L;
    
    @Valid
    private T data;

}
