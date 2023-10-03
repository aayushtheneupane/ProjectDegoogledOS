package android.support.p000v4.app;

import android.os.Build;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: android.support.v4.app.FragmentTransition */
class FragmentTransition {
    private static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8};
    private static final FragmentTransitionImpl PLATFORM_IMPL = new FragmentTransitionCompat21();
    private static final FragmentTransitionImpl SUPPORT_IMPL;

    /* renamed from: android.support.v4.app.FragmentTransition$FragmentContainerTransition */
    static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;

        FragmentContainerTransition() {
        }
    }

    static {
        FragmentTransitionImpl fragmentTransitionImpl;
        int i = Build.VERSION.SDK_INT;
        try {
            fragmentTransitionImpl = (FragmentTransitionImpl) Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            fragmentTransitionImpl = null;
        }
        SUPPORT_IMPL = fragmentTransitionImpl;
    }

    private static void addSharedElementsWithMatchingNames(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View valueAt = arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(valueAt))) {
                arrayList.add(valueAt);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (r10.mAdded != false) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x007f, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0091, code lost:
        if (r10.mHidden == false) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0093, code lost:
        r1 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00f5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:99:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addToFirstInLastOut(android.support.p000v4.app.BackStackRecord r16, android.support.p000v4.app.BackStackRecord.C0080Op r17, android.util.SparseArray<android.support.p000v4.app.FragmentTransition.FragmentContainerTransition> r18, boolean r19, boolean r20) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            android.support.v4.app.Fragment r10 = r1.fragment
            if (r10 != 0) goto L_0x000d
            return
        L_0x000d:
            int r11 = r10.mContainerId
            if (r11 != 0) goto L_0x0012
            return
        L_0x0012:
            if (r3 == 0) goto L_0x001b
            int[] r4 = INVERSE_OPS
            int r1 = r1.cmd
            r1 = r4[r1]
            goto L_0x001d
        L_0x001b:
            int r1 = r1.cmd
        L_0x001d:
            r4 = 0
            r5 = 1
            if (r1 == r5) goto L_0x0086
            r6 = 3
            if (r1 == r6) goto L_0x005f
            r6 = 4
            if (r1 == r6) goto L_0x0047
            r6 = 5
            if (r1 == r6) goto L_0x0035
            r6 = 6
            if (r1 == r6) goto L_0x005f
            r6 = 7
            if (r1 == r6) goto L_0x0086
            r1 = r4
            r12 = r1
            r13 = r12
            goto L_0x009a
        L_0x0035:
            if (r20 == 0) goto L_0x0044
            boolean r1 = r10.mHiddenChanged
            if (r1 == 0) goto L_0x0095
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0095
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0095
            goto L_0x0093
        L_0x0044:
            boolean r1 = r10.mHidden
            goto L_0x0096
        L_0x0047:
            if (r20 == 0) goto L_0x0056
            boolean r1 = r10.mHiddenChanged
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mHidden
            if (r1 == 0) goto L_0x0081
            goto L_0x007f
        L_0x0056:
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0081
            goto L_0x007f
        L_0x005f:
            if (r20 == 0) goto L_0x0077
            boolean r1 = r10.mAdded
            if (r1 != 0) goto L_0x0081
            android.view.View r1 = r10.mView
            if (r1 == 0) goto L_0x0081
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x0081
            float r1 = r10.mPostponedAlpha
            r6 = 0
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0081
            goto L_0x007f
        L_0x0077:
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0081
        L_0x007f:
            r1 = r5
            goto L_0x0082
        L_0x0081:
            r1 = r4
        L_0x0082:
            r13 = r1
            r1 = r4
            r12 = r5
            goto L_0x009a
        L_0x0086:
            if (r20 == 0) goto L_0x008b
            boolean r1 = r10.mIsNewlyAdded
            goto L_0x0096
        L_0x008b:
            boolean r1 = r10.mAdded
            if (r1 != 0) goto L_0x0095
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0095
        L_0x0093:
            r1 = r5
            goto L_0x0096
        L_0x0095:
            r1 = r4
        L_0x0096:
            r12 = r4
            r13 = r12
            r4 = r1
            r1 = r5
        L_0x009a:
            java.lang.Object r6 = r2.get(r11)
            android.support.v4.app.FragmentTransition$FragmentContainerTransition r6 = (android.support.p000v4.app.FragmentTransition.FragmentContainerTransition) r6
            if (r4 == 0) goto L_0x00b3
            if (r6 != 0) goto L_0x00ad
            android.support.v4.app.FragmentTransition$FragmentContainerTransition r4 = new android.support.v4.app.FragmentTransition$FragmentContainerTransition
            r4.<init>()
            r2.put(r11, r4)
            r6 = r4
        L_0x00ad:
            r6.lastIn = r10
            r6.lastInIsPop = r3
            r6.lastInTransaction = r0
        L_0x00b3:
            r14 = r6
            r15 = 0
            if (r20 != 0) goto L_0x00da
            if (r1 == 0) goto L_0x00da
            if (r14 == 0) goto L_0x00c1
            android.support.v4.app.Fragment r1 = r14.firstOut
            if (r1 != r10) goto L_0x00c1
            r14.firstOut = r15
        L_0x00c1:
            android.support.v4.app.FragmentManagerImpl r4 = r0.mManager
            int r1 = r10.mState
            if (r1 >= r5) goto L_0x00da
            int r1 = r4.mCurState
            if (r1 < r5) goto L_0x00da
            boolean r1 = r0.mReorderingAllowed
            if (r1 != 0) goto L_0x00da
            r4.makeActive(r10)
            r6 = 1
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r10
            r4.moveToState(r5, r6, r7, r8, r9)
        L_0x00da:
            if (r13 == 0) goto L_0x00f3
            if (r14 == 0) goto L_0x00e2
            android.support.v4.app.Fragment r1 = r14.firstOut
            if (r1 != 0) goto L_0x00f3
        L_0x00e2:
            if (r14 != 0) goto L_0x00ed
            android.support.v4.app.FragmentTransition$FragmentContainerTransition r1 = new android.support.v4.app.FragmentTransition$FragmentContainerTransition
            r1.<init>()
            r2.put(r11, r1)
            r14 = r1
        L_0x00ed:
            r14.firstOut = r10
            r14.firstOutIsPop = r3
            r14.firstOutTransaction = r0
        L_0x00f3:
            if (r20 != 0) goto L_0x00ff
            if (r12 == 0) goto L_0x00ff
            if (r14 == 0) goto L_0x00ff
            android.support.v4.app.Fragment r0 = r14.lastIn
            if (r0 != r10) goto L_0x00ff
            r14.lastIn = r15
        L_0x00ff:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTransition.addToFirstInLastOut(android.support.v4.app.BackStackRecord, android.support.v4.app.BackStackRecord$Op, android.util.SparseArray, boolean, boolean):void");
    }

    /* access modifiers changed from: private */
    public static void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        if (z) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    private static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        ArrayList<String> arrayList;
        Fragment fragment = fragmentContainerTransition.lastIn;
        View view = fragment.getView();
        if (arrayMap.isEmpty() || obj == null || view == null) {
            arrayMap.clear();
            return null;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.findNamedViews(arrayMap2, view);
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (fragmentContainerTransition.lastInIsPop) {
            fragment.getExitTransitionCallback();
            arrayList = backStackRecord.mSharedElementSourceNames;
        } else {
            fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.mSharedElementTargetNames;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
            arrayMap2.retainAll(arrayMap.values());
        }
        int size = arrayMap.size();
        while (true) {
            size--;
            if (size < 0) {
                return arrayMap2;
            }
            if (!arrayMap2.containsKey(arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        ArrayList<String> arrayList;
        if (arrayMap.isEmpty() || obj == null) {
            arrayMap.clear();
            return null;
        }
        Fragment fragment = fragmentContainerTransition.firstOut;
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.findNamedViews(arrayMap2, fragment.getView());
        BackStackRecord backStackRecord = fragmentContainerTransition.firstOutTransaction;
        if (fragmentContainerTransition.firstOutIsPop) {
            fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.mSharedElementTargetNames;
        } else {
            fragment.getExitTransitionCallback();
            arrayList = backStackRecord.mSharedElementSourceNames;
        }
        arrayMap2.retainAll(arrayList);
        arrayMap.retainAll(arrayMap2.keySet());
        return arrayMap2;
    }

    private static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = PLATFORM_IMPL;
        if (fragmentTransitionImpl != null && canHandleAll(fragmentTransitionImpl, arrayList)) {
            return PLATFORM_IMPL;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = SUPPORT_IMPL;
        if (fragmentTransitionImpl2 != null && canHandleAll(fragmentTransitionImpl2, arrayList)) {
            return SUPPORT_IMPL;
        }
        if (PLATFORM_IMPL == null && SUPPORT_IMPL == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    /* access modifiers changed from: private */
    public static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            fragmentTransitionImpl.captureTransitioningViews(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        fragmentTransitionImpl.addTargets(obj, arrayList2);
        return arrayList2;
    }

    private static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object obj;
        if (fragment == null) {
            return null;
        }
        if (z) {
            obj = fragment.getReenterTransition();
        } else {
            obj = fragment.getEnterTransition();
        }
        return fragmentTransitionImpl.cloneTransition(obj);
    }

    private static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object obj;
        if (fragment == null) {
            return null;
        }
        if (z) {
            obj = fragment.getReturnTransition();
        } else {
            obj = fragment.getExitTransition();
        }
        return fragmentTransitionImpl.cloneTransition(obj);
    }

    /* access modifiers changed from: private */
    public static View getInEpicenterView(ArrayMap<String, View> arrayMap, FragmentContainerTransition fragmentContainerTransition, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (obj == null || arrayMap == null || (arrayList = backStackRecord.mSharedElementSourceNames) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = backStackRecord.mSharedElementSourceNames.get(0);
        } else {
            str = backStackRecord.mSharedElementTargetNames.get(0);
        }
        return arrayMap.get(str);
    }

    private static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        Object obj;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            obj = fragment2.getSharedElementReturnTransition();
        } else {
            obj = fragment.getSharedElementEnterTransition();
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(obj));
    }

    private static Object mergeTransitions(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean z2;
        if (obj == null || obj2 == null || fragment == null) {
            z2 = true;
        } else {
            z2 = z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3);
        }
        return fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    private static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, BackStackRecord backStackRecord) {
        String str;
        ArrayList<String> arrayList = backStackRecord.mSharedElementSourceNames;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (z) {
                str = backStackRecord.mSharedElementTargetNames.get(0);
            } else {
                str = backStackRecord.mSharedElementSourceNames.get(0);
            }
            View view = arrayMap.get(str);
            fragmentTransitionImpl.setEpicenter(obj, view);
            if (obj2 != null) {
                fragmentTransitionImpl.setEpicenter(obj2, view);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setViewVisibility(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).setVisibility(i);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:151:0x041b  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0459 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0224 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void startTransitions(android.support.p000v4.app.FragmentManagerImpl r39, java.util.ArrayList<android.support.p000v4.app.BackStackRecord> r40, java.util.ArrayList<java.lang.Boolean> r41, int r42, int r43, boolean r44) {
        /*
            r0 = r39
            r1 = r40
            r2 = r41
            r3 = r43
            r4 = r44
            int r5 = r0.mCurState
            r6 = 1
            if (r5 >= r6) goto L_0x0010
            return
        L_0x0010:
            android.util.SparseArray r5 = new android.util.SparseArray
            r5.<init>()
            r7 = r42
        L_0x0017:
            r8 = 0
            if (r7 >= r3) goto L_0x0068
            java.lang.Object r9 = r1.get(r7)
            android.support.v4.app.BackStackRecord r9 = (android.support.p000v4.app.BackStackRecord) r9
            java.lang.Object r10 = r2.get(r7)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x004e
            android.support.v4.app.FragmentManagerImpl r8 = r9.mManager
            android.support.v4.app.FragmentContainer r8 = r8.mContainer
            boolean r8 = r8.onHasView()
            if (r8 != 0) goto L_0x0037
            goto L_0x0065
        L_0x0037:
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r8 = r9.mOps
            int r8 = r8.size()
            int r8 = r8 - r6
        L_0x003e:
            if (r8 < 0) goto L_0x0065
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r10 = r9.mOps
            java.lang.Object r10 = r10.get(r8)
            android.support.v4.app.BackStackRecord$Op r10 = (android.support.p000v4.app.BackStackRecord.C0080Op) r10
            addToFirstInLastOut(r9, r10, r5, r6, r4)
            int r8 = r8 + -1
            goto L_0x003e
        L_0x004e:
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r10 = r9.mOps
            int r10 = r10.size()
            r11 = r8
        L_0x0055:
            if (r11 >= r10) goto L_0x0065
            java.util.ArrayList<android.support.v4.app.BackStackRecord$Op> r12 = r9.mOps
            java.lang.Object r12 = r12.get(r11)
            android.support.v4.app.BackStackRecord$Op r12 = (android.support.p000v4.app.BackStackRecord.C0080Op) r12
            addToFirstInLastOut(r9, r12, r5, r8, r4)
            int r11 = r11 + 1
            goto L_0x0055
        L_0x0065:
            int r7 = r7 + 1
            goto L_0x0017
        L_0x0068:
            int r7 = r5.size()
            if (r7 == 0) goto L_0x046d
            android.view.View r7 = new android.view.View
            android.support.v4.app.FragmentHostCallback r9 = r0.mHost
            android.content.Context r9 = r9.getContext()
            r7.<init>(r9)
            int r15 = r5.size()
            r14 = r8
        L_0x007e:
            if (r14 >= r15) goto L_0x046d
            int r9 = r5.keyAt(r14)
            android.support.v4.util.ArrayMap r13 = new android.support.v4.util.ArrayMap
            r13.<init>()
            int r10 = r3 + -1
            r12 = r42
        L_0x008d:
            if (r10 < r12) goto L_0x00f8
            java.lang.Object r11 = r1.get(r10)
            android.support.v4.app.BackStackRecord r11 = (android.support.p000v4.app.BackStackRecord) r11
            boolean r16 = r11.interactsWith(r9)
            if (r16 != 0) goto L_0x009c
            goto L_0x00ed
        L_0x009c:
            java.lang.Object r16 = r2.get(r10)
            java.lang.Boolean r16 = (java.lang.Boolean) r16
            boolean r16 = r16.booleanValue()
            java.util.ArrayList<java.lang.String> r6 = r11.mSharedElementSourceNames
            if (r6 == 0) goto L_0x00ed
            int r6 = r6.size()
            if (r16 == 0) goto L_0x00b5
            java.util.ArrayList<java.lang.String> r8 = r11.mSharedElementSourceNames
            java.util.ArrayList<java.lang.String> r11 = r11.mSharedElementTargetNames
            goto L_0x00be
        L_0x00b5:
            java.util.ArrayList<java.lang.String> r8 = r11.mSharedElementSourceNames
            java.util.ArrayList<java.lang.String> r11 = r11.mSharedElementTargetNames
            r38 = r11
            r11 = r8
            r8 = r38
        L_0x00be:
            r1 = 0
        L_0x00bf:
            if (r1 >= r6) goto L_0x00ed
            java.lang.Object r16 = r11.get(r1)
            r2 = r16
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r16 = r8.get(r1)
            r3 = r16
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r16 = r13.remove(r3)
            r17 = r6
            r6 = r16
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x00e1
            r13.put(r2, r6)
            goto L_0x00e4
        L_0x00e1:
            r13.put(r2, r3)
        L_0x00e4:
            int r1 = r1 + 1
            r2 = r41
            r3 = r43
            r6 = r17
            goto L_0x00bf
        L_0x00ed:
            int r10 = r10 + -1
            r1 = r40
            r2 = r41
            r3 = r43
            r6 = 1
            r8 = 0
            goto L_0x008d
        L_0x00f8:
            java.lang.Object r1 = r5.valueAt(r14)
            android.support.v4.app.FragmentTransition$FragmentContainerTransition r1 = (android.support.p000v4.app.FragmentTransition.FragmentContainerTransition) r1
            if (r4 == 0) goto L_0x02f8
            android.support.v4.app.FragmentContainer r3 = r0.mContainer
            boolean r3 = r3.onHasView()
            if (r3 == 0) goto L_0x0111
            android.support.v4.app.FragmentContainer r3 = r0.mContainer
            android.view.View r3 = r3.onFindViewById(r9)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            goto L_0x0112
        L_0x0111:
            r3 = 0
        L_0x0112:
            if (r3 != 0) goto L_0x011c
        L_0x0114:
            r31 = r5
            r32 = r14
            r33 = r15
            goto L_0x02f1
        L_0x011c:
            android.support.v4.app.Fragment r6 = r1.lastIn
            android.support.v4.app.Fragment r8 = r1.firstOut
            android.support.v4.app.FragmentTransitionImpl r9 = chooseImpl(r8, r6)
            if (r9 != 0) goto L_0x0127
            goto L_0x0114
        L_0x0127:
            boolean r10 = r1.lastInIsPop
            boolean r11 = r1.firstOutIsPop
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r31 = r5
            java.lang.Object r5 = getEnterTransition(r9, r6, r10)
            java.lang.Object r11 = getExitTransition(r9, r8, r11)
            android.support.v4.app.Fragment r12 = r1.lastIn
            r32 = r14
            android.support.v4.app.Fragment r14 = r1.firstOut
            r33 = r15
            if (r12 == 0) goto L_0x0151
            android.view.View r15 = r12.getView()
            r0 = 0
            r15.setVisibility(r0)
        L_0x0151:
            if (r12 == 0) goto L_0x01f1
            if (r14 != 0) goto L_0x0157
            goto L_0x01f1
        L_0x0157:
            boolean r0 = r1.lastInIsPop
            boolean r15 = r13.isEmpty()
            if (r15 == 0) goto L_0x0163
            r34 = r10
            r15 = 0
            goto L_0x0169
        L_0x0163:
            java.lang.Object r15 = getSharedElementTransition(r9, r12, r14, r0)
            r34 = r10
        L_0x0169:
            android.support.v4.util.ArrayMap r10 = captureOutSharedElements(r9, r13, r15, r1)
            r35 = r6
            android.support.v4.util.ArrayMap r6 = captureInSharedElements(r9, r13, r15, r1)
            boolean r16 = r13.isEmpty()
            if (r16 == 0) goto L_0x0185
            if (r10 == 0) goto L_0x017e
            r10.clear()
        L_0x017e:
            if (r6 == 0) goto L_0x0183
            r6.clear()
        L_0x0183:
            r15 = 0
            goto L_0x0197
        L_0x0185:
            r16 = r15
            java.util.Set r15 = r13.keySet()
            addSharedElementsWithMatchingNames(r4, r10, r15)
            java.util.Collection r15 = r13.values()
            addSharedElementsWithMatchingNames(r2, r6, r15)
            r15 = r16
        L_0x0197:
            if (r5 != 0) goto L_0x01a0
            if (r11 != 0) goto L_0x01a0
            if (r15 != 0) goto L_0x01a0
            r37 = r2
            goto L_0x01f7
        L_0x01a0:
            r36 = r13
            r13 = 1
            callSharedElementStartEnd(r12, r14, r0, r10, r13)
            if (r15 == 0) goto L_0x01d6
            r2.add(r7)
            r9.setSharedElementTargets(r15, r7, r4)
            boolean r13 = r1.firstOutIsPop
            r37 = r2
            android.support.v4.app.BackStackRecord r2 = r1.firstOutTransaction
            r16 = r9
            r17 = r15
            r18 = r11
            r19 = r10
            r20 = r13
            r21 = r2
            setOutEpicenter(r16, r17, r18, r19, r20, r21)
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            android.view.View r1 = getInEpicenterView(r6, r1, r5, r0)
            if (r1 == 0) goto L_0x01d1
            r9.setEpicenter((java.lang.Object) r5, (android.graphics.Rect) r2)
        L_0x01d1:
            r27 = r1
            r29 = r2
            goto L_0x01dc
        L_0x01d6:
            r37 = r2
            r27 = 0
            r29 = 0
        L_0x01dc:
            android.support.v4.app.FragmentTransition$3 r1 = new android.support.v4.app.FragmentTransition$3
            r22 = r1
            r23 = r12
            r24 = r14
            r25 = r0
            r26 = r6
            r28 = r9
            r22.<init>(r24, r25, r26, r27, r28, r29)
            android.support.p000v4.app.OneShotPreDrawListener.add(r3, r1)
            goto L_0x01fa
        L_0x01f1:
            r37 = r2
            r35 = r6
            r34 = r10
        L_0x01f7:
            r36 = r13
            r15 = 0
        L_0x01fa:
            if (r5 != 0) goto L_0x0202
            if (r15 != 0) goto L_0x0202
            if (r11 != 0) goto L_0x0202
            goto L_0x02f1
        L_0x0202:
            java.util.ArrayList r0 = configureEnteringExitingViews(r9, r11, r8, r4, r7)
            r1 = r35
            r2 = r37
            java.util.ArrayList r6 = configureEnteringExitingViews(r9, r5, r1, r2, r7)
            r10 = 4
            setViewVisibility(r6, r10)
            r16 = r9
            r17 = r5
            r18 = r11
            r19 = r15
            r20 = r1
            r21 = r34
            java.lang.Object r1 = mergeTransitions(r16, r17, r18, r19, r20, r21)
            if (r1 == 0) goto L_0x02f1
            if (r8 == 0) goto L_0x0249
            if (r11 == 0) goto L_0x0249
            boolean r10 = r8.mAdded
            if (r10 == 0) goto L_0x0249
            boolean r10 = r8.mHidden
            if (r10 == 0) goto L_0x0249
            boolean r10 = r8.mHiddenChanged
            if (r10 == 0) goto L_0x0249
            r10 = 1
            r8.setHideReplaced(r10)
            android.view.View r10 = r8.getView()
            r9.scheduleHideFragmentView(r11, r10, r0)
            android.view.ViewGroup r8 = r8.mContainer
            android.support.v4.app.FragmentTransition$1 r10 = new android.support.v4.app.FragmentTransition$1
            r10.<init>(r0)
            android.support.p000v4.app.OneShotPreDrawListener.add(r8, r10)
        L_0x0249:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            int r10 = r2.size()
            r12 = 0
        L_0x0253:
            if (r12 >= r10) goto L_0x026f
            java.lang.Object r13 = r2.get(r12)
            android.view.View r13 = (android.view.View) r13
            java.lang.String r14 = android.support.p000v4.view.ViewCompat.getTransitionName(r13)
            r8.add(r14)
            int r14 = android.os.Build.VERSION.SDK_INT
            r16 = r10
            r10 = 0
            r13.setTransitionName(r10)
            int r12 = r12 + 1
            r10 = r16
            goto L_0x0253
        L_0x026f:
            r22 = r9
            r23 = r1
            r24 = r5
            r25 = r6
            r26 = r11
            r27 = r0
            r28 = r15
            r29 = r2
            r22.scheduleRemoveTargets(r23, r24, r25, r26, r27, r28, r29)
            r9.beginDelayedTransition(r3, r1)
            int r0 = r2.size()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5 = 0
        L_0x028f:
            if (r5 >= r0) goto L_0x02d3
            java.lang.Object r10 = r4.get(r5)
            android.view.View r10 = (android.view.View) r10
            java.lang.String r11 = android.support.p000v4.view.ViewCompat.getTransitionName(r10)
            r1.add(r11)
            if (r11 != 0) goto L_0x02a3
            r13 = r36
            goto L_0x02ce
        L_0x02a3:
            int r12 = android.os.Build.VERSION.SDK_INT
            r14 = 0
            r10.setTransitionName(r14)
            r13 = r36
            java.lang.Object r10 = r13.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            r12 = 0
        L_0x02b2:
            if (r12 >= r0) goto L_0x02ce
            java.lang.Object r14 = r8.get(r12)
            boolean r14 = r10.equals(r14)
            if (r14 == 0) goto L_0x02ca
            java.lang.Object r10 = r2.get(r12)
            android.view.View r10 = (android.view.View) r10
            int r12 = android.os.Build.VERSION.SDK_INT
            r10.setTransitionName(r11)
            goto L_0x02ce
        L_0x02ca:
            int r12 = r12 + 1
            r14 = 0
            goto L_0x02b2
        L_0x02ce:
            int r5 = r5 + 1
            r36 = r13
            goto L_0x028f
        L_0x02d3:
            android.support.v4.app.FragmentTransitionImpl$1 r5 = new android.support.v4.app.FragmentTransitionImpl$1
            r22 = r5
            r23 = r9
            r24 = r0
            r25 = r2
            r26 = r8
            r27 = r4
            r28 = r1
            r22.<init>(r23, r24, r25, r26, r27, r28)
            android.support.p000v4.app.OneShotPreDrawListener.add(r3, r5)
            r0 = 0
            setViewVisibility(r6, r0)
            r9.swapSharedElementTargets(r15, r4, r2)
            goto L_0x02f2
        L_0x02f1:
            r0 = 0
        L_0x02f2:
            r27 = r32
            r30 = r33
            goto L_0x0459
        L_0x02f8:
            r31 = r5
            r32 = r14
            r33 = r15
            r0 = 0
            r2 = r39
            android.support.v4.app.FragmentContainer r3 = r2.mContainer
            boolean r3 = r3.onHasView()
            if (r3 == 0) goto L_0x0312
            android.support.v4.app.FragmentContainer r3 = r2.mContainer
            android.view.View r3 = r3.onFindViewById(r9)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            goto L_0x0313
        L_0x0312:
            r3 = 0
        L_0x0313:
            if (r3 != 0) goto L_0x0316
            goto L_0x02f2
        L_0x0316:
            android.support.v4.app.Fragment r4 = r1.lastIn
            android.support.v4.app.Fragment r5 = r1.firstOut
            android.support.v4.app.FragmentTransitionImpl r6 = chooseImpl(r5, r4)
            if (r6 != 0) goto L_0x0321
            goto L_0x02f2
        L_0x0321:
            boolean r8 = r1.lastInIsPop
            boolean r9 = r1.firstOutIsPop
            java.lang.Object r8 = getEnterTransition(r6, r4, r8)
            java.lang.Object r12 = getExitTransition(r6, r5, r9)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            android.support.v4.app.Fragment r9 = r1.lastIn
            android.support.v4.app.Fragment r15 = r1.firstOut
            if (r9 == 0) goto L_0x03d9
            if (r15 != 0) goto L_0x0341
            goto L_0x03d9
        L_0x0341:
            boolean r14 = r1.lastInIsPop
            boolean r16 = r13.isEmpty()
            if (r16 == 0) goto L_0x034b
            r0 = 0
            goto L_0x0351
        L_0x034b:
            java.lang.Object r16 = getSharedElementTransition(r6, r9, r15, r14)
            r0 = r16
        L_0x0351:
            android.support.v4.util.ArrayMap r2 = captureOutSharedElements(r6, r13, r0, r1)
            boolean r16 = r13.isEmpty()
            if (r16 == 0) goto L_0x035d
            r0 = 0
            goto L_0x0368
        L_0x035d:
            r16 = r0
            java.util.Collection r0 = r2.values()
            r11.addAll(r0)
            r0 = r16
        L_0x0368:
            if (r8 != 0) goto L_0x0370
            if (r12 != 0) goto L_0x0370
            if (r0 != 0) goto L_0x0370
            goto L_0x03d9
        L_0x0370:
            r22 = r4
            r4 = 1
            callSharedElementStartEnd(r9, r15, r14, r2, r4)
            if (r0 == 0) goto L_0x039f
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r6.setSharedElementTargets(r0, r7, r11)
            r20 = r9
            boolean r9 = r1.firstOutIsPop
            r21 = r10
            android.support.v4.app.BackStackRecord r10 = r1.firstOutTransaction
            r23 = r14
            r14 = r6
            r24 = r15
            r15 = r0
            r16 = r12
            r17 = r2
            r18 = r9
            r19 = r10
            setOutEpicenter(r14, r15, r16, r17, r18, r19)
            if (r8 == 0) goto L_0x03a8
            r6.setEpicenter((java.lang.Object) r8, (android.graphics.Rect) r4)
            goto L_0x03a8
        L_0x039f:
            r20 = r9
            r21 = r10
            r23 = r14
            r24 = r15
            r4 = 0
        L_0x03a8:
            android.support.v4.app.FragmentTransition$4 r2 = new android.support.v4.app.FragmentTransition$4
            r16 = r20
            r9 = r2
            r15 = r21
            r10 = r6
            r14 = r11
            r11 = r13
            r25 = r5
            r5 = r12
            r12 = r0
            r26 = r0
            r0 = r13
            r13 = r1
            r28 = r14
            r27 = r32
            r29 = 0
            r14 = r15
            r32 = r15
            r30 = r33
            r15 = r7
            r17 = r24
            r18 = r23
            r19 = r28
            r20 = r8
            r21 = r4
            r9.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            android.support.p000v4.app.OneShotPreDrawListener.add(r3, r2)
            r20 = r26
            goto L_0x03eb
        L_0x03d9:
            r22 = r4
            r25 = r5
            r28 = r11
            r5 = r12
            r0 = r13
            r27 = r32
            r30 = r33
            r29 = 0
            r32 = r10
            r20 = r29
        L_0x03eb:
            if (r8 != 0) goto L_0x03f3
            if (r20 != 0) goto L_0x03f3
            if (r5 != 0) goto L_0x03f3
            goto L_0x0459
        L_0x03f3:
            r2 = r25
            r4 = r28
            java.util.ArrayList r2 = configureEnteringExitingViews(r6, r5, r2, r4, r7)
            if (r2 == 0) goto L_0x0406
            boolean r4 = r2.isEmpty()
            if (r4 == 0) goto L_0x0404
            goto L_0x0406
        L_0x0404:
            r29 = r5
        L_0x0406:
            r6.addTarget(r8, r7)
            boolean r1 = r1.lastInIsPop
            r14 = r6
            r15 = r8
            r16 = r29
            r17 = r20
            r18 = r22
            r19 = r1
            java.lang.Object r1 = mergeTransitions(r14, r15, r16, r17, r18, r19)
            if (r1 == 0) goto L_0x0459
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r14 = r6
            r15 = r1
            r16 = r8
            r17 = r4
            r18 = r29
            r19 = r2
            r21 = r32
            r14.scheduleRemoveTargets(r15, r16, r17, r18, r19, r20, r21)
            android.support.v4.app.FragmentTransition$2 r5 = new android.support.v4.app.FragmentTransition$2
            r9 = r5
            r10 = r8
            r11 = r6
            r12 = r7
            r13 = r22
            r14 = r32
            r15 = r4
            r16 = r2
            r17 = r29
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
            android.support.p000v4.app.OneShotPreDrawListener.add(r3, r5)
            android.support.v4.app.FragmentTransitionImpl$2 r2 = new android.support.v4.app.FragmentTransitionImpl$2
            r4 = r32
            r2.<init>(r6, r4, r0)
            android.support.p000v4.app.OneShotPreDrawListener.add(r3, r2)
            r6.beginDelayedTransition(r3, r1)
            android.support.v4.app.FragmentTransitionImpl$3 r1 = new android.support.v4.app.FragmentTransitionImpl$3
            r1.<init>(r6, r4, r0)
            android.support.p000v4.app.OneShotPreDrawListener.add(r3, r1)
        L_0x0459:
            int r14 = r27 + 1
            r0 = r39
            r1 = r40
            r2 = r41
            r3 = r43
            r4 = r44
            r15 = r30
            r5 = r31
            r6 = 1
            r8 = 0
            goto L_0x007e
        L_0x046d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.FragmentTransition.startTransitions(android.support.v4.app.FragmentManagerImpl, java.util.ArrayList, java.util.ArrayList, int, int, boolean):void");
    }
}
