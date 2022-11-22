/*
 * Copyright 2004-2022 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (https://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.expression.aggregate;

import org.h2.engine.SessionLocal;
import org.h2.value.Value;
import org.h2.value.ValueBigint;
import org.h2.value.ValueNull;

/**
 * Data stored while calculating a COUNT aggregate.
 */
final class AggregateDataCountEven extends AggregateData {

    private final boolean all;
    private boolean even;
    private long count;

    AggregateDataCountEven(boolean all) {
        this.all = all;
    }

    @Override
    void add(SessionLocal session, Value v) {
        System.out.println(v.getFloat());
        even = v.getFloat() % 2 == 0;
        if (all || (v != ValueNull.INSTANCE && even)) {
            count = count + 1;
        }
    }

    @Override
    Value getValue(SessionLocal session) {
        return ValueBigint.get(count);
    }

}
