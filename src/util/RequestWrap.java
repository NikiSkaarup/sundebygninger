package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * RequestWrap wraps a HttpServletRequest, in order to turn a POST request
 * into a GET request by forcing getMethod() to return GET
 *
 * @author Niki
 */
public class RequestWrap extends HttpServletRequestWrapper {

    /**
     * default constructor to ensure the rest of the request data stays intact
     * @param request the request that should be wrapped
     */
    public RequestWrap(HttpServletRequest request) {
        super(request);
    }

    /**
     * force getMethod() to return GET
     * @return GET
     */
    @Override
    public String getMethod() {
        return "GET";
    }
}
