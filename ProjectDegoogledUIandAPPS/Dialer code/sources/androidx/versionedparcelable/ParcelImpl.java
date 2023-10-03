package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new Parcelable.Creator<ParcelImpl>() {
        public Object createFromParcel(Parcel parcel) {
            return new ParcelImpl(parcel);
        }

        public Object[] newArray(int i) {
            return new ParcelImpl[i];
        }
    };
    private final VersionedParcelable mParcel;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: androidx.versionedparcelable.VersionedParcelable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected ParcelImpl(android.os.Parcel r8) {
        /*
            r7 = this;
            r7.<init>()
            androidx.versionedparcelable.VersionedParcelParcel r0 = new androidx.versionedparcelable.VersionedParcelParcel
            int r1 = r8.dataPosition()
            int r2 = r8.dataSize()
            java.lang.String r3 = ""
            r0.<init>(r8, r1, r2, r3)
            java.lang.String r8 = r0.readString()
            r1 = 0
            if (r8 != 0) goto L_0x001a
            goto L_0x0041
        L_0x001a:
            androidx.versionedparcelable.VersionedParcel r0 = r0.createSubParcel()
            java.lang.Class<androidx.versionedparcelable.VersionedParcel> r2 = androidx.versionedparcelable.VersionedParcel.class
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            r3 = 1
            java.lang.Class r8 = java.lang.Class.forName(r8, r3, r2)     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            java.lang.String r2 = "read"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            java.lang.Class<androidx.versionedparcelable.VersionedParcel> r5 = androidx.versionedparcelable.VersionedParcel.class
            r6 = 0
            r4[r6] = r5     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            java.lang.reflect.Method r8 = r8.getDeclaredMethod(r2, r4)     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            r2[r6] = r0     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            java.lang.Object r8 = r8.invoke(r1, r2)     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
            r1 = r8
            androidx.versionedparcelable.VersionedParcelable r1 = (androidx.versionedparcelable.VersionedParcelable) r1     // Catch:{ IllegalAccessException -> 0x006e, InvocationTargetException -> 0x0056, NoSuchMethodException -> 0x004d, ClassNotFoundException -> 0x0044 }
        L_0x0041:
            r7.mParcel = r1
            return
        L_0x0044:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r0 = "VersionedParcel encountered ClassNotFoundException"
            r8.<init>(r0, r7)
            throw r8
        L_0x004d:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r0 = "VersionedParcel encountered NoSuchMethodException"
            r8.<init>(r0, r7)
            throw r8
        L_0x0056:
            r7 = move-exception
            java.lang.Throwable r8 = r7.getCause()
            boolean r8 = r8 instanceof java.lang.RuntimeException
            if (r8 == 0) goto L_0x0066
            java.lang.Throwable r7 = r7.getCause()
            java.lang.RuntimeException r7 = (java.lang.RuntimeException) r7
            throw r7
        L_0x0066:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r0 = "VersionedParcel encountered InvocationTargetException"
            r8.<init>(r0, r7)
            throw r8
        L_0x006e:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r0 = "VersionedParcel encountered IllegalAccessException"
            r8.<init>(r0, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.ParcelImpl.<init>(android.os.Parcel):void");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        new VersionedParcelParcel(parcel, parcel.dataPosition(), parcel.dataSize(), "").writeVersionedParcelable(this.mParcel);
    }
}
