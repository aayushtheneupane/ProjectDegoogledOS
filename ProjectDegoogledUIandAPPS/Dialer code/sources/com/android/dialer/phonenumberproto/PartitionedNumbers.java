package com.android.dialer.phonenumberproto;

import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.ArraySet;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.Assert;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class PartitionedNumbers {
    private final ImmutableMap<String, ImmutableSet<DialerPhoneNumber>> e164NumbersToDialerPhoneNumbers;
    private final ImmutableMap<String, ImmutableSet<DialerPhoneNumber>> invalidNumbersToDialerPhoneNumbers;

    public PartitionedNumbers(ImmutableSet<DialerPhoneNumber> immutableSet) {
        Assert.isWorkerThread();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        UnmodifiableIterator<DialerPhoneNumber> it = immutableSet.iterator();
        while (it.hasNext()) {
            DialerPhoneNumber next = it.next();
            if (!next.getIsValid() || !next.getPostDialPortion().isEmpty()) {
                String normalizedNumber = next.getNormalizedNumber();
                Set set = (Set) arrayMap2.get(normalizedNumber);
                if (set == null) {
                    set = new ArraySet();
                    arrayMap2.put(normalizedNumber, set);
                }
                set.add(next);
            } else {
                String normalizedNumber2 = next.getNormalizedNumber();
                Set set2 = (Set) arrayMap.get(normalizedNumber2);
                if (set2 == null) {
                    set2 = new ArraySet();
                    arrayMap.put(normalizedNumber2, set2);
                }
                set2.add(next);
            }
        }
        this.e164NumbersToDialerPhoneNumbers = makeImmutable(arrayMap);
        this.invalidNumbersToDialerPhoneNumbers = makeImmutable(arrayMap2);
    }

    private static <K, V> ImmutableMap<K, ImmutableSet<V>> makeImmutable(Map<K, Set<V>> map) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Map.Entry next : map.entrySet()) {
            builder.put(next.getKey(), ImmutableSet.copyOf((Collection) next.getValue()));
        }
        return builder.build();
    }

    public ImmutableSet<DialerPhoneNumber> dialerPhoneNumbersForInvalid(String str) {
        ImmutableSet<DialerPhoneNumber> immutableSet = this.invalidNumbersToDialerPhoneNumbers.get(str);
        Assert.isNotNull(immutableSet);
        return immutableSet;
    }

    public ImmutableSet<DialerPhoneNumber> dialerPhoneNumbersForValidE164(String str) {
        ImmutableSet<DialerPhoneNumber> immutableSet = this.e164NumbersToDialerPhoneNumbers.get(str);
        Assert.isNotNull(immutableSet);
        return immutableSet;
    }

    public ImmutableSet<String> invalidNumbers() {
        return this.invalidNumbersToDialerPhoneNumbers.keySet();
    }

    public ImmutableSet<String> validE164Numbers() {
        return this.e164NumbersToDialerPhoneNumbers.keySet();
    }
}
