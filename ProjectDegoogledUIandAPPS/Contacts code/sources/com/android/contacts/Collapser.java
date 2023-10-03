package com.android.contacts;

import android.content.Context;
import java.util.Iterator;
import java.util.List;

public final class Collapser {
    private static final int MAX_LISTSIZE_TO_COLLAPSE = 20;

    public interface Collapsible<T> {
        void collapseWith(T t);

        boolean shouldCollapseWith(T t, Context context);
    }

    private Collapser() {
    }

    public static <T extends Collapsible<T>> void collapseList(List<T> list, Context context) {
        int size = list.size();
        if (size <= 20) {
            for (int i = 0; i < size; i++) {
                Collapsible collapsible = (Collapsible) list.get(i);
                if (collapsible != null) {
                    int i2 = i + 1;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        Collapsible collapsible2 = (Collapsible) list.get(i2);
                        if (collapsible2 != null) {
                            if (collapsible.shouldCollapseWith(collapsible2, context)) {
                                collapsible.collapseWith(collapsible2);
                                list.set(i2, (Object) null);
                            } else if (collapsible2.shouldCollapseWith(collapsible, context)) {
                                collapsible2.collapseWith(collapsible);
                                list.set(i, (Object) null);
                                break;
                            }
                        }
                        i2++;
                    }
                }
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                }
            }
        }
    }
}
