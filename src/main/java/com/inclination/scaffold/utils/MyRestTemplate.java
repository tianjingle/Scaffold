package com.inclination.scaffold.utils;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.URI;

public class MyRestTemplate extends RestTemplate {


    public HttpHeaders responseHeader;


    /**
     * Execute the given method on the provided URI.
     * <p>The {@link ClientHttpRequest} is processed using the {@link RequestCallback};
     * the response with the {@link ResponseExtractor}.
     * @param url the fully-expanded URL to connect to
     * @param method the HTTP method to execute (GET, POST, etc.)
     * @param requestCallback object that prepares the request (can be {@code null})
     * @param responseExtractor object that extracts the return value from the response (can be {@code null})
     * @return an arbitrary object, as returned by the {@link ResponseExtractor}
     */
    @Nullable
    public <T> T doExecute(URI url, @Nullable HttpMethod method, @Nullable RequestCallback requestCallback,
                              @Nullable ResponseExtractor<T> responseExtractor) throws RestClientException {

        Assert.notNull(url, "URI is required");
        Assert.notNull(method, "HttpMethod is required");
        ClientHttpResponse response = null;
        try {
            ClientHttpRequest request = super.createRequest(url, method);
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
            super.handleResponse(url, method, response);
            return (responseExtractor != null ? responseExtractor.extractData(response) : null);
        }
        catch (IOException ex) {
            String resource = url.toString();
            String query = url.getRawQuery();
            resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
            throw new ResourceAccessException("I/O error on " + method.name() +
                    " request for \"" + resource + "\": " + ex.getMessage(), ex);
        }
        finally {
            if (response != null) {
                responseHeader =response.getHeaders();
                response.close();
            }
        }
    }
}
