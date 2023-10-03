package com.android.dialer.precall;

import com.google.common.collect.ImmutableList;

public abstract class PreCallComponent {

    public interface HasComponent {
    }

    public abstract ImmutableList<PreCallAction> createActions();

    public abstract PreCall getPreCall();
}
