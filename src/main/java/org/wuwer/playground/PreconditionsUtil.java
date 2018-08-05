package org.wuwer.playground;

import com.google.common.base.Preconditions;

public class PreconditionsUtil {
    public static <T> T nonNullArg(T obj) {
        Preconditions.checkNotNull(obj);
        return obj;
    }
}
