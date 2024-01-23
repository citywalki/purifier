package pro.walkin.purifier.web;

import javax.servlet.http.HttpServletResponse;

public class JavaxResponse implements PurifierResponse {
    private final HttpServletResponse response;

    public JavaxResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public int getStatus() {
        return response.getStatus();
    }
}
