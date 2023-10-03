package p000a.p006c.p007a;

import android.database.Cursor;

/* renamed from: a.c.a.d */
interface C0032d {
    void changeCursor(Cursor cursor);

    CharSequence convertToString(Cursor cursor);

    Cursor getCursor();

    Cursor runQueryOnBackgroundThread(CharSequence charSequence);
}
