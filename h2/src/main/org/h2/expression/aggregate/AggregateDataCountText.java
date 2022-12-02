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
 * Data stored while calculating a COUNT_TEXT aggregate.
 */
final class AggregateDataCountText extends AggregateData {

    private final boolean all;
    private boolean is_target_string;
    private long count;
    private String buffer;

    AggregateDataCountText(boolean all) {
        this.all = all;
    }

    @Override
    void add(SessionLocal session, Value v) {
        buffer = v.getString();
        // System.out.println(buffer.getClass().getName());
        // System.out.println(buffer);
        is_target_string = buffer.equals("ERROR");
        // System.out.println(is_target_string);
        if (all || (v != ValueNull.INSTANCE && is_target_string)) {
            count = count + 1;
        }
    }

    @Override
    Value getValue(SessionLocal session) {
        return ValueBigint.get(count);
    }

}
