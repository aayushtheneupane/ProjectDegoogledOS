package androidx.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.C0442A;
import androidx.lifecycle.C0443B;
import androidx.lifecycle.C0449f;
import androidx.lifecycle.C0450g;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.C0455l;
import androidx.lifecycle.C0465v;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import androidx.savedstate.C0607c;
import androidx.savedstate.C0608d;
import androidx.savedstate.C0609e;

public class ComponentActivity extends androidx.core.app.ComponentActivity implements C0453j, C0443B, C0609e, C0125g {
    private int mContentLayoutId;
    private final C0455l mLifecycleRegistry;
    private final C0124f mOnBackPressedDispatcher;
    private final C0608d mSavedStateRegistryController;
    private C0442A mViewModelStore;

    public ComponentActivity() {
        this.mLifecycleRegistry = new C0455l(this);
        this.mSavedStateRegistryController = C0608d.m958b(this);
        this.mOnBackPressedDispatcher = new C0124f(new C0120b(this));
        if (getLifecycle() != null) {
            int i = Build.VERSION.SDK_INT;
            getLifecycle().mo4460a(new C0449f() {
                /* renamed from: a */
                public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
                    if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                        Window window = ComponentActivity.this.getWindow();
                        View peekDecorView = window != null ? window.peekDecorView() : null;
                        if (peekDecorView != null) {
                            peekDecorView.cancelPendingInputEvents();
                        }
                    }
                }
            });
            getLifecycle().mo4460a(new C0449f() {
                /* renamed from: a */
                public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
                    if (lifecycle$Event == Lifecycle$Event.ON_DESTROY && !ComponentActivity.this.isChangingConfigurations()) {
                        ComponentActivity.this.getViewModelStore().clear();
                    }
                }
            });
            int i2 = Build.VERSION.SDK_INT;
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        C0121c cVar = (C0121c) getLastNonConfigurationInstance();
        if (cVar != null) {
            return cVar.custom;
        }
        return null;
    }

    public C0450g getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final C0124f getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public final C0607c getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    public C0442A getViewModelStore() {
        if (getApplication() != null) {
            if (this.mViewModelStore == null) {
                C0121c cVar = (C0121c) getLastNonConfigurationInstance();
                if (cVar != null) {
                    this.mViewModelStore = cVar.f125Bl;
                }
                if (this.mViewModelStore == null) {
                    this.mViewModelStore = new C0442A();
                }
            }
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public void onBackPressed() {
        this.mOnBackPressedDispatcher.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.mo5286g(bundle);
        C0465v.m488a(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public final Object onRetainNonConfigurationInstance() {
        C0121c cVar;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        C0442A a = this.mViewModelStore;
        if (a == null && (cVar = (C0121c) getLastNonConfigurationInstance()) != null) {
            a = cVar.f125Bl;
        }
        if (a == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        C0121c cVar2 = new C0121c();
        cVar2.custom = onRetainCustomNonConfigurationInstance;
        cVar2.f125Bl = a;
        return cVar2;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        C0450g lifecycle = getLifecycle();
        if (lifecycle instanceof C0455l) {
            ((C0455l) lifecycle).mo4465a(Lifecycle$State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.mo5285f(bundle);
    }

    public ComponentActivity(int i) {
        this();
        this.mContentLayoutId = i;
    }
}
