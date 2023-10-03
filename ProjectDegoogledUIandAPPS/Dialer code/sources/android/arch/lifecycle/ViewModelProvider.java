package android.arch.lifecycle;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public class ViewModelProvider {
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = viewModelStore;
    }

    public <T extends ViewModel> T get(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String outline8 = GeneratedOutlineSupport.outline8("android.arch.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            T t = this.mViewModelStore.get(outline8);
            if (cls.isInstance(t)) {
                return t;
            }
            T create = this.mFactory.create(cls);
            this.mViewModelStore.put(outline8, create);
            return create;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
