package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by Niki on 2016-11-07.
 *
 * @author Niki
 */
public class RequestWrap extends HttpServletRequestWrapper {

    public RequestWrap(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getMethod() {
        return "GET";
    }
}
