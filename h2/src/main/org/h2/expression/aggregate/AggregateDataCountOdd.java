/*
 * Copyright 2004-2022 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (https://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */
package org.h2.expression.aggregate;

import org.h2.api.ErrorCode;
import org.h2.engine.SessionLocal;
import org.h2.message.DbException;
import org.h2.value.Value;
import org.h2.value.ValueBigint;
import org.h2.value.ValueNull;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data stored while calculating a COUNT_ODD aggregate.
 */
final class AggregateDataCountOdd extends AggregateData {

    private final boolean all;
    private boolean odd;
    private long count;
    private ArrayList<Integer> allowedTypes = new ArrayList<>(Arrays.asList(9, 11, 12, 13, 14, 15, 16));

    AggregateDataCountOdd(boolean all) {
        this.all = all;
    }

    @Override
    void add(SessionLocal session, Value v) {
        if (allowedTypes.contains(v.getValueType())) {
            odd = v.getFloat() % 2 != 0;
            if (all || (v != ValueNull.INSTANCE && odd)) {
                count = count + 1;
            }
        } else {
            throw DbException.get(ErrorCode.INVALID_AGGREGATE_DATA_TYPE_ERROR_CODE);
        }
    }

    @Override
    Value getValue(SessionLocal session) {
        return ValueBigint.get(count);
    }

}
