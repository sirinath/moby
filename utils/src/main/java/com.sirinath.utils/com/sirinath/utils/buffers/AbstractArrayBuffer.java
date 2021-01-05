package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

public abstract class AbstractArrayBuffer implements Buffer {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   protected abstract long getIndexScale();

   public final boolean checkIndexRange(final long index, final long indexScale) {
      return checkByteIndexRange(index * indexScale, indexScale);
   }

   public final boolean checkByteIndexRange(final long positionInBytes, final long elementSizeInBytes) {
      return (positionInBytes >= 0) && ((positionInBytes + elementSizeInBytes) <= getSizeInBytes());
   }

   public final boolean checkIndexRangeForBackingArray(final long index) {
      return (index >= 0) && (index < getSize());
   }

   protected abstract long getSize();

   @Override
   public final void loadFence() {
      UNSAFE.loadFence();
   }

   @Override
   public final void storeFence() {
      UNSAFE.storeFence();
   }

   @Override
   public final void fullFence() {
      UNSAFE.fullFence();
   }

   @Override
   public final int getInt(final long index) {
      return UNSAFE.getInt(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final void putInt(final long index, final int x) {
      UNSAFE.putInt(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final Object getObject(final long index) {
      return UNSAFE.getObject(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   protected abstract Object getArray();

   protected final long calculateIndex(final long baseOffset, final long index, final long scale) {
      return baseOffset + index * scale;
   }

   protected abstract long getBaseOffset();

   @Override
   public final void putObject(final long index, final Object x) {
      UNSAFE.putObject(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final boolean getBoolean(final long index) {
      return UNSAFE.getBoolean(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final void putBoolean(final long index, final boolean x) {
      UNSAFE.putBoolean(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE), x);
   }

   @Override
   public final byte getByte(final long index) {
      return UNSAFE.getByte(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final void putByte(final long index, final byte x) {
      UNSAFE.putByte(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final short getShort(final long index) {
      return UNSAFE.getShort(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final void putShort(final long index, final short x) {
      UNSAFE.putShort(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final char getChar(final long index) {
      return UNSAFE.getChar(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final void putChar(final long index, final char x) {
      UNSAFE.putChar(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final long getLong(final long index) {
      return UNSAFE.getLong(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final void putLong(final long index, final long x) {
      UNSAFE.putLong(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final float getFloat(final long index) {
      return UNSAFE.getFloat(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final void putFloat(final long index, final float x) {
      UNSAFE.putFloat(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final double getDouble(final long index) {
      return UNSAFE.getDouble(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final void putDouble(final long index, final double x) {
      UNSAFE.putDouble(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final int getIntVolatile(final long index) {
      return UNSAFE.getIntVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final void putIntVolatile(final long index, final int x) {
      UNSAFE.putIntVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final Object getObjectVolatile(final long index) {
      return UNSAFE.getObjectVolatile(getArray(),
                                      calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final void putObjectVolatile(final long index, final Object x) {
      UNSAFE.putObjectVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final boolean getBooleanVolatile(final long index) {
      return UNSAFE.getBooleanVolatile(getArray(),
                                       calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final void putBooleanVolatile(final long index, final boolean x) {
      UNSAFE.putBooleanVolatile(getArray(),
                                calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE),
                                x);
   }

   @Override
   public final byte getByteVolatile(final long index) {
      return UNSAFE.getByteVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final void putByteVolatile(final long index, final byte x) {
      UNSAFE.putByteVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final short getShortVolatile(final long index) {
      return UNSAFE.getShortVolatile(getArray(),
                                     calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final void putShortVolatile(final long index, final short x) {
      UNSAFE.putShortVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final char getCharVolatile(final long index) {
      return UNSAFE.getCharVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final void putCharVolatile(final long index, final char x) {
      UNSAFE.putCharVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final long getLongVolatile(final long index) {
      return UNSAFE.getLongVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final void putLongVolatile(final long index, final long x) {
      UNSAFE.putLongVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final float getFloatVolatile(final long index) {
      return UNSAFE.getFloatVolatile(getArray(),
                                     calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final void putFloatVolatile(final long index, final float x) {
      UNSAFE.putFloatVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final double getDoubleVolatile(final long index) {
      return UNSAFE.getDoubleVolatile(getArray(),
                                      calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final void putDoubleVolatile(final long index, final double x) {
      UNSAFE.putDoubleVolatile(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedInt(final long index, final int x) {
      UNSAFE.putOrderedInt(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedObject(final long index, final Object x) {
      UNSAFE.putOrderedObject(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedLong(final long index, final long x) {
      UNSAFE.putOrderedLong(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final int getAndSetInt(final long index, final int x) {
      return UNSAFE.getAndSetInt(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final Object getAndSetObject(final long index, final Object x) {
      return UNSAFE.getAndSetObject(getArray(),
                                    calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE),
                                    x);
   }

   @Override
   public final long getAndSetLong(final long index, final long x) {
      return UNSAFE.getAndSetLong(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final int getAndAddInt(final long index, final int x) {
      return UNSAFE.getAndAddInt(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final long getAndAddLong(final long index, final long x) {
      return UNSAFE.getAndAddLong(getArray(), calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final boolean compareAndSwapInt(final long index, final int expected, final int x) {
      return UNSAFE.compareAndSwapInt(getArray(),
                                      calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                      expected,
                                      x);
   }

   @Override
   public final boolean compareAndSwapObject(final long index, final Object expected, final Object x) {
      return UNSAFE.compareAndSwapObject(getArray(),
                                         calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                         expected,
                                         x);
   }

   @Override
   public final boolean compareAndSwapLong(final long index, final long expected, final long x) {
      return UNSAFE.compareAndSwapLong(getArray(),
                                       calculateIndex(getBaseOffset(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                       expected,
                                       x);
   }

   @Override
   public final void putOrderedByte(final long index, final byte x) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      final int  byteAtIndex         = Buffer.placeByteAtIndex(x, bitIndexOfByteInInt);
      final int  currentIntValue     = this.getIntVolatile(intAlignedIndex);
      final int  newIntValue         = Buffer.combineUnmaskedBits(byteAtIndex, currentIntValue, byteMask);

      this.putOrderedInt(intAlignedIndex, newIntValue);
   }

   @Override
   public final void putOrderedBoolean(final long index, final boolean x) {
      putOrderedByte(index, (byte) (x ? 1 : 0));
   }

   @Override
   public final void putOrderedShort(final long index, final short x) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  byteMask             = Buffer.shortMask(bitIndexOfShortInInt);
      final int  shortAtIndex         = Buffer.placeShortAtIndex(x, bitIndexOfShortInInt);
      final int  currentIntValue      = this.getIntVolatile(intAlignedIndex);
      final int  newIntValue          = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, byteMask);

      this.putOrderedInt(intAlignedIndex, newIntValue);
   }

   @Override
   public final void putOrderedChar(final long index, final char x) {
      putOrderedShort(index, (short) x);
   }

   @Override
   public final void putOrderedFloat(final long index, final float x) {
      putOrderedInt(index, Float.floatToRawIntBits(x));
   }

   @Override
   public final void putOrderedDouble(final long index, final double x) {
      putOrderedLong(index, Double.doubleToRawLongBits(x));
   }

   @Override
   public final byte getAndSetByte(final long index, final byte x) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      final int  byteAtIndex         = Buffer.placeByteAtIndex(x, bitIndexOfByteInInt);
      int        currentIntValue;
      int        newIntValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         newIntValue = Buffer.combineUnmaskedBits(byteAtIndex, currentIntValue, byteMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return Buffer.extractByteFromInt(currentIntValue, bitIndexOfByteInInt, byteMask);
   }

   @Override
   public final boolean getAndSetBoolean(final long index, final boolean x) {
      return getAndSetByte(index, (byte) (x ? 1 : 0)) == 0 ? false : true;
   }

   @Override
   public final short getAndSetShort(final long index, final short x) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.byteMask(bitIndexOfShortInInt);
      final int  shortAtIndex         = Buffer.placeShortAtIndex(x, bitIndexOfShortInInt);
      int        currentIntValue;
      int        newIntValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         newIntValue = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, shortMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return Buffer.extractByteFromInt(currentIntValue, bitIndexOfShortInInt, shortMask);
   }

   @Override
   public final char getAndSetChar(final long index, final char x) {
      return (char) getAndSetShort(index, (short) x);
   }

   @Override
   public final float getAndSetFloat(final long index, final float x) {
      return Float.intBitsToFloat(getAndSetInt(index, Float.floatToRawIntBits(x)));
   }

   @Override
   public final double getAndSetDouble(final long index, final double x) {
      return Double.longBitsToDouble(getAndAddLong(index, Double.doubleToRawLongBits(x)));
   }

   @Override
   public final byte getAndAddByte(final long index, final byte x) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      int        byteAtIndex;
      int        currentIntValue;
      int        newIntValue;
      byte       currentValue;
      byte       newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = Buffer.extractByteFromInt(currentIntValue, bitIndexOfByteInInt, byteMask);
         newValue = (byte) (currentValue + x);
         byteAtIndex = Buffer.placeByteAtIndex(newValue, bitIndexOfByteInInt);
         newIntValue = Buffer.combineUnmaskedBits(byteAtIndex, currentIntValue, byteMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final short getAndAddShort(final long index, final short x) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.shortMask(bitIndexOfShortInInt);
      int        shortAtIndex;
      int        currentIntValue;
      int        newIntValue;
      short      currentValue;
      short      newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = Buffer.extractShortFromInt(currentIntValue, bitIndexOfShortInInt, shortMask);
         newValue = (short) (currentValue + x);
         shortAtIndex = Buffer.placeShortAtIndex(newValue, bitIndexOfShortInInt);
         newIntValue = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, shortMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final float getAndAddFloat(final long index, final float x) {
      float value;
      float newValue;
      do {
         value = getFloatVolatile(index);
         newValue = value + x;
      } while (!compareAndSwapFloat(index, value, newValue));

      return value;
   }

   @Override
   public final double getAndAddDouble(final long index, final double x) {
      double value;
      double newValue;
      do {
         value = getDoubleVolatile(index);
         newValue = value + x;
      } while (!compareAndSwapDouble(index, value, newValue));

      return value;
   }

   @Override
   public final boolean compareAndSwapByte(final long index, final byte expected, final byte x) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      final int  xAtIndex            = Buffer.placeByteAtIndex(x, bitIndexOfByteInInt);
      final int  expectedAtIndex     = Buffer.placeByteAtIndex(expected, bitIndexOfByteInInt);
      int        currentIntValue;
      int        expectedIntValue;
      int        newIntValue;

      currentIntValue = this.getIntVolatile(intAlignedIndex);
      expectedIntValue = Buffer.combineUnmaskedBits(expectedAtIndex, currentIntValue, byteMask);
      newIntValue = Buffer.combineUnmaskedBits(xAtIndex, currentIntValue, byteMask);

      return compareAndSwapInt(index, expectedIntValue, newIntValue);
   }

   @Override
   public final boolean compareAndSwapBoolean(final long index, final boolean expected, final boolean x) {
      return compareAndSwapByte(index, (byte) (expected ? 1 : 0), (byte) (x ? 1 : 0));
   }

   @Override
   public final boolean compareAndSwapShort(final long index, final short expected, final short x) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.shortMask(bitIndexOfShortInInt);
      final int  xAtIndex             = Buffer.placeShortAtIndex(x, bitIndexOfShortInInt);
      final int  expectedAtIndex      = Buffer.placeShortAtIndex(expected, bitIndexOfShortInInt);
      int        currentIntValue;
      int        expectedIntValue;
      int        newIntValue;

      currentIntValue = this.getIntVolatile(intAlignedIndex);
      expectedIntValue = Buffer.combineUnmaskedBits(expectedAtIndex, currentIntValue, shortMask);
      newIntValue = Buffer.combineUnmaskedBits(xAtIndex, currentIntValue, shortMask);

      return compareAndSwapInt(index, expectedIntValue, newIntValue);
   }

   @Override
   public final boolean compareAndSwapChar(final long index, final char expected, final char x) {
      return compareAndSwapShort(index, (short) expected, (short) x);
   }

   @Override
   public final boolean compareAndSwapFloat(final long index, final float expected, final float x) {
      return compareAndSwapInt(index, Float.floatToRawIntBits(expected), Float.floatToRawIntBits(x));
   }

   @Override
   public final boolean compareAndSwapDouble(final long index, final double expected, final double x) {
      return compareAndSwapLong(index, Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(x));
   }

   @Override
   public final int getInt(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getInt(index), byteOrder);
   }

   @Override
   public final void putInt(final long index, final int x, final ByteOrder byteOrder) {
      putInt(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final short getShort(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getShort(index), byteOrder);
   }

   @Override
   public final void putShort(final long index, final short x, final ByteOrder byteOrder) {
      putShort(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final char getChar(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getChar(index), byteOrder);
   }

   @Override
   public final void putChar(final long index, final char x, final ByteOrder byteOrder) {
      putChar(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final long getLong(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getLong(index), byteOrder);
   }

   @Override
   public final void putLong(final long index, final long x, final ByteOrder byteOrder) {
      putLong(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final float getFloat(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getFloat(index), byteOrder);
   }

   @Override
   public final void putFloat(final long index, final float x, final ByteOrder byteOrder) {
      putFloat(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final double getDouble(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getDouble(index), byteOrder);
   }

   @Override
   public final void putDouble(final long index, final double x, final ByteOrder byteOrder) {
      putDouble(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final int getIntVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getIntVolatile(index), byteOrder);
   }

   @Override
   public final void putIntVolatile(final long index, final int x, final ByteOrder byteOrder) {
      putIntVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final short getShortVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getShortVolatile(index), byteOrder);
   }

   @Override
   public final void putShortVolatile(final long index, final short x, final ByteOrder byteOrder) {
      putShortVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final char getCharVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getCharVolatile(index), byteOrder);
   }

   @Override
   public final void putCharVolatile(final long index, final char x, final ByteOrder byteOrder) {
      putCharVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final long getLongVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getLongVolatile(index), byteOrder);
   }

   @Override
   public final void putLongVolatile(final long index, final long x, final ByteOrder byteOrder) {
      putLongVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final float getFloatVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getFloatVolatile(index), byteOrder);
   }

   @Override
   public final void putFloatVolatile(final long index, final float x, final ByteOrder byteOrder) {
      putFloatVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final double getDoubleVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getDoubleVolatile(index), byteOrder);
   }

   @Override
   public final void putDoubleVolatile(final long index, final double x, final ByteOrder byteOrder) {
      putDoubleVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final void putOrderedInt(final long index, final int x, final ByteOrder byteOrder) {
      putOrderedInt(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final void putOrderedLong(final long index, final long x, final ByteOrder byteOrder) {
      putOrderedLong(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final int getAndSetInt(final long index, final int x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetInt(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final long getAndSetLong(final long index, final long x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetLong(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final int getAndAddInt(final long index, final int x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndAddInt(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final long getAndAddLong(final long index, final long x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetLong(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final boolean compareAndSwapInt(final long index, final int expected, final int x,
                                          final ByteOrder byteOrder) {
      return compareAndSwapInt(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final boolean compareAndSwapLong(final long index, final long expected, final long x,
                                           final ByteOrder byteOrder) {
      return compareAndSwapLong(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final void putOrderedShort(final long index, final short x, final ByteOrder byteOrder) {
      putOrderedShort(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final void putOrderedChar(final long index, final char x, final ByteOrder byteOrder) {
      putOrderedChar(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final void putOrderedFloat(final long index, final float x, final ByteOrder byteOrder) {
      putOrderedFloat(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final void putOrderedDouble(final long index, final double x, final ByteOrder byteOrder) {
      putOrderedDouble(index, Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final short getAndSetShort(final long index, final short x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetShort(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final char getAndSetChar(final long index, final char x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetChar(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final float getAndSetFloat(final long index, final float x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetFloat(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final double getAndSetDouble(final long index, final double x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetDouble(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final short getAndAddShort(final long index, final short x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndAddShort(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final float getAndAddFloat(final long index, final float x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetFloat(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final double getAndAddDouble(final long index, final double x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetDouble(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   @Override
   public final boolean compareAndSwapShort(final long index, final short expected, final short x,
                                            final ByteOrder byteOrder) {
      return compareAndSwapShort(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final boolean compareAndSwapChar(final long index, final char expected, final char x,
                                           final ByteOrder byteOrder) {
      return compareAndSwapChar(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final boolean compareAndSwapFloat(final long index, final float expected, final float x,
                                            final ByteOrder byteOrder) {
      return compareAndSwapFloat(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final boolean compareAndSwapDouble(final long index, final double expected, final double x,
                                             final ByteOrder byteOrder) {
      return compareAndSwapDouble(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   @Override
   public final byte getAndApply(final long index, final OpByte op) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      int        byteAtIndex;
      int        currentIntValue;
      int        newIntValue;
      byte       currentValue;
      byte       newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = Buffer.extractByteFromInt(currentIntValue, bitIndexOfByteInInt, byteMask);
         newValue = op.apply(currentValue);
         byteAtIndex = Buffer.placeByteAtIndex(newValue, bitIndexOfByteInInt);
         newIntValue = Buffer.combineUnmaskedBits(byteAtIndex, currentIntValue, byteMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final boolean getAndApply(final long index, final OpBoolean op) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      int        byteAtIndex;
      int        currentIntValue;
      int        newIntValue;
      boolean    currentValue;
      boolean    newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = Buffer.extractByteFromInt(currentIntValue, bitIndexOfByteInInt, byteMask) != 0;
         newValue = op.apply(currentValue);
         byteAtIndex = Buffer.placeByteAtIndex((byte) (newValue ? 1 : 0), bitIndexOfByteInInt);
         newIntValue = Buffer.combineUnmaskedBits(byteAtIndex, currentIntValue, byteMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final short getAndApply(final long index, final OpShort op) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.shortMask(bitIndexOfShortInInt);
      int        shortAtIndex;
      int        currentIntValue;
      int        newIntValue;
      short      currentValue;
      short      newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = Buffer.extractShortFromInt(currentIntValue, bitIndexOfShortInInt, shortMask);
         newValue = op.apply(currentValue);
         shortAtIndex = Buffer.placeShortAtIndex(newValue, bitIndexOfShortInInt);
         newIntValue = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, shortMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final short getAndApply(final long index, final OpShort op, final ByteOrder byteOrder) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.shortMask(bitIndexOfShortInInt);
      int        shortAtIndex;
      int        currentIntValue;
      int        newIntValue;
      short      currentValue;
      short      newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = Buffer.extractShortFromInt(currentIntValue, bitIndexOfShortInInt, shortMask);
         newValue = Buffer.toByteOrder(op.apply(Buffer.toByteOrder(currentValue, byteOrder)), byteOrder);
         shortAtIndex = Buffer.placeShortAtIndex(newValue, bitIndexOfShortInInt);
         newIntValue = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, shortMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final char getAndApply(final long index, final OpChar op) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.shortMask(bitIndexOfShortInInt);
      int        shortAtIndex;
      int        currentIntValue;
      int        newIntValue;
      char       currentValue;
      char       newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = (char) Buffer.extractShortFromInt(currentIntValue, bitIndexOfShortInInt, shortMask);
         newValue = op.apply(currentValue);
         shortAtIndex = Buffer.placeShortAtIndex((short) newValue, bitIndexOfShortInInt);
         newIntValue = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, shortMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final char getAndApply(final long index, final OpChar op, final ByteOrder byteOrder) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  shortMask            = Buffer.shortMask(bitIndexOfShortInInt);
      int        shortAtIndex;
      int        currentIntValue;
      int        newIntValue;
      char       currentValue;
      char       newValue;

      do {
         currentIntValue = this.getIntVolatile(intAlignedIndex);
         currentValue = (char) Buffer.extractShortFromInt(currentIntValue, bitIndexOfShortInInt, shortMask);
         newValue = Buffer.toByteOrder(op.apply(Buffer.toByteOrder(currentValue, byteOrder)), byteOrder);
         shortAtIndex = Buffer.placeShortAtIndex((short) newValue, bitIndexOfShortInInt);
         newIntValue = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, shortMask);
      } while (!compareAndSwapInt(index, currentIntValue, newIntValue));

      return currentValue;
   }

   @Override
   public final int getAndApply(final long index, final OpInt op) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = op.apply(value);
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   @Override
   public final int getAndApply(final long index, final OpInt op, final ByteOrder byteOrder) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = Buffer.toByteOrder(op.apply(Buffer.toByteOrder(value, byteOrder)), byteOrder);
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   @Override
   public final long getAndApply(final long index, final OpLong op) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = op.apply(value);
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }

   @Override
   public final long getAndApply(final long index, final OpLong op, final ByteOrder byteOrder) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = Buffer.toByteOrder(op.apply(Buffer.toByteOrder(value, byteOrder)), byteOrder);
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }

   @Override
   public final float getAndApply(final long index, final OpFloat op) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = Float.floatToRawIntBits(op.apply(Float.intBitsToFloat(value)));
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   @Override
   public final float getAndApply(final long index, final OpFloat op, final ByteOrder byteOrder) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = Buffer.toByteOrder(Float.floatToRawIntBits(op.apply(Float.intBitsToFloat(Buffer.toByteOrder(value,
                                                                                                                byteOrder)))),
                                       byteOrder);
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   @Override
   public final double getAndApply(final long index, final OpDouble op) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = Double.doubleToRawLongBits(op.apply(Double.longBitsToDouble(value)));
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }

   @Override
   public final double getAndApply(final long index, final OpDouble op, final ByteOrder byteOrder) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = Buffer.toByteOrder(Double.doubleToRawLongBits(op.apply(Double.longBitsToDouble(Buffer.toByteOrder(
               value,
               byteOrder)))), byteOrder);
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }
}
