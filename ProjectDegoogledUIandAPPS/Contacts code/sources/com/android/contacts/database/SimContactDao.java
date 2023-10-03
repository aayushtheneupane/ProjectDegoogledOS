package com.android.contacts.database;

import android.content.ContentProviderResult;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import com.android.contacts.database.SimContactDaoImpl;
import com.android.contacts.model.SimCard;
import com.android.contacts.model.SimContact;
import com.android.contacts.model.account.AccountWithDataSet;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SimContactDao {
    public static final Function<Context, SimContactDao> DEFAULT_FACTORY = new Function<Context, SimContactDao>() {
        public SimContactDao apply(Context context) {
            return new SimContactDaoImpl(context);
        }
    };
    private static final boolean USE_FAKE_INSTANCE = false;
    private static Function<? super Context, SimContactDao> sInstanceFactory = DEFAULT_FACTORY;

    public abstract boolean canReadSimContacts();

    public abstract Map<AccountWithDataSet, Set<SimContact>> findAccountsOfExistingSimContacts(List<SimContact> list);

    public abstract SimCard getSimBySubscriptionId(int i);

    public abstract List<SimCard> getSimCards();

    public abstract ContentProviderResult[] importContacts(List<SimContact> list, AccountWithDataSet accountWithDataSet) throws RemoteException, OperationApplicationException;

    public abstract ArrayList<SimContact> loadContactsForSim(SimCard simCard);

    public abstract void persistSimStates(List<SimCard> list);

    private static SimContactDao createDebugInstance(Context context) {
        SimContactDaoImpl.DebugImpl debugImpl = new SimContactDaoImpl.DebugImpl(context);
        debugImpl.addSimCard(new SimCard("fake-sim-id1", 1, "Fake Carrier", "Card 1", "15095550101", "us").withContacts(new SimContact(1, "Sim One", "15095550111", (String[]) null), new SimContact(2, "Sim Two", "15095550112", (String[]) null), new SimContact(3, "Sim Three", "15095550113", (String[]) null), new SimContact(4, "Sim Four", "15095550114", (String[]) null), new SimContact(5, "411 & more", "411", (String[]) null)));
        debugImpl.addSimCard(new SimCard("fake-sim-id2", 2, "Carrier Two", "Card 2", "15095550102", "us").withContacts(new SimContact(1, "John Sim", "15095550121", (String[]) null), new SimContact(2, "Bob Sim", "15095550122", (String[]) null), new SimContact(3, "Mary Sim", "15095550123", (String[]) null), new SimContact(4, "Alice Sim", "15095550124", (String[]) null), new SimContact(5, "Sim Duplicate", "15095550121", (String[]) null)));
        return debugImpl;
    }

    public static synchronized SimContactDao create(Context context) {
        SimContactDao apply;
        synchronized (SimContactDao.class) {
            apply = sInstanceFactory.apply(context);
        }
        return apply;
    }

    public static synchronized void setFactoryForTest(Function<? super Context, SimContactDao> function) {
        synchronized (SimContactDao.class) {
            sInstanceFactory = function;
        }
    }

    public void persistSimState(SimCard simCard) {
        persistSimStates(Collections.singletonList(simCard));
    }
}
