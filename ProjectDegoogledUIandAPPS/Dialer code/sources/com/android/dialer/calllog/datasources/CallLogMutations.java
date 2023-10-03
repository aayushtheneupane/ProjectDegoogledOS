package com.android.dialer.calllog.datasources;

import android.content.ContentValues;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.dialer.common.Assert;

public final class CallLogMutations {
    private final ArraySet<Long> deletes = new ArraySet<>();
    private final ArrayMap<Long, ContentValues> inserts = new ArrayMap<>();
    private final ArrayMap<Long, ContentValues> updates = new ArrayMap<>();

    public void delete(long j) {
        Assert.checkArgument(!this.inserts.containsKey(Long.valueOf(j)), "Can't delete row scheduled for insert", new Object[0]);
        Assert.checkArgument(!this.updates.containsKey(Long.valueOf(j)), "Can't delete row scheduled for update", new Object[0]);
        Assert.checkArgument(!this.deletes.contains(Long.valueOf(j)), "Can't delete row already scheduled for delete", new Object[0]);
        this.deletes.add(Long.valueOf(j));
    }

    public ArraySet<Long> getDeletes() {
        return this.deletes;
    }

    public ArrayMap<Long, ContentValues> getInserts() {
        return this.inserts;
    }

    public ArrayMap<Long, ContentValues> getUpdates() {
        return this.updates;
    }

    public void insert(long j, ContentValues contentValues) {
        Assert.checkArgument(!this.inserts.containsKey(Long.valueOf(j)), "Can't insert row already scheduled for insert", new Object[0]);
        Assert.checkArgument(!this.updates.containsKey(Long.valueOf(j)), "Can't insert row scheduled for update", new Object[0]);
        Assert.checkArgument(!this.deletes.contains(Long.valueOf(j)), "Can't insert row scheduled for delete", new Object[0]);
        this.inserts.put(Long.valueOf(j), contentValues);
    }

    public boolean isEmpty() {
        return this.inserts.isEmpty() && this.updates.isEmpty() && this.deletes.isEmpty();
    }

    public void update(long j, ContentValues contentValues) {
        Assert.checkArgument(!this.inserts.containsKey(Long.valueOf(j)), "Can't update row scheduled for insert", new Object[0]);
        Assert.checkArgument(!this.deletes.contains(Long.valueOf(j)), "Can't delete row scheduled for delete", new Object[0]);
        ContentValues contentValues2 = this.updates.get(Long.valueOf(j));
        if (contentValues2 != null) {
            contentValues2.putAll(contentValues);
        } else {
            this.updates.put(Long.valueOf(j), contentValues);
        }
    }
}
