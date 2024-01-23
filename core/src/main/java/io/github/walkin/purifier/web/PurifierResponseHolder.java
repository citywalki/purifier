package io.github.walkin.purifier.web;

import net.jcip.annotations.ThreadSafe;


/**
 * Provides a thread-local for holding a servlet response.
 */
@ThreadSafe
public class PurifierResponseHolder {
    private static final ThreadLocal<PurifierResponse> HOLDER = new ThreadLocal<>();

    public static PurifierResponse getResponse() {
        return HOLDER.get();
    }

    public static void setResponse(PurifierResponse response) {
        HOLDER.set(response);
    }

    public static void removeResponse() {
        HOLDER.remove();
    }

}
