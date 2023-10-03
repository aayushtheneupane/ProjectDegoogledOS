package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.os.Build;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.LongSparseArray;
import android.support.p000v4.view.ViewCompat;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Transition implements Cloneable {
    private static final int[] DEFAULT_MATCH_ORDER = {2, 1, 3, 4};
    private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() {
        public Path getPath(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    };
    private static ThreadLocal<ArrayMap<Animator, AnimationInfo>> sRunningAnimators = new ThreadLocal<>();
    private ArrayList<Animator> mAnimators = new ArrayList<>();
    boolean mCanRemoveViews = false;
    /* access modifiers changed from: private */
    public ArrayList<Animator> mCurrentAnimators = new ArrayList<>();
    long mDuration = -1;
    private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
    private ArrayList<TransitionValues> mEndValuesList;
    private boolean mEnded = false;
    private EpicenterCallback mEpicenterCallback;
    private TimeInterpolator mInterpolator = null;
    private ArrayList<TransitionListener> mListeners = null;
    private int[] mMatchOrder = DEFAULT_MATCH_ORDER;
    private String mName = getClass().getName();
    private ArrayMap<String, String> mNameOverrides;
    private int mNumInstances = 0;
    TransitionSet mParent = null;
    private PathMotion mPathMotion = STRAIGHT_PATH_MOTION;
    private boolean mPaused = false;
    private long mStartDelay = -1;
    private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
    private ArrayList<TransitionValues> mStartValuesList;
    private ArrayList<View> mTargetChildExcludes = null;
    private ArrayList<View> mTargetExcludes = null;
    private ArrayList<Integer> mTargetIdChildExcludes = null;
    private ArrayList<Integer> mTargetIdExcludes = null;
    ArrayList<Integer> mTargetIds = new ArrayList<>();
    private ArrayList<String> mTargetNameExcludes = null;
    private ArrayList<String> mTargetNames = null;
    private ArrayList<Class> mTargetTypeChildExcludes = null;
    private ArrayList<Class> mTargetTypeExcludes = null;
    private ArrayList<Class> mTargetTypes = null;
    ArrayList<View> mTargets = new ArrayList<>();

    private static class AnimationInfo {
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowIdImpl mWindowId;

        AnimationInfo(View view, String str, Transition transition, WindowIdImpl windowIdImpl, TransitionValues transitionValues) {
            this.mView = view;
            this.mName = str;
            this.mValues = transitionValues;
            this.mWindowId = windowIdImpl;
            this.mTransition = transition;
        }
    }

    public static abstract class EpicenterCallback {
    }

    public interface TransitionListener {
        void onTransitionEnd(Transition transition);

        void onTransitionPause(Transition transition);

        void onTransitionResume(Transition transition);

        void onTransitionStart(Transition transition);
    }

    private static void addViewValues(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.mViewValues.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.mIdValues.indexOfKey(id) >= 0) {
                transitionValuesMaps.mIdValues.put(id, (Object) null);
            } else {
                transitionValuesMaps.mIdValues.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (transitionValuesMaps.mNameValues.indexOfKey(transitionName) >= 0) {
                transitionValuesMaps.mNameValues.put(transitionName, null);
            } else {
                transitionValuesMaps.mNameValues.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (transitionValuesMaps.mItemIdValues.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = transitionValuesMaps.mItemIdValues.get(itemIdAtPosition);
                    if (view2 != null) {
                        int i = Build.VERSION.SDK_INT;
                        view2.setHasTransientState(false);
                        transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                int i2 = Build.VERSION.SDK_INT;
                view.setHasTransientState(true);
                transitionValuesMaps.mItemIdValues.put(itemIdAtPosition, view);
            }
        }
    }

    private void captureHierarchy(View view, boolean z) {
        if (view != null) {
            int id = view.getId();
            ArrayList<Integer> arrayList = this.mTargetIdExcludes;
            if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
                ArrayList<View> arrayList2 = this.mTargetExcludes;
                if (arrayList2 == null || !arrayList2.contains(view)) {
                    ArrayList<Class> arrayList3 = this.mTargetTypeExcludes;
                    if (arrayList3 != null) {
                        int size = arrayList3.size();
                        int i = 0;
                        while (i < size) {
                            if (!this.mTargetTypeExcludes.get(i).isInstance(view)) {
                                i++;
                            } else {
                                return;
                            }
                        }
                    }
                    if (view.getParent() instanceof ViewGroup) {
                        TransitionValues transitionValues = new TransitionValues();
                        transitionValues.view = view;
                        if (z) {
                            captureStartValues(transitionValues);
                        } else {
                            captureEndValues(transitionValues);
                        }
                        transitionValues.mTargetedTransitions.add(this);
                        capturePropagationValues(transitionValues);
                        if (z) {
                            addViewValues(this.mStartValues, view, transitionValues);
                        } else {
                            addViewValues(this.mEndValues, view, transitionValues);
                        }
                    }
                    if (view instanceof ViewGroup) {
                        ArrayList<Integer> arrayList4 = this.mTargetIdChildExcludes;
                        if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                            ArrayList<View> arrayList5 = this.mTargetChildExcludes;
                            if (arrayList5 == null || !arrayList5.contains(view)) {
                                ArrayList<Class> arrayList6 = this.mTargetTypeChildExcludes;
                                if (arrayList6 != null) {
                                    int size2 = arrayList6.size();
                                    int i2 = 0;
                                    while (i2 < size2) {
                                        if (!this.mTargetTypeChildExcludes.get(i2).isInstance(view)) {
                                            i2++;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                                ViewGroup viewGroup = (ViewGroup) view;
                                for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                                    captureHierarchy(viewGroup.getChildAt(i3), z);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static ArrayMap<Animator, AnimationInfo> getRunningAnimators() {
        ArrayMap<Animator, AnimationInfo> arrayMap = sRunningAnimators.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, AnimationInfo> arrayMap2 = new ArrayMap<>();
        sRunningAnimators.set(arrayMap2);
        return arrayMap2;
    }

    private static boolean isValueChanged(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public Transition addListener(TransitionListener transitionListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(transitionListener);
        return this;
    }

    public Transition addTarget(View view) {
        this.mTargets.add(view);
        return this;
    }

    public abstract void captureEndValues(TransitionValues transitionValues);

    /* access modifiers changed from: package-private */
    public void capturePropagationValues(TransitionValues transitionValues) {
    }

    public abstract void captureStartValues(TransitionValues transitionValues);

    /* access modifiers changed from: package-private */
    public void captureValues(ViewGroup viewGroup, boolean z) {
        ArrayMap<String, String> arrayMap;
        ArrayList<String> arrayList;
        ArrayList<Class> arrayList2;
        clearValues(z);
        if ((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && (((arrayList = this.mTargetNames) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetTypes) == null || arrayList2.isEmpty()))) {
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                View findViewById = viewGroup.findViewById(this.mTargetIds.get(i).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues();
                    transitionValues.view = findViewById;
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.mTargetedTransitions.add(this);
                    capturePropagationValues(transitionValues);
                    if (z) {
                        addViewValues(this.mStartValues, findViewById, transitionValues);
                    } else {
                        addViewValues(this.mEndValues, findViewById, transitionValues);
                    }
                }
            }
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                View view = this.mTargets.get(i2);
                TransitionValues transitionValues2 = new TransitionValues();
                transitionValues2.view = view;
                if (z) {
                    captureStartValues(transitionValues2);
                } else {
                    captureEndValues(transitionValues2);
                }
                transitionValues2.mTargetedTransitions.add(this);
                capturePropagationValues(transitionValues2);
                if (z) {
                    addViewValues(this.mStartValues, view, transitionValues2);
                } else {
                    addViewValues(this.mEndValues, view, transitionValues2);
                }
            }
        } else {
            captureHierarchy(viewGroup, z);
        }
        if (!z && (arrayMap = this.mNameOverrides) != null) {
            int size = arrayMap.size();
            ArrayList arrayList3 = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                arrayList3.add(this.mStartValues.mNameValues.remove(this.mNameOverrides.keyAt(i3)));
            }
            for (int i4 = 0; i4 < size; i4++) {
                View view2 = (View) arrayList3.get(i4);
                if (view2 != null) {
                    this.mStartValues.mNameValues.put(this.mNameOverrides.valueAt(i4), view2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void clearValues(boolean z) {
        if (z) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        int i;
        Animator animator;
        TransitionValues transitionValues;
        View view;
        TransitionValues transitionValues2;
        Animator animator2;
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            TransitionValues transitionValues3 = arrayList.get(i2);
            TransitionValues transitionValues4 = arrayList2.get(i2);
            if (transitionValues3 != null && !transitionValues3.mTargetedTransitions.contains(this)) {
                transitionValues3 = null;
            }
            if (transitionValues4 != null && !transitionValues4.mTargetedTransitions.contains(this)) {
                transitionValues4 = null;
            }
            if (!(transitionValues3 == null && transitionValues4 == null)) {
                if (transitionValues3 == null || transitionValues4 == null || isTransitionRequired(transitionValues3, transitionValues4)) {
                    Animator createAnimator = createAnimator(viewGroup, transitionValues3, transitionValues4);
                    if (createAnimator != null) {
                        if (transitionValues4 != null) {
                            View view2 = transitionValues4.view;
                            String[] transitionProperties = getTransitionProperties();
                            if (view2 != null && transitionProperties != null && transitionProperties.length > 0) {
                                transitionValues2 = new TransitionValues();
                                transitionValues2.view = view2;
                                TransitionValues transitionValues5 = transitionValuesMaps2.mViewValues.get(view2);
                                if (transitionValues5 != null) {
                                    int i3 = 0;
                                    while (i3 < transitionProperties.length) {
                                        transitionValues2.values.put(transitionProperties[i3], transitionValues5.values.get(transitionProperties[i3]));
                                        i3++;
                                        createAnimator = createAnimator;
                                        size = size;
                                        transitionValues5 = transitionValues5;
                                    }
                                }
                                Animator animator3 = createAnimator;
                                i = size;
                                int size2 = runningAnimators.size();
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= size2) {
                                        animator2 = animator3;
                                        break;
                                    }
                                    AnimationInfo animationInfo = runningAnimators.get(runningAnimators.keyAt(i4));
                                    if (animationInfo.mValues != null && animationInfo.mView == view2 && animationInfo.mName.equals(this.mName) && animationInfo.mValues.equals(transitionValues2)) {
                                        animator2 = null;
                                        break;
                                    }
                                    i4++;
                                }
                            } else {
                                TransitionValuesMaps transitionValuesMaps3 = transitionValuesMaps2;
                                i = size;
                                animator2 = createAnimator;
                                transitionValues2 = null;
                            }
                            view = view2;
                            animator = animator2;
                            transitionValues = transitionValues2;
                        } else {
                            TransitionValuesMaps transitionValuesMaps4 = transitionValuesMaps2;
                            i = size;
                            view = transitionValues3.view;
                            animator = createAnimator;
                            transitionValues = null;
                        }
                        if (animator != null) {
                            runningAnimators.put(animator, new AnimationInfo(view, this.mName, this, ViewUtils.getWindowId(viewGroup), transitionValues));
                            this.mAnimators.add(animator);
                        }
                        i2++;
                        size = i;
                    }
                    TransitionValuesMaps transitionValuesMaps5 = transitionValuesMaps2;
                    i = size;
                    i2++;
                    size = i;
                }
            }
            ViewGroup viewGroup2 = viewGroup;
            TransitionValuesMaps transitionValuesMaps52 = transitionValuesMaps2;
            i = size;
            i2++;
            size = i;
        }
        for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
            Animator animator4 = this.mAnimators.get(sparseIntArray.keyAt(i5));
            animator4.setStartDelay(animator4.getStartDelay() + (((long) sparseIntArray.valueAt(i5)) - Long.MAX_VALUE));
        }
    }

    /* access modifiers changed from: protected */
    public void end() {
        this.mNumInstances--;
        if (this.mNumInstances == 0) {
            ArrayList<TransitionListener> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList2.get(i)).onTransitionEnd(this);
                }
            }
            for (int i2 = 0; i2 < this.mStartValues.mItemIdValues.size(); i2++) {
                View valueAt = this.mStartValues.mItemIdValues.valueAt(i2);
                if (valueAt != null) {
                    ViewCompat.setHasTransientState(valueAt, false);
                }
            }
            for (int i3 = 0; i3 < this.mEndValues.mItemIdValues.size(); i3++) {
                View valueAt2 = this.mEndValues.mItemIdValues.valueAt(i3);
                if (valueAt2 != null) {
                    ViewCompat.setHasTransientState(valueAt2, false);
                }
            }
            this.mEnded = true;
        }
    }

    public long getDuration() {
        return this.mDuration;
    }

    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    /* access modifiers changed from: package-private */
    public TransitionValues getMatchedTransitionValues(View view, boolean z) {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getMatchedTransitionValues(view, z);
        }
        ArrayList<TransitionValues> arrayList = z ? this.mStartValuesList : this.mEndValuesList;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            TransitionValues transitionValues = arrayList.get(i2);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i < 0) {
            return null;
        }
        return (z ? this.mEndValuesList : this.mStartValuesList).get(i);
    }

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List<String> getTargetNames() {
        return this.mTargetNames;
    }

    public List<Class> getTargetTypes() {
        return this.mTargetTypes;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public TransitionValues getTransitionValues(View view, boolean z) {
        TransitionSet transitionSet = this.mParent;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        return (z ? this.mStartValues : this.mEndValues).mViewValues.get(view);
    }

    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties != null) {
            int length = transitionProperties.length;
            int i = 0;
            while (i < length) {
                if (!isValueChanged(transitionValues, transitionValues2, transitionProperties[i])) {
                    i++;
                }
            }
            return false;
        }
        for (String isValueChanged : transitionValues.values.keySet()) {
            if (isValueChanged(transitionValues, transitionValues2, isValueChanged)) {
            }
        }
        return false;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isValidTarget(View view) {
        ArrayList<Class> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.mTargetIdExcludes;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.mTargetExcludes;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class> arrayList5 = this.mTargetTypeExcludes;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i = 0; i < size; i++) {
                if (this.mTargetTypeExcludes.get(i).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.mTargetNameExcludes != null && ViewCompat.getTransitionName(view) != null && this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(view))) {
            return false;
        }
        if ((this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (((arrayList = this.mTargetTypes) == null || arrayList.isEmpty()) && ((arrayList2 = this.mTargetNames) == null || arrayList2.isEmpty()))) || this.mTargetIds.contains(Integer.valueOf(id)) || this.mTargets.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.mTargetNames;
        if (arrayList6 != null && arrayList6.contains(ViewCompat.getTransitionName(view))) {
            return true;
        }
        if (this.mTargetTypes != null) {
            for (int i2 = 0; i2 < this.mTargetTypes.size(); i2++) {
                if (this.mTargetTypes.get(i2).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void pause(View view) {
        if (!this.mEnded) {
            ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
            int size = runningAnimators.size();
            WindowIdImpl windowId = ViewUtils.getWindowId(view);
            for (int i = size - 1; i >= 0; i--) {
                AnimationInfo valueAt = runningAnimators.valueAt(i);
                if (valueAt.mView != null && windowId.equals(valueAt.mWindowId)) {
                    int i2 = Build.VERSION.SDK_INT;
                    runningAnimators.keyAt(i).pause();
                }
            }
            ArrayList<TransitionListener> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size2 = arrayList2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ((TransitionListener) arrayList2.get(i3)).onTransitionPause(this);
                }
            }
            this.mPaused = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void playTransition(ViewGroup viewGroup) {
        AnimationInfo animationInfo;
        TransitionValues transitionValues;
        View view;
        View view2;
        View view3;
        View view4;
        this.mStartValuesList = new ArrayList<>();
        this.mEndValuesList = new ArrayList<>();
        TransitionValuesMaps transitionValuesMaps = this.mStartValues;
        TransitionValuesMaps transitionValuesMaps2 = this.mEndValues;
        ArrayMap arrayMap = new ArrayMap(transitionValuesMaps.mViewValues);
        ArrayMap arrayMap2 = new ArrayMap(transitionValuesMaps2.mViewValues);
        int i = 0;
        while (true) {
            int[] iArr = this.mMatchOrder;
            if (i >= iArr.length) {
                break;
            }
            int i2 = iArr[i];
            if (i2 == 1) {
                int size = arrayMap.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    }
                    View view5 = (View) arrayMap.keyAt(size);
                    if (!(view5 == null || !isValidTarget(view5) || (transitionValues = (TransitionValues) arrayMap2.remove(view5)) == null || (view = transitionValues.view) == null || !isValidTarget(view))) {
                        this.mStartValuesList.add((TransitionValues) arrayMap.removeAt(size));
                        this.mEndValuesList.add(transitionValues);
                    }
                }
            } else if (i2 == 2) {
                ArrayMap<String, View> arrayMap3 = transitionValuesMaps.mNameValues;
                ArrayMap<String, View> arrayMap4 = transitionValuesMaps2.mNameValues;
                int size2 = arrayMap3.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    View valueAt = arrayMap3.valueAt(i3);
                    if (valueAt != null && isValidTarget(valueAt) && (view2 = arrayMap4.get(arrayMap3.keyAt(i3))) != null && isValidTarget(view2)) {
                        TransitionValues transitionValues2 = (TransitionValues) arrayMap.get(valueAt);
                        TransitionValues transitionValues3 = (TransitionValues) arrayMap2.get(view2);
                        if (!(transitionValues2 == null || transitionValues3 == null)) {
                            this.mStartValuesList.add(transitionValues2);
                            this.mEndValuesList.add(transitionValues3);
                            arrayMap.remove(valueAt);
                            arrayMap2.remove(view2);
                        }
                    }
                }
            } else if (i2 == 3) {
                SparseArray<View> sparseArray = transitionValuesMaps.mIdValues;
                SparseArray<View> sparseArray2 = transitionValuesMaps2.mIdValues;
                int size3 = sparseArray.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    View valueAt2 = sparseArray.valueAt(i4);
                    if (valueAt2 != null && isValidTarget(valueAt2) && (view3 = sparseArray2.get(sparseArray.keyAt(i4))) != null && isValidTarget(view3)) {
                        TransitionValues transitionValues4 = (TransitionValues) arrayMap.get(valueAt2);
                        TransitionValues transitionValues5 = (TransitionValues) arrayMap2.get(view3);
                        if (!(transitionValues4 == null || transitionValues5 == null)) {
                            this.mStartValuesList.add(transitionValues4);
                            this.mEndValuesList.add(transitionValues5);
                            arrayMap.remove(valueAt2);
                            arrayMap2.remove(view3);
                        }
                    }
                }
            } else if (i2 == 4) {
                LongSparseArray<View> longSparseArray = transitionValuesMaps.mItemIdValues;
                LongSparseArray<View> longSparseArray2 = transitionValuesMaps2.mItemIdValues;
                int size4 = longSparseArray.size();
                for (int i5 = 0; i5 < size4; i5++) {
                    View valueAt3 = longSparseArray.valueAt(i5);
                    if (valueAt3 != null && isValidTarget(valueAt3) && (view4 = longSparseArray2.get(longSparseArray.keyAt(i5))) != null && isValidTarget(view4)) {
                        TransitionValues transitionValues6 = (TransitionValues) arrayMap.get(valueAt3);
                        TransitionValues transitionValues7 = (TransitionValues) arrayMap2.get(view4);
                        if (!(transitionValues6 == null || transitionValues7 == null)) {
                            this.mStartValuesList.add(transitionValues6);
                            this.mEndValuesList.add(transitionValues7);
                            arrayMap.remove(valueAt3);
                            arrayMap2.remove(view4);
                        }
                    }
                }
            }
            i++;
        }
        for (int i6 = 0; i6 < arrayMap.size(); i6++) {
            TransitionValues transitionValues8 = (TransitionValues) arrayMap.valueAt(i6);
            if (isValidTarget(transitionValues8.view)) {
                this.mStartValuesList.add(transitionValues8);
                this.mEndValuesList.add((Object) null);
            }
        }
        for (int i7 = 0; i7 < arrayMap2.size(); i7++) {
            TransitionValues transitionValues9 = (TransitionValues) arrayMap2.valueAt(i7);
            if (isValidTarget(transitionValues9.view)) {
                this.mEndValuesList.add(transitionValues9);
                this.mStartValuesList.add((Object) null);
            }
        }
        ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        int size5 = runningAnimators.size();
        WindowIdImpl windowId = ViewUtils.getWindowId(viewGroup);
        for (int i8 = size5 - 1; i8 >= 0; i8--) {
            Animator keyAt = runningAnimators.keyAt(i8);
            if (!(keyAt == null || (animationInfo = runningAnimators.get(keyAt)) == null || animationInfo.mView == null || !windowId.equals(animationInfo.mWindowId))) {
                TransitionValues transitionValues10 = animationInfo.mValues;
                View view6 = animationInfo.mView;
                TransitionValues transitionValues11 = getTransitionValues(view6, true);
                TransitionValues matchedTransitionValues = getMatchedTransitionValues(view6, true);
                if (!(transitionValues11 == null && matchedTransitionValues == null) && animationInfo.mTransition.isTransitionRequired(transitionValues10, matchedTransitionValues)) {
                    if (keyAt.isRunning() || keyAt.isStarted()) {
                        keyAt.cancel();
                    } else {
                        runningAnimators.remove(keyAt);
                    }
                }
            }
        }
        createAnimators(viewGroup, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        runAnimators();
    }

    public Transition removeListener(TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList = this.mListeners;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(transitionListener);
        if (this.mListeners.size() == 0) {
            this.mListeners = null;
        }
        return this;
    }

    public Transition removeTarget(View view) {
        this.mTargets.remove(view);
        return this;
    }

    public void resume(View view) {
        if (this.mPaused) {
            if (!this.mEnded) {
                ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
                int size = runningAnimators.size();
                WindowIdImpl windowId = ViewUtils.getWindowId(view);
                for (int i = size - 1; i >= 0; i--) {
                    AnimationInfo valueAt = runningAnimators.valueAt(i);
                    if (valueAt.mView != null && windowId.equals(valueAt.mWindowId)) {
                        int i2 = Build.VERSION.SDK_INT;
                        runningAnimators.keyAt(i).resume();
                    }
                }
                ArrayList<TransitionListener> arrayList = this.mListeners;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                    int size2 = arrayList2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((TransitionListener) arrayList2.get(i3)).onTransitionResume(this);
                    }
                }
            }
            this.mPaused = false;
        }
    }

    /* access modifiers changed from: protected */
    public void runAnimators() {
        start();
        final ArrayMap<Animator, AnimationInfo> runningAnimators = getRunningAnimators();
        Iterator<Animator> it = this.mAnimators.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (runningAnimators.containsKey(next)) {
                start();
                if (next != null) {
                    next.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            runningAnimators.remove(animator);
                            Transition.this.mCurrentAnimators.remove(animator);
                        }

                        public void onAnimationStart(Animator animator) {
                            Transition.this.mCurrentAnimators.add(animator);
                        }
                    });
                    if (getDuration() >= 0) {
                        next.setDuration(getDuration());
                    }
                    long j = this.mStartDelay;
                    if (j >= 0) {
                        next.setStartDelay(j);
                    }
                    TimeInterpolator timeInterpolator = this.mInterpolator;
                    if (timeInterpolator != null) {
                        next.setInterpolator(timeInterpolator);
                    }
                    next.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            Transition.this.end();
                            animator.removeListener(this);
                        }
                    });
                    next.start();
                }
            }
        }
        this.mAnimators.clear();
        end();
    }

    public Transition setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        this.mEpicenterCallback = epicenterCallback;
    }

    public Transition setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public void setPathMotion(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.mPathMotion = STRAIGHT_PATH_MOTION;
        } else {
            this.mPathMotion = pathMotion;
        }
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
    }

    public Transition setStartDelay(long j) {
        this.mStartDelay = j;
        return this;
    }

    /* access modifiers changed from: protected */
    public void start() {
        if (this.mNumInstances == 0) {
            ArrayList<TransitionListener> arrayList = this.mListeners;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.mListeners.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((TransitionListener) arrayList2.get(i)).onTransitionStart(this);
                }
            }
            this.mEnded = false;
        }
        this.mNumInstances++;
    }

    public String toString() {
        return toString("");
    }

    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.mAnimators = new ArrayList<>();
            transition.mStartValues = new TransitionValuesMaps();
            transition.mEndValues = new TransitionValuesMaps();
            transition.mStartValuesList = null;
            transition.mEndValuesList = null;
            return transition;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
        outline13.append(getClass().getSimpleName());
        outline13.append("@");
        outline13.append(Integer.toHexString(hashCode()));
        outline13.append(": ");
        String sb = outline13.toString();
        if (this.mDuration != -1) {
            StringBuilder outline14 = GeneratedOutlineSupport.outline14(sb, "dur(");
            outline14.append(this.mDuration);
            outline14.append(") ");
            sb = outline14.toString();
        }
        if (this.mStartDelay != -1) {
            StringBuilder outline142 = GeneratedOutlineSupport.outline14(sb, "dly(");
            outline142.append(this.mStartDelay);
            outline142.append(") ");
            sb = outline142.toString();
        }
        if (this.mInterpolator != null) {
            sb = GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline14(sb, "interp("), this.mInterpolator, ") ");
        }
        if (this.mTargetIds.size() <= 0 && this.mTargets.size() <= 0) {
            return sb;
        }
        String outline8 = GeneratedOutlineSupport.outline8(sb, "tgts(");
        if (this.mTargetIds.size() > 0) {
            String str2 = outline8;
            for (int i = 0; i < this.mTargetIds.size(); i++) {
                if (i > 0) {
                    str2 = GeneratedOutlineSupport.outline8(str2, ", ");
                }
                StringBuilder outline132 = GeneratedOutlineSupport.outline13(str2);
                outline132.append(this.mTargetIds.get(i));
                str2 = outline132.toString();
            }
            outline8 = str2;
        }
        if (this.mTargets.size() > 0) {
            for (int i2 = 0; i2 < this.mTargets.size(); i2++) {
                if (i2 > 0) {
                    outline8 = GeneratedOutlineSupport.outline8(outline8, ", ");
                }
                StringBuilder outline133 = GeneratedOutlineSupport.outline13(outline8);
                outline133.append(this.mTargets.get(i2));
                outline8 = outline133.toString();
            }
        }
        return GeneratedOutlineSupport.outline8(outline8, ")");
    }
}
