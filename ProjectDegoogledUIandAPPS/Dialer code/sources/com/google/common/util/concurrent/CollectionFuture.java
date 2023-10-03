package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {

    abstract class CollectionFutureRunningState extends AggregateFuture<V, C>.RunningState {
        private List<Optional<V>> values;

        CollectionFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
            super(immutableCollection, z, true);
            List<Optional<V>> list;
            if (immutableCollection.isEmpty()) {
                list = ImmutableList.m74of();
            } else {
                list = Lists.newArrayListWithCapacity(immutableCollection.size());
            }
            this.values = list;
            for (int i = 0; i < immutableCollection.size(); i++) {
                this.values.add((Object) null);
            }
        }

        /* access modifiers changed from: package-private */
        public final void collectOneValue(boolean z, int i, V v) {
            List<Optional<V>> list = this.values;
            if (list != null) {
                list.set(i, Optional.fromNullable(v));
            } else {
                MoreObjects.checkState(z || CollectionFuture.this.isCancelled(), "Future was done before all dependencies completed");
            }
        }

        /* access modifiers changed from: package-private */
        public final void handleAllCompleted() {
            List<Optional<V>> list = this.values;
            if (list != null) {
                CollectionFuture collectionFuture = CollectionFuture.this;
                ListFuture.ListFutureRunningState listFutureRunningState = (ListFuture.ListFutureRunningState) this;
                ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(list.size());
                Iterator<Optional<V>> it = list.iterator();
                while (it.hasNext()) {
                    Optional next = it.next();
                    newArrayListWithCapacity.add(next != null ? next.orNull() : null);
                }
                collectionFuture.set(Collections.unmodifiableList(newArrayListWithCapacity));
                return;
            }
            MoreObjects.checkState(CollectionFuture.this.isDone());
        }

        /* access modifiers changed from: package-private */
        public void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.values = null;
        }
    }

    static final class ListFuture<V> extends CollectionFuture<V, List<V>> {

        private final class ListFutureRunningState extends CollectionFuture<V, List<V>>.CollectionFutureRunningState {
            ListFutureRunningState(ListFuture listFuture, ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
                super(immutableCollection, z);
            }
        }

        ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z) {
            init(new ListFutureRunningState(this, immutableCollection, z));
        }
    }

    CollectionFuture() {
    }
}
