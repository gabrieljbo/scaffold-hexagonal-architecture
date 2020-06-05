package basepackage.api.common.model;

import java.io.Serializable;

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

    @NotNull(message = "{request.header.correlationId.notNull}")
    @Size(min = 10, max = 36, message = "{request.header.correlationId.size}")
    private String correlationId;

    @NotNull(message = "{request.header.sessionId.notNull}")
    private String sessionId;

    @NotNull(message = "{request.header.appId.notNull}")
    @Size(min = 3, message = "{request.header.appId.size}")
    private String appId;

    @NotNull(message = "{request.header.channel.notNull}")
    @Size(min = 3, message = "{request.header.channel.size}")
    private String channel;

    @NotNull(message = "{request.header.lang.notNull}")
    @Size(min = 2, message = "{request.header.lang.size}")
    private String lang;

}
