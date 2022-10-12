package org.h2.value;


import org.h2.engine.CastDataProvider;

/**
 * Implementation of the MATRIX data type.
 */
public class ValueMatrix extends Value{

    final float[][] matrix;

    public ValueMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public StringBuilder getSQL() {
        StringBuilder builder = new StringBuilder().append('[');
        for (int i = 0; i < matrix.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(matrix[i].getString());
        }
        return builder.append(']').toString();
    }

    @Override
    public TypeInfo getType() {
        return null;
    }

    @Override
    public int getValueType() {
        return 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public int compareTypeSafe(Value v, CompareMode mode, CastDataProvider provider) {
        return 0;
    }
}
