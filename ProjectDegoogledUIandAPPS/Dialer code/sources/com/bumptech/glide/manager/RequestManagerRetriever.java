package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.util.ArrayMap;
import android.support.p002v7.appcompat.R$style;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Handler.Callback {
    private static final RequestManagerFactory DEFAULT_FACTORY = new RequestManagerFactory() {
        public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    };
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private volatile RequestManager applicationManager;
    private final RequestManagerFactory factory;
    private final Handler handler;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
    final Map<android.support.p000v4.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();
    private final Bundle tempBundle = new Bundle();
    private final ArrayMap<View, Fragment> tempViewToFragment = new ArrayMap<>();
    private final ArrayMap<View, android.support.p000v4.app.Fragment> tempViewToSupportFragment = new ArrayMap<>();

    public interface RequestManagerFactory {
        RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context);
    }

    public RequestManagerRetriever(RequestManagerFactory requestManagerFactory) {
        this.factory = requestManagerFactory == null ? DEFAULT_FACTORY : requestManagerFactory;
        this.handler = new Handler(Looper.getMainLooper(), this);
    }

    private Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @TargetApi(26)
    private void findAllFragmentsWithViews(FragmentManager fragmentManager, ArrayMap<View, Fragment> arrayMap) {
        int i = Build.VERSION.SDK_INT;
        for (Fragment next : fragmentManager.getFragments()) {
            if (next.getView() != null) {
                arrayMap.put(next.getView(), next);
                findAllFragmentsWithViews(next.getChildFragmentManager(), arrayMap);
            }
        }
    }

    private static void findAllSupportFragmentsWithViews(Collection<android.support.p000v4.app.Fragment> collection, Map<View, android.support.p000v4.app.Fragment> map) {
        if (collection != null) {
            for (android.support.p000v4.app.Fragment next : collection) {
                if (!(next == null || next.getView() == null)) {
                    map.put(next.getView(), next);
                    findAllSupportFragmentsWithViews(next.getChildFragmentManager().getFragments(), map);
                }
            }
        }
    }

    private RequestManager fragmentGet(Context context, FragmentManager fragmentManager, Fragment fragment) {
        RequestManagerFragment requestManagerFragment = getRequestManagerFragment(fragmentManager, fragment);
        RequestManager requestManager = requestManagerFragment.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        RequestManager build = this.factory.build(Glide.get(context), requestManagerFragment.getGlideLifecycle(), requestManagerFragment.getRequestManagerTreeNode(), context);
        requestManagerFragment.setRequestManager(build);
        return build;
    }

    private RequestManager getApplicationManager(Context context) {
        if (this.applicationManager == null) {
            synchronized (this) {
                if (this.applicationManager == null) {
                    this.applicationManager = this.factory.build(Glide.get(context.getApplicationContext()), new ApplicationLifecycle(), new EmptyRequestManagerTreeNode(), context.getApplicationContext());
                }
            }
        }
        return this.applicationManager;
    }

    private RequestManager supportFragmentGet(Context context, android.support.p000v4.app.FragmentManager fragmentManager, android.support.p000v4.app.Fragment fragment) {
        SupportRequestManagerFragment supportRequestManagerFragment = getSupportRequestManagerFragment(fragmentManager, fragment);
        RequestManager requestManager = supportRequestManagerFragment.getRequestManager();
        if (requestManager != null) {
            return requestManager;
        }
        RequestManager build = this.factory.build(Glide.get(context), supportRequestManagerFragment.getGlideLifecycle(), supportRequestManagerFragment.getRequestManagerTreeNode(), context);
        supportRequestManagerFragment.setRequestManager(build);
        return build;
    }

    public RequestManager get(Context context) {
        if (context != null) {
            if (Util.isOnMainThread() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return get((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return get((Activity) context);
                }
                if (context instanceof ContextWrapper) {
                    return get(((ContextWrapper) context).getBaseContext());
                }
            }
            return getApplicationManager(context);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    /* access modifiers changed from: package-private */
    public RequestManagerFragment getRequestManagerFragment(FragmentManager fragmentManager, Fragment fragment) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        RequestManagerFragment requestManagerFragment2 = this.pendingRequestManagerFragments.get(fragmentManager);
        if (requestManagerFragment2 != null) {
            return requestManagerFragment2;
        }
        RequestManagerFragment requestManagerFragment3 = new RequestManagerFragment(new ActivityFragmentLifecycle());
        requestManagerFragment3.setParentFragmentHint(fragment);
        this.pendingRequestManagerFragments.put(fragmentManager, requestManagerFragment3);
        fragmentManager.beginTransaction().add(requestManagerFragment3, FRAGMENT_TAG).commitAllowingStateLoss();
        this.handler.obtainMessage(1, fragmentManager).sendToTarget();
        return requestManagerFragment3;
    }

    /* access modifiers changed from: package-private */
    public SupportRequestManagerFragment getSupportRequestManagerFragment(android.support.p000v4.app.FragmentManager fragmentManager, android.support.p000v4.app.Fragment fragment) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        SupportRequestManagerFragment supportRequestManagerFragment2 = this.pendingSupportRequestManagerFragments.get(fragmentManager);
        if (supportRequestManagerFragment2 != null) {
            return supportRequestManagerFragment2;
        }
        SupportRequestManagerFragment supportRequestManagerFragment3 = new SupportRequestManagerFragment(new ActivityFragmentLifecycle());
        supportRequestManagerFragment3.setParentFragmentHint(fragment);
        this.pendingSupportRequestManagerFragments.put(fragmentManager, supportRequestManagerFragment3);
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((android.support.p000v4.app.Fragment) supportRequestManagerFragment3, FRAGMENT_TAG);
        beginTransaction.commitAllowingStateLoss();
        this.handler.obtainMessage(2, fragmentManager).sendToTarget();
        return supportRequestManagerFragment3;
    }

    public boolean handleMessage(Message message) {
        Object obj;
        int i = message.what;
        Object obj2 = null;
        boolean z = true;
        if (i == 1) {
            obj2 = (FragmentManager) message.obj;
            obj = this.pendingRequestManagerFragments.remove(obj2);
        } else if (i != 2) {
            z = false;
            obj = null;
        } else {
            obj2 = (android.support.p000v4.app.FragmentManager) message.obj;
            obj = this.pendingSupportRequestManagerFragments.remove(obj2);
        }
        if (z && obj == null && Log.isLoggable("RMRetriever", 5)) {
            String valueOf = String.valueOf(obj2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 61);
            sb.append("Failed to remove expected request manager fragment, manager: ");
            sb.append(valueOf);
            Log.w("RMRetriever", sb.toString());
        }
        return z;
    }

    public RequestManager get(FragmentActivity fragmentActivity) {
        if (Util.isOnBackgroundThread()) {
            return get(fragmentActivity.getApplicationContext());
        }
        int i = Build.VERSION.SDK_INT;
        if (!fragmentActivity.isDestroyed()) {
            return supportFragmentGet(fragmentActivity, fragmentActivity.getSupportFragmentManager(), (android.support.p000v4.app.Fragment) null);
        }
        throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
    }

    public RequestManager get(android.support.p000v4.app.Fragment fragment) {
        R$style.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        }
        return supportFragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment);
    }

    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        int i = Build.VERSION.SDK_INT;
        if (!activity.isDestroyed()) {
            return fragmentGet(activity, activity.getFragmentManager(), (Fragment) null);
        }
        throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [android.support.v4.app.Fragment] */
    /* JADX WARNING: type inference failed for: r2v10, types: [android.support.v4.app.Fragment] */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0079, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0079, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00bc, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bc, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bumptech.glide.RequestManager get(android.view.View r7) {
        /*
            r6 = this;
            boolean r0 = com.bumptech.glide.util.Util.isOnBackgroundThread()
            if (r0 == 0) goto L_0x0013
            android.content.Context r7 = r7.getContext()
            android.content.Context r7 = r7.getApplicationContext()
            com.bumptech.glide.RequestManager r6 = r6.get((android.content.Context) r7)
            return r6
        L_0x0013:
            java.lang.String r0 = "Argument must not be null"
            android.support.p002v7.appcompat.R$style.checkNotNull(r7, r0)
            android.content.Context r0 = r7.getContext()
            java.lang.String r1 = "Unable to obtain a request manager for a view without a Context"
            android.support.p002v7.appcompat.R$style.checkNotNull(r0, r1)
            android.content.Context r0 = r7.getContext()
            android.app.Activity r0 = r6.findActivity(r0)
            if (r0 != 0) goto L_0x0038
            android.content.Context r7 = r7.getContext()
            android.content.Context r7 = r7.getApplicationContext()
            com.bumptech.glide.RequestManager r6 = r6.get((android.content.Context) r7)
            return r6
        L_0x0038:
            boolean r1 = r0 instanceof android.support.p000v4.app.FragmentActivity
            r2 = 0
            r3 = 16908290(0x1020002, float:2.3877235E-38)
            if (r1 == 0) goto L_0x008a
            r1 = r0
            android.support.v4.app.FragmentActivity r1 = (android.support.p000v4.app.FragmentActivity) r1
            android.support.v4.util.ArrayMap<android.view.View, android.support.v4.app.Fragment> r4 = r6.tempViewToSupportFragment
            r4.clear()
            android.support.v4.app.FragmentManager r4 = r1.getSupportFragmentManager()
            java.util.List r4 = r4.getFragments()
            android.support.v4.util.ArrayMap<android.view.View, android.support.v4.app.Fragment> r5 = r6.tempViewToSupportFragment
            findAllSupportFragmentsWithViews(r4, r5)
            android.view.View r1 = r1.findViewById(r3)
        L_0x0059:
            boolean r3 = r7.equals(r1)
            if (r3 != 0) goto L_0x0079
            android.support.v4.util.ArrayMap<android.view.View, android.support.v4.app.Fragment> r2 = r6.tempViewToSupportFragment
            java.lang.Object r2 = r2.get(r7)
            android.support.v4.app.Fragment r2 = (android.support.p000v4.app.Fragment) r2
            if (r2 == 0) goto L_0x006a
            goto L_0x0079
        L_0x006a:
            android.view.ViewParent r3 = r7.getParent()
            boolean r3 = r3 instanceof android.view.View
            if (r3 == 0) goto L_0x0079
            android.view.ViewParent r7 = r7.getParent()
            android.view.View r7 = (android.view.View) r7
            goto L_0x0059
        L_0x0079:
            android.support.v4.util.ArrayMap<android.view.View, android.support.v4.app.Fragment> r7 = r6.tempViewToSupportFragment
            r7.clear()
            if (r2 == 0) goto L_0x0085
            com.bumptech.glide.RequestManager r6 = r6.get((android.support.p000v4.app.Fragment) r2)
            goto L_0x0089
        L_0x0085:
            com.bumptech.glide.RequestManager r6 = r6.get((android.app.Activity) r0)
        L_0x0089:
            return r6
        L_0x008a:
            android.support.v4.util.ArrayMap<android.view.View, android.app.Fragment> r1 = r6.tempViewToFragment
            r1.clear()
            android.app.FragmentManager r1 = r0.getFragmentManager()
            android.support.v4.util.ArrayMap<android.view.View, android.app.Fragment> r4 = r6.tempViewToFragment
            r6.findAllFragmentsWithViews(r1, r4)
            android.view.View r1 = r0.findViewById(r3)
        L_0x009c:
            boolean r3 = r7.equals(r1)
            if (r3 != 0) goto L_0x00bc
            android.support.v4.util.ArrayMap<android.view.View, android.app.Fragment> r2 = r6.tempViewToFragment
            java.lang.Object r2 = r2.get(r7)
            android.app.Fragment r2 = (android.app.Fragment) r2
            if (r2 == 0) goto L_0x00ad
            goto L_0x00bc
        L_0x00ad:
            android.view.ViewParent r3 = r7.getParent()
            boolean r3 = r3 instanceof android.view.View
            if (r3 == 0) goto L_0x00bc
            android.view.ViewParent r7 = r7.getParent()
            android.view.View r7 = (android.view.View) r7
            goto L_0x009c
        L_0x00bc:
            android.support.v4.util.ArrayMap<android.view.View, android.app.Fragment> r7 = r6.tempViewToFragment
            r7.clear()
            if (r2 != 0) goto L_0x00c8
            com.bumptech.glide.RequestManager r6 = r6.get((android.app.Activity) r0)
            return r6
        L_0x00c8:
            com.bumptech.glide.RequestManager r6 = r6.get((android.app.Fragment) r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerRetriever.get(android.view.View):com.bumptech.glide.RequestManager");
    }

    @TargetApi(17)
    public RequestManager get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            int i = Build.VERSION.SDK_INT;
            return fragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment);
        }
    }
}
