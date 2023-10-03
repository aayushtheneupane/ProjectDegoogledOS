package com.google.common.util.concurrent;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Ordering;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

final class FuturesGetChecked {
    private static final Ordering<Constructor<?>> WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new Function<Constructor<?>, Boolean>() {
        public Object apply(Object obj) {
            return Boolean.valueOf(Arrays.asList(((Constructor) obj).getParameterTypes()).contains(String.class));
        }
    }).reverse();

    interface GetCheckedTypeValidator {
        void validateClass(Class<? extends Exception> cls);
    }

    static class GetCheckedTypeValidatorHolder {
        static final String CLASS_VALUE_VALIDATOR_NAME = (GetCheckedTypeValidatorHolder.class.getName() + "$ClassValueValidator");

        enum ClassValueValidator implements GetCheckedTypeValidator {
            INSTANCE;
            
            private static final ClassValue<Boolean> isValidClass = null;

            static {
                isValidClass = new ClassValue<Boolean>() {
                };
            }

            public void validateClass(Class<? extends Exception> cls) {
                isValidClass.get(cls);
            }
        }

        enum WeakSetValidator implements GetCheckedTypeValidator {
            INSTANCE;
            
            private static final Set<WeakReference<Class<? extends Exception>>> validClasses = null;

            static {
                validClasses = new CopyOnWriteArraySet();
            }

            public void validateClass(Class<? extends Exception> cls) {
                for (WeakReference<Class<? extends Exception>> weakReference : validClasses) {
                    if (cls.equals(weakReference.get())) {
                        return;
                    }
                }
                FuturesGetChecked.checkExceptionClassValidity(cls);
                if (validClasses.size() > 1000) {
                    validClasses.clear();
                }
                validClasses.add(new WeakReference(cls));
            }
        }

        static {
            try {
                GetCheckedTypeValidator getCheckedTypeValidator = (GetCheckedTypeValidator) Class.forName(CLASS_VALUE_VALIDATOR_NAME).getEnumConstants()[0];
            } catch (Throwable unused) {
                FuturesGetChecked.weakSetValidator();
            }
        }

        GetCheckedTypeValidatorHolder() {
        }
    }

    static void checkExceptionClassValidity(Class<? extends Exception> cls) {
        boolean z;
        MoreObjects.checkArgument(isCheckedException(cls), "Futures.getChecked exception type (%s) must not be a RuntimeException", (Object) cls);
        try {
            newWithCause(cls, new Exception());
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        MoreObjects.checkArgument(z, "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", (Object) cls);
    }

    static GetCheckedTypeValidator classValueValidator() {
        return GetCheckedTypeValidatorHolder.ClassValueValidator.INSTANCE;
    }

    static <V, X extends Exception> V getChecked(GetCheckedTypeValidator getCheckedTypeValidator, Future<V> future, Class<X> cls) throws Exception {
        getCheckedTypeValidator.validateClass(cls);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof Error) {
                throw new ExecutionError((Error) cause);
            } else if (cause instanceof RuntimeException) {
                throw new UncheckedExecutionException(cause);
            } else {
                throw newWithCause(cls, cause);
            }
        }
    }

    static boolean isCheckedException(Class<? extends Exception> cls) {
        return !RuntimeException.class.isAssignableFrom(cls);
    }

    private static <X extends Exception> X newWithCause(Class<X> cls, Throwable th) {
        X x;
        for (E e : WITH_STRING_PARAM_FIRST.sortedCopy(Arrays.asList(cls.getConstructors()))) {
            Class[] parameterTypes = e.getParameterTypes();
            Object[] objArr = new Object[parameterTypes.length];
            int i = 0;
            while (true) {
                x = null;
                if (i < parameterTypes.length) {
                    Class cls2 = parameterTypes[i];
                    if (!cls2.equals(String.class)) {
                        if (!cls2.equals(Throwable.class)) {
                            break;
                        }
                        objArr[i] = th;
                    } else {
                        objArr[i] = th.toString();
                    }
                    i++;
                } else {
                    try {
                        x = e.newInstance(objArr);
                        break;
                    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused) {
                    }
                }
            }
            X x2 = (Exception) x;
            if (x2 != null) {
                if (x2.getCause() == null) {
                    x2.initCause(th);
                }
                return x2;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("No appropriate constructor for exception of type ", cls, " in response to chained exception"), th);
    }

    static GetCheckedTypeValidator weakSetValidator() {
        return GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
    }
}
