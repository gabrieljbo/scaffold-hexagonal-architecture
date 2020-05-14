package basepackage.api.common.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestHeader implements Serializable {

    private static final long serialVersionUID = -6181012467335774207L;

    @NotBlank(message = "{request.header.correlationId.notBlank}")
    @Size(min = 10, max = 36, message = "{request.header.correlationId.size}")
    private String correlationId;

    @NotNull(message = "{request.header.sessionId.notNull}")
    private String sessionId;

    @NotBlank(message = "{request.header.appId.notBlank}")
    @Size(min = 3, message = "{request.header.appId.size}")
    private String appId;

    @NotBlank(message = "{request.header.channel.notBlank}")
    @Size(min = 3, message = "{request.header.channel.size}")
    private String channel;

    @NotBlank(message = "{request.header.lang.notBlank}")
    @Size(min = 2, message = "{request.header.lang.size}")
    private String lang;

}
