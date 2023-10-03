package com.android.dialer.preferredsim;

public abstract class PreferredSimComponent {

    public interface HasComponent {
    }

    public abstract PreferredAccountWorker preferredAccountWorker();
}
