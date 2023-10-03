package com.google.common.base;

import java.util.Iterator;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

final class Platform {
    private static final Logger logger = Logger.getLogger(Platform.class.getName());

    static {
        try {
            Iterator<S> it = ServiceLoader.load(PatternCompiler.class).iterator();
            while (it.hasNext()) {
                try {
                    PatternCompiler patternCompiler = (PatternCompiler) it.next();
                    return;
                } catch (ServiceConfigurationError e) {
                    logPatternCompilerError(e);
                }
            }
        } catch (ServiceConfigurationError e2) {
            logPatternCompilerError(e2);
        }
    }

    private Platform() {
    }

    static String formatCompact4Digits(double d) {
        return String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(d)});
    }

    private static void logPatternCompilerError(ServiceConfigurationError serviceConfigurationError) {
        logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", serviceConfigurationError);
    }

    static boolean stringIsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    static long systemNanoTime() {
        return System.nanoTime();
    }
}
