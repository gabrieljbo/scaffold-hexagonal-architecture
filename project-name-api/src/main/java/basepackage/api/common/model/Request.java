package basepackage.api.common.model;

import java.io.Serializable;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Request<T> implements Serializable {

    private static final long serialVersionUID = 7048983674910384510L;

    @Valid
    private T data;

}
