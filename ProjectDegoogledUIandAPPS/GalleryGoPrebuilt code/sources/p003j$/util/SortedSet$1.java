package p003j$.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import p003j$.util.Spliterators;

/* renamed from: j$.util.SortedSet$1 */
class SortedSet$1 extends Spliterators.IteratorSpliterator {
    final /* synthetic */ SortedSet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SortedSet$1(SortedSet sortedSet, Collection collection, int i) {
        super(collection, i);
        this.this$0 = sortedSet;
    }

    public Comparator getComparator() {
        return this.this$0.comparator();
    }
}
