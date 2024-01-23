package io.github.walkin.purifier.web;

import net.jcip.annotations.ThreadSafe;


/**
 * Provides a thread-local for holding a servlet request.
 */
@ThreadSafe
public class PurifierRequestHolder {
    private static final ThreadLocal<PurifierRequest> HOLDER = new ThreadLocal<PurifierRequest>();

    public static PurifierRequest getRequest() {
        return HOLDER.get();
    }

    public static void setRequest(PurifierRequest request) {
        HOLDER.set(request);
    }

    public static void removeRequest() {
        HOLDER.remove();
    }

}
