package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

public abstract class AbstractDirectBuffer implements Buffer {
   private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   protected abstract long getIndexScale();

   public final boolean checkIndexRange(final long index, final long indexScale) {
      return checkByteIndexRange(index * indexScale, indexScale);
   }

   public final boolean checkByteIndexRange(final long positionInBytes, final long elementSizeInBytes) {
      return (positionInBytes >= 0) && ((positionInBytes + elementSizeInBytes) <= getSizeInBytes());
   }

   @Override
   public abstract long getSizeInBytes();

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
      return UNSAFE.getInt(calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final void putInt(final long index, final int x) {
      UNSAFE.putInt(calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final Object getObject(final long index) {
      return UNSAFE.getObject(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final void putObject(final long index, final Object x) {
      UNSAFE.putObject(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final boolean getBoolean(final long index) {
      return UNSAFE.getBoolean(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final void putBoolean(final long index, final boolean x) {
      UNSAFE.putBoolean(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE), x);
   }

   @Override
   public final byte getByte(final long index) {
      return UNSAFE.getByte(calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final void putByte(final long index, final byte x) {
      UNSAFE.putByte(calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final short getShort(final long index) {
      return UNSAFE.getShort(calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final void putShort(final long index, final short x) {
      UNSAFE.putShort(calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final char getChar(final long index) {
      return UNSAFE.getChar(calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final void putChar(final long index, final char x) {
      UNSAFE.putChar(calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final long getLong(final long index) {
      return UNSAFE.getLong(calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final void putLong(final long index, final long x) {
      UNSAFE.putLong(calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final float getFloat(final long index) {
      return UNSAFE.getFloat(calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final void putFloat(final long index, final float x) {
      UNSAFE.putFloat(calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final double getDouble(final long index) {
      return UNSAFE.getDouble(calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final void putDouble(final long index, final double x) {
      UNSAFE.putDouble(calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final int getIntVolatile(final long index) {
      return UNSAFE.getIntVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE));
   }

   @Override
   public final void putIntVolatile(final long index, final int x) {
      UNSAFE.putIntVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final Object getObjectVolatile(final long index) {
      return UNSAFE.getObjectVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE));
   }

   @Override
   public final void putObjectVolatile(final long index, final Object x) {
      UNSAFE.putObjectVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final boolean getBooleanVolatile(final long index) {
      return UNSAFE.getBooleanVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE));
   }

   @Override
   public final void putBooleanVolatile(final long index, final boolean x) {
      UNSAFE.putBooleanVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_BOOLEAN_INDEX_SCALE), x);
   }

   @Override
   public final byte getByteVolatile(final long index) {
      return UNSAFE.getByteVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE));
   }

   @Override
   public final void putByteVolatile(final long index, final byte x) {
      UNSAFE.putByteVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_BYTE_INDEX_SCALE), x);
   }

   @Override
   public final short getShortVolatile(final long index) {
      return UNSAFE.getShortVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE));
   }

   @Override
   public final void putShortVolatile(final long index, final short x) {
      UNSAFE.putShortVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_SHORT_INDEX_SCALE), x);
   }

   @Override
   public final char getCharVolatile(final long index) {
      return UNSAFE.getCharVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE));
   }

   @Override
   public final void putCharVolatile(final long index, final char x) {
      UNSAFE.putCharVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_CHAR_INDEX_SCALE), x);
   }

   @Override
   public final long getLongVolatile(final long index) {
      return UNSAFE.getLongVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   @Override
   public final void putLongVolatile(final long index, final long x) {
      UNSAFE.putLongVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final float getFloatVolatile(final long index) {
      return UNSAFE.getFloatVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE));
   }

   @Override
   public final void putFloatVolatile(final long index, final float x) {
      UNSAFE.putFloatVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_FLOAT_INDEX_SCALE), x);
   }

   @Override
   public final double getDoubleVolatile(final long index) {
      return UNSAFE.getDoubleVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE));
   }

   @Override
   public final void putDoubleVolatile(final long index, final double x) {
      UNSAFE.putDoubleVolatile(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_DOUBLE_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedInt(final long index, final int x) {
      UNSAFE.putOrderedInt(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedObject(final long index, final Object x) {
      UNSAFE.putOrderedObject(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final void putOrderedLong(final long index, final long x) {
      UNSAFE.putOrderedLong(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final int getAndSetInt(final long index, final int x) {
      return UNSAFE.getAndSetInt(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final Object getAndSetObject(final long index, final Object x) {
      return UNSAFE.getAndSetObject(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_OBJECT_INDEX_SCALE), x);
   }

   @Override
   public final long getAndSetLong(final long index, final long x) {
      return UNSAFE.getAndSetLong(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final int getAndAddInt(final long index, final int x) {
      return UNSAFE.getAndAddInt(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE), x);
   }

   @Override
   public final long getAndAddLong(final long index, final long x) {
      return UNSAFE.getAndAddLong(null, calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }

   @Override
   public final boolean compareAndSwapInt(final long index, final int expected, final int x) {
      return UNSAFE.compareAndSwapInt(null,
                                      calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                      expected,
                                      x);
   }

   @Override
   public final boolean compareAndSwapObject(final long index, final Object expected, final Object x) {
      return UNSAFE.compareAndSwapObject(null,
                                         calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                         expected,
                                         x);
   }

   @Override
   public final boolean compareAndSwapLong(final long index, final long expected, final long x) {
      return UNSAFE.compareAndSwapLong(null,
                                       calculateIndex(getAddress(), index, Unsafe.ARRAY_INT_INDEX_SCALE),
                                       expected,
                                       x);
   }

   @Override
   public final void putOrderedByte(final long index, final byte x) {

   }

   @Override
   public final void putOrderedBoolean(final long index, final boolean x) {

   }

   @Override
   public final void putOrderedShort(final long index, final short x) {

   }

   @Override
   public final void putOrderedChar(final long index, final char x) {

   }

   @Override
   public final void putOrderedFloat(final long index, final float x) {

   }

   @Override
   public final void putOrderedDouble(final long index, final double x) {

   }

   @Override
   public final byte getAndSetByte(final long index, final byte x) {
      return 0;
   }

   @Override
   public final boolean getAndSetBoolean(final long index, final boolean x) {
      return false;
   }

   @Override
   public final short getAndSetShort(final long index, final short x) {
      return 0;
   }

   @Override
   public final char getAndSetChar(final long index, final char x) {
      return 0;
   }

   @Override
   public final float getAndSetFloat(final long index, final float x) {
      return 0;
   }

   @Override
   public final double getAndSetDouble(final long index, final double x) {
      return 0;
   }

   @Override
   public final byte getAndAddByte(final long index, final byte x) {
      return 0;
   }

   @Override
   public final short getAndAddShort(final long index, final short x) {
      return 0;
   }

   @Override
   public final float getAndAddFloat(final long index, final float x) {
      return 0;
   }

   @Override
   public final double getAndAddDouble(final long index, final double x) {
      return 0;
   }

   @Override
   public final boolean compareAndSwapByte(final long index, final byte expected, final byte x) {
      return false;
   }

   @Override
   public final boolean compareAndSwapBoolean(final long index, final boolean expected, final boolean x) {
      return false;
   }

   @Override
   public final boolean compareAndSwapShort(final long index, final short expected, final short x) {
      return false;
   }

   @Override
   public final boolean compareAndSwapChar(final long index, final char expected, final char x) {
      return false;
   }

   @Override
   public final boolean compareAndSwapFloat(final long index, final float expected, final float x) {
      return false;
   }

   @Override
   public final boolean compareAndSwapDouble(final long index, final double expected, final double x) {
      return false;
   }

   @Override
   public final int getInt(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putInt(final long index, final int x, final ByteOrder byteOrder) {

   }

   @Override
   public final short getShort(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putShort(final long index, final short x, final ByteOrder byteOrder) {

   }

   @Override
   public final char getChar(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putChar(final long index, final char x, final ByteOrder byteOrder) {

   }

   @Override
   public final long getLong(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putLong(final long index, final long x, final ByteOrder byteOrder) {

   }

   @Override
   public final float getFloat(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putFloat(final long index, final float x, final ByteOrder byteOrder) {

   }

   @Override
   public final double getDouble(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putDouble(final long index, final double x, final ByteOrder byteOrder) {

   }

   @Override
   public final int getIntVolatile(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putIntVolatile(final long index, final int x, final ByteOrder byteOrder) {

   }

   @Override
   public final short getShortVolatile(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putShortVolatile(final long index, final short x, final ByteOrder byteOrder) {

   }

   @Override
   public final char getCharVolatile(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putCharVolatile(final long index, final char x, final ByteOrder byteOrder) {

   }

   @Override
   public final long getLongVolatile(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putLongVolatile(final long index, final long x, final ByteOrder byteOrder) {

   }

   @Override
   public final float getFloatVolatile(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putFloatVolatile(final long index, final float x, final ByteOrder byteOrder) {

   }

   @Override
   public final double getDoubleVolatile(final long index, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final void putDoubleVolatile(final long index, final double x, final ByteOrder byteOrder) {

   }

   @Override
   public final void putOrderedInt(final long index, final int x, final ByteOrder byteOrder) {

   }

   @Override
   public final void putOrderedLong(final long index, final long x, final ByteOrder byteOrder) {

   }

   @Override
   public final int getAndSetInt(final long index, final int x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final long getAndSetLong(final long index, final long x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final int getAndAddInt(final long index, final int x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final long getAndAddLong(final long index, final long x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final boolean compareAndSwapInt(final long index, final int expected, final int x, final ByteOrder byteOrder) {
      return false;
   }

   @Override
   public final boolean compareAndSwapLong(final long index, final long expected, final long x, final ByteOrder byteOrder) {
      return false;
   }

   @Override
   public final void putOrderedShort(final long index, final short x, final ByteOrder byteOrder) {

   }

   @Override
   public final void putOrderedChar(final long index, final char x, final ByteOrder byteOrder) {

   }

   @Override
   public final void putOrderedFloat(final long index, final float x, final ByteOrder byteOrder) {

   }

   @Override
   public final void putOrderedDouble(final long index, final double x, final ByteOrder byteOrder) {

   }

   @Override
   public final short getAndSetShort(final long index, final short x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final char getAndSetChar(final long index, final char x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final float getAndSetFloat(final long index, final float x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final double getAndSetDouble(final long index, final double x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final short getAndAddShort(final long index, final short x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final float getAndAddFloat(final long index, final float x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final double getAndAddDouble(final long index, final double x, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final boolean compareAndSwapShort(final long index, final short expected, final short x,
                                      final ByteOrder byteOrder) {
      return false;
   }

   @Override
   public final boolean compareAndSwapChar(final long index, final char expected, final char x, final ByteOrder byteOrder) {
      return false;
   }

   @Override
   public final boolean compareAndSwapFloat(final long index, final float expected, final float x,
                                      final ByteOrder byteOrder) {
      return false;
   }

   @Override
   public final boolean compareAndSwapDouble(final long index, final double expected, final double x,
                                       final ByteOrder byteOrder) {
      return false;
   }

   @Override
   public final byte getAndApply(final long index, final OpByte op) {
      return 0;
   }

   @Override
   public final boolean getAndApply(final long index, final OpBoolean op) {
      return false;
   }

   @Override
   public final short getAndApply(final long index, final OpShort op) {
      return 0;
   }

   @Override
   public final short getAndApply(final long index, final OpShort op, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final char getAndApply(final long index, final OpChar op) {
      return 0;
   }

   @Override
   public final char getAndApply(final long index, final OpChar op, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final int getAndApply(final long index, final OpInt op) {
      return 0;
   }

   @Override
   public final int getAndApply(final long index, final OpInt op, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final long getAndApply(final long index, final OpLong op) {
      return 0;
   }

   @Override
   public final long getAndApply(final long index, final OpLong op, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final float getAndApply(final long index, final OpFloat op) {
      return 0;
   }

   @Override
   public final float getAndApply(final long index, final OpFloat op, final ByteOrder byteOrder) {
      return 0;
   }

   @Override
   public final double getAndApply(final long index, final OpDouble op) {
      return 0;
   }

   @Override
   public final double getAndApply(final long index, final OpDouble op, final ByteOrder byteOrder) {
      return 0;
   }

   protected final long calculateIndex(final long address, final long index, final long scale) {
      return address + index * scale;
   }

   protected abstract long getAddress();

   public final boolean checkIndexRangeForBackingArray(final long index) {
      return (index >= 0) && (index < getSize());
   }

   protected abstract long getSize();

   public final long getAddress(final long index) {
      return UNSAFE.getAddress(calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE));
   }

   public final void putAddress(final long index, final long x) {
      UNSAFE.putAddress(calculateIndex(getAddress(), index, Unsafe.ARRAY_LONG_INDEX_SCALE), x);
   }
}
