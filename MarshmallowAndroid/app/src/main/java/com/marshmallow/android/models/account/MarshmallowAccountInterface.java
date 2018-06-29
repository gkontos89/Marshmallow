package com.marshmallow.android.models.account;

import com.marshmallow.android.models.transaction.Transaction;

/**
 * Created by George on 6/2/2018.
 */
public interface MarshmallowAccountInterface {

    int getAccountValue();

    void setAccountValue(int value);

    void handleTransaction(Transaction transaction);

    String getAccountName();

    String getUniqueId();

}
