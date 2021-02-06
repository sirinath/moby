package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

public interface Buffer {
   @FunctionalInterface
   interface Op<T> {
      T apply(final T param);
   }

   @FunctionalInterface
   interface OpBoolean {
      boolean apply(final boolean param);
   }

   @FunctionalInterface
   interface OpByte {
      byte apply(final byte param);
   }

   @FunctionalInterface
   interface OpChar {
      char apply(final char param);
   }

   @FunctionalInterface
   interface OpDouble {
      double apply(final double param);
   }

   @FunctionalInterface
   interface OpFloat {
      float apply(final float param);
   }

   @FunctionalInterface
   interface OpInt {
      int apply(final int param);
   }

   @FunctionalInterface
   interface OpLong {
      long apply(final long param);
   }

   @FunctionalInterface
   interface OpShort {
      short apply(final short param);
   }

   Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   long      BIG_ENDIAN_BYTE_OFFSET  = 24;
   long      BIG_ENDIAN_SHORT_OFFSET = 16;
   long      BYTE_INDEX_IN_INT_MASK  = Integer.BYTES - 1;
   long      INT_ALIGNED_MASK        = ~(BYTE_INDEX_IN_INT_MASK);
   ByteOrder NATIVE_BYTE_ORDER       = ByteOrder.nativeOrder();
   boolean   IS_BIG_ENDIAN           = NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN;
   boolean   IS_LITTLE_ENDIAN        = NATIVE_BYTE_ORDER == ByteOrder.LITTLE_ENDIAN;

   static short toBigEndian(final short x) {
      return IS_BIG_ENDIAN ? x : Short.reverseBytes(x);
   }

   static char toBigEndian(final char x) {
      return IS_BIG_ENDIAN ? x : Character.reverseBytes(x);
   }

   static float toBigEndian(final float x) {
      return Float.intBitsToFloat(toBigEndian(Float.floatToRawIntBits(x)));
   }

   static int toBigEndian(final int x) {
      return IS_BIG_ENDIAN ? x : Integer.reverseBytes(x);
   }

   static double toBigEndian(final double x) {
      return Double.longBitsToDouble(toBigEndian(Double.doubleToRawLongBits(x)));
   }

   static long toBigEndian(final long x) {
      return IS_BIG_ENDIAN ? x : Long.reverseBytes(x);
   }

   static char toByteOrder(final char x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Character.reverseBytes(x);
   }

   static double toByteOrder(final double x, final ByteOrder byteOrder) {
      return Double.longBitsToDouble(toByteOrder(Double.doubleToRawLongBits(x), byteOrder));
   }

   static float toByteOrder(final float x, final ByteOrder byteOrder) {
      return Float.intBitsToFloat(toByteOrder(Float.floatToRawIntBits(x), byteOrder));
   }

   static int toByteOrder(final int x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Integer.reverseBytes(x);
   }

   static long toByteOrder(final long x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Long.reverseBytes(x);
   }

   static short toByteOrder(final short x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Short.reverseBytes(x);
   }

   static short toLittleEndian(final short x) {
      return IS_LITTLE_ENDIAN ? x : Short.reverseBytes(x);
   }

   static char toLittleEndian(final char x) {
      return IS_LITTLE_ENDIAN ? x : Character.reverseBytes(x);
   }

   static float toLittleEndian(final float x) {
      return Float.intBitsToFloat(toLittleEndian(Float.floatToRawIntBits(x)));
   }

   static int toLittleEndian(final int x) {
      return IS_LITTLE_ENDIAN ? x : Integer.reverseBytes(x);
   }

   static double toLittleEndian(final double x) {
      return Double.longBitsToDouble(toLittleEndian(Double.doubleToRawLongBits(x)));
   }

   static long toLittleEndian(final long x) {
      return IS_LITTLE_ENDIAN ? x : Long.reverseBytes(x);
   }

   private static long bitIndexInInt(final long byteIndex, final long bigEndianOffset) {
      final long byteInInt            = byteIndex & BYTE_INDEX_IN_INT_MASK;
      final long littleEndianBitIndex = byteInInt << BYTE_INDEX_IN_INT_MASK;
      final long bigEndianBitIndex    = bigEndianOffset - littleEndianBitIndex;

      return IS_BIG_ENDIAN ? bigEndianBitIndex : littleEndianBitIndex;
   }

   private static long bitIndexOfByteInInt(final long byteIndex) {
      return bitIndexInInt(byteIndex, BIG_ENDIAN_BYTE_OFFSET);
   }

   private static long bitIndexOfShortInInt(final long byteIndex) {
      return bitIndexInInt(byteIndex, BIG_ENDIAN_SHORT_OFFSET);
   }

   private static int byteMask(final long bitIndex) {
      return 0xFF << bitIndex;
   }

   private static int combineUnmaskedBits(final int valueInInt, final int unmaskedBits, final int byteMask) {
      return valueInInt | ((~byteMask) & unmaskedBits);
   }

   private static byte extractByteFromInt(final int intValue, final long bitIndex, final int byteMask) {
      return (byte) ((byteMask & intValue) >> bitIndex);
   }

   private static short extractShortFromInt(final int intValue, final long bitIndex, final int shortMask) {
      return (short) ((shortMask & intValue) >> bitIndex);
   }

   private static long intAlignedIndex(final long byteIndex) {
      return byteIndex & INT_ALIGNED_MASK;
   }

   private static int placeByteAtIndex(final byte byteValue, final long bitIndex) {
      return (byteValue & 0xFF) << bitIndex;
   }

   private static int placeShortAtIndex(final short shortValue, final long bitIndex) {
      return (shortValue & 0xFFFF) << bitIndex;
   }

   private static int shortMask(final long bitIndex) {
      return 0xFFFF << bitIndex;
   }

   long getSize();

   long getSizeInBytes();

   boolean compareAndSwapInt(long index, int expected, int x);

   boolean compareAndSwapLong(long index, long expected, long x);

   boolean compareAndSwapObject(long index, Object expected, Object x);

   int getAndAddInt(long index, int x);

   long getAndAddLong(long index, long x);

   int getAndSetInt(long index, int x);

   long getAndSetLong(long index, long x);

   Object getAndSetObject(long index, Object x);

   boolean getBoolean(long index);

   boolean getBooleanVolatile(long index);

   byte getByte(long index);

   byte getByteVolatile(long index);

   char getChar(long index);

   char getCharVolatile(long index);

   double getDouble(long index);

   double getDoubleVolatile(long index);

   float getFloat(long index);

   float getFloatVolatile(long index);

   int getInt(long index);

   int getIntVolatile(long index);

   long getLong(long index);

   long getLongVolatile(long index);

   Object getObject(long index);

   Object getObjectVolatile(long index);

   short getShort(long index);

   short getShortVolatile(long index);

   void putBoolean(long index, boolean x);

   void putBooleanVolatile(long index, boolean x);

   void putByte(long index, byte x);

   void putByteVolatile(long index, byte x);

   void putChar(long index, char x);

   void putCharVolatile(long index, char x);

   void putDouble(long index, double x);

   void putDoubleVolatile(long index, double x);

   void putFloat(long index, float x);

   void putFloatVolatile(long index, float x);

   void putInt(long index, int x);

   void putIntVolatile(long index, int x);

   void putLong(long index, long x);

   void putLongVolatile(long index, long x);

   void putObject(long index, Object x);

   void putObjectVolatile(long index, Object x);

   void putShort(long index, short x);

   void putShortVolatile(long index, short x);

   void putOrderedInt(long index, int x);

   void putOrderedLong(long index, long x);

   void putOrderedObject(long index, Object x);

   void copy(long srcOffset, AbstractDirectBuffer destBuffer, long destOffset, long elements);

   void copy(long srcOffset, AbstractArrayBuffer destBuffer, long destOffset, long elements);

   default void fullFence() {
      UNSAFE.fullFence();
   }

   default void storeFence() {
      UNSAFE.storeFence();
   }

   default void loadFence() {
      UNSAFE.loadFence();
   }

   default boolean compareAndSwapBoolean(final long index, final boolean expected, final boolean x) {
      return compareAndSwapByte(index, (byte) (expected ? 1 : 0), (byte) (x ? 1 : 0));
   }

   default boolean compareAndSwapByte(final long index, final byte expected, final byte x) {
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

   default boolean compareAndSwapChar(final long index, final char expected, final char x, final ByteOrder byteOrder) {
      return compareAndSwapChar(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   default boolean compareAndSwapChar(final long index, final char expected, final char x) {
      return compareAndSwapShort(index, (short) expected, (short) x);
   }

   default boolean compareAndSwapDouble(final long index, final double expected, final double x,
                                        final ByteOrder byteOrder) {
      return compareAndSwapDouble(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   default boolean compareAndSwapDouble(final long index, final double expected, final double x) {
      return compareAndSwapLong(index, Double.doubleToRawLongBits(expected), Double.doubleToRawLongBits(x));
   }

   default boolean compareAndSwapFloat(final long index, final float expected, final float x,
                                       final ByteOrder byteOrder) {
      return compareAndSwapFloat(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   default boolean compareAndSwapFloat(final long index, final float expected, final float x) {
      return compareAndSwapInt(index, Float.floatToRawIntBits(expected), Float.floatToRawIntBits(x));
   }

   default boolean compareAndSwapInt(final long index, final int expected, final int x, final ByteOrder byteOrder) {
      return compareAndSwapInt(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   default boolean compareAndSwapLong(final long index, final long expected, final long x, final ByteOrder byteOrder) {
      return compareAndSwapLong(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   default boolean compareAndSwapShort(final long index, final short expected, final short x,
                                       final ByteOrder byteOrder) {
      return compareAndSwapShort(index, Buffer.toByteOrder(expected, byteOrder), Buffer.toByteOrder(x, byteOrder));
   }

   default boolean compareAndSwapShort(final long index, final short expected, final short x) {
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

   default byte getAndAddByte(final long index, final byte x) {
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

   default double getAndAddDouble(final long index, final double x) {
      double value;
      double newValue;
      do {
         value = getDoubleVolatile(index);
         newValue = value + x;
      } while (!compareAndSwapDouble(index, value, newValue));

      return value;
   }

   default double getAndAddDouble(final long index, final double x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetDouble(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default float getAndAddFloat(final long index, final float x) {
      float value;
      float newValue;
      do {
         value = getFloatVolatile(index);
         newValue = value + x;
      } while (!compareAndSwapFloat(index, value, newValue));

      return value;
   }

   default float getAndAddFloat(final long index, final float x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetFloat(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default int getAndAddInt(final long index, final int x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndAddInt(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default long getAndAddLong(final long index, final long x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetLong(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default short getAndAddShort(final long index, final short x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndAddShort(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default short getAndAddShort(final long index, final short x) {
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

   default boolean getAndSetBoolean(final long index, final boolean x) {
      return getAndSetByte(index, (byte) (x ? 1 : 0)) != 0;
   }

   default byte getAndSetByte(final long index, final byte x) {
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

   default char getAndSetChar(final long index, final char x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetChar(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default char getAndSetChar(final long index, final char x) {
      return (char) getAndSetShort(index, (short) x);
   }

   default double getAndSetDouble(final long index, final double x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetDouble(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default double getAndSetDouble(final long index, final double x) {
      return Double.longBitsToDouble(getAndAddLong(index, Double.doubleToRawLongBits(x)));
   }

   default float getAndSetFloat(final long index, final float x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetFloat(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default float getAndSetFloat(final long index, final float x) {
      return Float.intBitsToFloat(getAndSetInt(index, Float.floatToRawIntBits(x)));
   }

   default int getAndSetInt(final long index, final int x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetInt(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default long getAndSetLong(final long index, final long x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetLong(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default short getAndSetShort(final long index, final short x, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getAndSetShort(index, Buffer.toByteOrder(x, byteOrder)), byteOrder);
   }

   default short getAndSetShort(final long index, final short x) {
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

   default char getChar(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getChar(index), byteOrder);
   }

   default char getCharVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getCharVolatile(index), byteOrder);
   }

   default double getDouble(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getDouble(index), byteOrder);
   }

   default double getDoubleVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getDoubleVolatile(index), byteOrder);
   }

   default float getFloat(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getFloat(index), byteOrder);
   }

   default float getFloatVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getFloatVolatile(index), byteOrder);
   }

   default int getInt(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getInt(index), byteOrder);
   }

   default int getIntVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getIntVolatile(index), byteOrder);
   }

   default long getLong(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getLong(index), byteOrder);
   }

   default long getLongVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getLongVolatile(index), byteOrder);
   }

   default short getShort(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getShort(index), byteOrder);
   }

   default short getShortVolatile(final long index, final ByteOrder byteOrder) {
      return Buffer.toByteOrder(getShortVolatile(index), byteOrder);
   }

   default void putChar(final long index, final char x, final ByteOrder byteOrder) {
      putChar(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putCharVolatile(final long index, final char x, final ByteOrder byteOrder) {
      putCharVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putDouble(final long index, final double x, final ByteOrder byteOrder) {
      putDouble(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putDoubleVolatile(final long index, final double x, final ByteOrder byteOrder) {
      putDoubleVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putFloat(final long index, final float x, final ByteOrder byteOrder) {
      putFloat(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putFloatVolatile(final long index, final float x, final ByteOrder byteOrder) {
      putFloatVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putInt(final long index, final int x, final ByteOrder byteOrder) {
      putInt(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putIntVolatile(final long index, final int x, final ByteOrder byteOrder) {
      putIntVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putLong(final long index, final long x, final ByteOrder byteOrder) {
      putLong(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putLongVolatile(final long index, final long x, final ByteOrder byteOrder) {
      putLongVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putShort(final long index, final short x, final ByteOrder byteOrder) {
      putShort(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putShortVolatile(final long index, final short x, final ByteOrder byteOrder) {
      putShortVolatile(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedBoolean(final long index, final boolean x) {
      putOrderedByte(index, (byte) (x ? 1 : 0));
   }

   default void putOrderedByte(final long index, final byte x) {
      final long intAlignedIndex     = Buffer.intAlignedIndex(index);
      final long bitIndexOfByteInInt = Buffer.bitIndexOfByteInInt(index);
      final int  byteMask            = Buffer.byteMask(bitIndexOfByteInInt);
      final int  byteAtIndex         = Buffer.placeByteAtIndex(x, bitIndexOfByteInInt);
      final int  currentIntValue     = this.getIntVolatile(intAlignedIndex);
      final int  newIntValue         = Buffer.combineUnmaskedBits(byteAtIndex, currentIntValue, byteMask);

      this.putOrderedInt(intAlignedIndex, newIntValue);
   }

   default void putOrderedChar(final long index, final char x, final ByteOrder byteOrder) {
      putOrderedChar(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedChar(final long index, final char x) {
      putOrderedShort(index, (short) x);
   }

   default void putOrderedDouble(final long index, final double x, final ByteOrder byteOrder) {
      putOrderedDouble(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedDouble(final long index, final double x) {
      putOrderedLong(index, Double.doubleToRawLongBits(x));
   }

   default void putOrderedFloat(final long index, final float x, final ByteOrder byteOrder) {
      putOrderedFloat(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedFloat(final long index, final float x) {
      putOrderedInt(index, Float.floatToRawIntBits(x));
   }

   default void putOrderedInt(final long index, final int x, final ByteOrder byteOrder) {
      putOrderedInt(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedLong(final long index, final long x, final ByteOrder byteOrder) {
      putOrderedLong(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedShort(final long index, final short x, final ByteOrder byteOrder) {
      putOrderedShort(index, Buffer.toByteOrder(x, byteOrder));
   }

   default void putOrderedShort(final long index, final short x) {
      final long intAlignedIndex      = Buffer.intAlignedIndex(index);
      final long bitIndexOfShortInInt = Buffer.bitIndexOfShortInInt(index);
      final int  byteMask             = Buffer.shortMask(bitIndexOfShortInInt);
      final int  shortAtIndex         = Buffer.placeShortAtIndex(x, bitIndexOfShortInInt);
      final int  currentIntValue      = this.getIntVolatile(intAlignedIndex);
      final int  newIntValue          = Buffer.combineUnmaskedBits(shortAtIndex, currentIntValue, byteMask);

      this.putOrderedInt(intAlignedIndex, newIntValue);
   }

   default byte getAndApply(final long index, final OpByte op) {
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

   default boolean getAndApply(final long index, final OpBoolean op) {
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

   default short getAndApply(final long index, final OpShort op) {
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

   default short getAndApply(final long index, final OpShort op, final ByteOrder byteOrder) {
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

   default char getAndApply(final long index, final OpChar op) {
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

   default char getAndApply(final long index, final OpChar op, final ByteOrder byteOrder) {
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

   default int getAndApply(final long index, final OpInt op) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = op.apply(value);
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   default int getAndApply(final long index, final OpInt op, final ByteOrder byteOrder) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = Buffer.toByteOrder(op.apply(Buffer.toByteOrder(value, byteOrder)), byteOrder);
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   default long getAndApply(final long index, final OpLong op) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = op.apply(value);
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }

   default long getAndApply(final long index, final OpLong op, final ByteOrder byteOrder) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = Buffer.toByteOrder(op.apply(Buffer.toByteOrder(value, byteOrder)), byteOrder);
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }

   default float getAndApply(final long index, final OpFloat op) {
      int value;
      int newValue;
      do {
         value = getIntVolatile(index);
         newValue = Float.floatToRawIntBits(op.apply(Float.intBitsToFloat(value)));
      } while (!compareAndSwapInt(index, value, newValue));

      return value;
   }

   default float getAndApply(final long index, final OpFloat op, final ByteOrder byteOrder) {
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

   default double getAndApply(final long index, final OpDouble op) {
      long value;
      long newValue;
      do {
         value = getIntVolatile(index);
         newValue = Double.doubleToRawLongBits(op.apply(Double.longBitsToDouble(value)));
      } while (!compareAndSwapLong(index, value, newValue));

      return value;
   }

   default double getAndApply(final long index, final OpDouble op, final ByteOrder byteOrder) {
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

   default boolean checkByteIndexRange(final long positionInBytes, final long elementSizeInBytes) {
      return (positionInBytes >= 0) && ((positionInBytes + elementSizeInBytes) <= getSizeInBytes());
   }

   default boolean checkIndexRange(final long index, final long indexScale) {
      return checkByteIndexRange(index * indexScale, indexScale);
   }

   default boolean checkIndexRangeForBackingArray(final long index) {
      return (index >= 0) && (index < getSize());
   }

   static long calculateIndex(final long baseOffset, final long index, final long scale) {
      return baseOffset + index * scale;
   }

   static void copy(final Object srcBase, final long srcOffset, final Object destBase, final long destOffset,
                    final long bytes) {
      UNSAFE.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
   }

   static void copy(final long srcAddress, final long destAddress, final long bytes) {
      UNSAFE.copyMemory(srcAddress, destAddress, bytes);
   }
}
