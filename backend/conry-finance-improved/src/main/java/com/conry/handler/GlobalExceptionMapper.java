package com.conry.handler;

import com.conry.exception.ErrorResponse;
import io.vertx.core.http.HttpServerRequest;
import lombok.SneakyThrows;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.net.URI;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class GlobalExceptionMapper {

    @Context
    HttpServerRequest request;

    @SneakyThrows
    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapException(Throwable exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .type(new URI(""))
                .title("Internal Server Error")
                .detail(exception.getMessage())
                .instance(new URI(request.absoluteURI()))
                .status(RestResponse.StatusCode.INTERNAL_SERVER_ERROR)
                .build();

        return RestResponse.status(INTERNAL_SERVER_ERROR, errorResponse);
    }

    @SneakyThrows
    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapException(IllegalArgumentException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .type(new URI(""))
                .title("Validation Error")
                .detail(exception.getMessage())
                .instance(new URI(request.absoluteURI()))
                .status(RestResponse.StatusCode.BAD_REQUEST)
                .build();

        return RestResponse.status(RestResponse.Status.BAD_REQUEST, errorResponse);
    }
}
