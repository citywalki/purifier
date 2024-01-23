package pro.walkin.purifier.web;

import jakarta.servlet.http.HttpServletResponse;

public class JakartaResponse implements PurifierResponse {
    private final HttpServletResponse response;

    public JakartaResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public int getStatus() {
        return response.getStatus();
    }
}
