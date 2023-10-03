package dagger.internal;

import dagger.MembersInjector;

public final class MembersInjectors {

    private enum NoOpMembersInjector implements MembersInjector<Object> {
        INSTANCE;

        public void injectMembers(Object obj) {
            if (obj == null) {
                throw new NullPointerException();
            }
        }
    }

    public static <T> T injectMembers(MembersInjector<T> membersInjector, T t) {
        ((NoOpMembersInjector) membersInjector).injectMembers(t);
        return t;
    }

    public static <T> MembersInjector<T> noOp() {
        return NoOpMembersInjector.INSTANCE;
    }
}
