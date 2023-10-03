package com.android.dialer.common;

import android.support.p000v4.app.Fragment;
import com.android.dialer.main.MainActivityPeer;
import com.android.dialer.main.impl.OldMainActivityPeer;

public class FragmentUtils {
    private static Object parentForTesting;

    public interface FragmentUtilListener {
    }

    public static void checkParent(Fragment fragment, Class<?> cls) throws IllegalStateException {
        String str;
        if (parentForTesting == null && getParent(fragment, cls) == null) {
            if (fragment.getParentFragment() == null) {
                str = fragment.getActivity().getClass().getName();
            } else {
                str = fragment.getParentFragment().getClass().getName();
            }
            throw new IllegalStateException(fragment.getClass().getName() + " must be added to a parent that implements " + cls.getName() + ". Instead found " + str);
        }
    }

    public static <T> T getParent(Fragment fragment, Class<T> cls) {
        if (cls.isInstance(parentForTesting)) {
            return parentForTesting;
        }
        T parentFragment = fragment.getParentFragment();
        if (cls.isInstance(parentFragment)) {
            return parentFragment;
        }
        if (cls.isInstance(fragment.getActivity())) {
            return fragment.getActivity();
        }
        if (fragment.getActivity() instanceof FragmentUtilListener) {
            return ((OldMainActivityPeer) fragment.getActivity()).getImpl(cls);
        }
        if (!(fragment.getActivity() instanceof MainActivityPeer.PeerSupplier)) {
            return null;
        }
        MainActivityPeer peer = ((MainActivityPeer.PeerSupplier) fragment.getActivity()).getPeer();
        if (peer instanceof FragmentUtilListener) {
            return ((OldMainActivityPeer) peer).getImpl(cls);
        }
        return null;
    }

    public static <T> T getParentUnsafe(Fragment fragment, Class<T> cls) {
        T parent = getParent(fragment, cls);
        Assert.isNotNull(parent);
        return parent;
    }

    public static void setParentForTesting(Object obj) {
        parentForTesting = obj;
    }

    public static <T> T getParentUnsafe(android.app.Fragment fragment, Class<T> cls) {
        T parent = getParent(fragment, cls);
        Assert.isNotNull(parent);
        return parent;
    }

    public static <T> T getParent(android.app.Fragment fragment, Class<T> cls) {
        if (cls.isInstance(parentForTesting)) {
            return parentForTesting;
        }
        T parentFragment = fragment.getParentFragment();
        if (cls.isInstance(parentFragment)) {
            return parentFragment;
        }
        if (cls.isInstance(fragment.getActivity())) {
            return fragment.getActivity();
        }
        if (fragment.getActivity() instanceof FragmentUtilListener) {
            return ((OldMainActivityPeer) fragment.getActivity()).getImpl(cls);
        }
        if (!(fragment.getActivity() instanceof MainActivityPeer.PeerSupplier)) {
            return null;
        }
        MainActivityPeer peer = ((MainActivityPeer.PeerSupplier) fragment.getActivity()).getPeer();
        if (peer instanceof FragmentUtilListener) {
            return ((OldMainActivityPeer) peer).getImpl(cls);
        }
        return null;
    }
}
