package com.sirinath.utils.buffers;

import java.nio.ByteOrder;

public interface Buffer {
   ByteOrder NATIVE_BYTE_ORDER = ByteOrder.nativeOrder();
   boolean   IS_BIG_ENDIAN     = NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN;
   boolean   IS_LITTLE_ENDIAN  = NATIVE_BYTE_ORDER == ByteOrder.LITTLE_ENDIAN;

   long BYTE_INDEX_IN_INT_MASK  = Integer.BYTES - 1;
   long INT_ALIGNED_MASK        = ~(BYTE_INDEX_IN_INT_MASK);
   long BIG_ENDIAN_BYTE_OFFSET  = 24;
   long BIG_ENDIAN_SHORT_OFFSET = 16;

   long getSizeInBytes();

   void loadFence();

   void storeFence();

   void fullFence();

   int getInt(long index);

   void putInt(long index, int x);

   Object getObject(long index);

   void putObject(long index, Object x);

   boolean getBoolean(long index);

   void putBoolean(long index, boolean x);

   byte getByte(long index);

   void putByte(long index, byte x);

   short getShort(long index);

   void putShort(long index, short x);

   char getChar(long index);

   void putChar(long index, char x);

   long getLong(long index);

   void putLong(long index, long x);

   float getFloat(long index);

   void putFloat(long index, float x);

   double getDouble(long index);

   void putDouble(long index, double x);

   int getIntVolatile(long index);

   void putIntVolatile(long index, int x);

   Object getObjectVolatile(long index);

   void putObjectVolatile(long index, Object x);

   boolean getBooleanVolatile(long index);

   void putBooleanVolatile(long index, boolean x);

   byte getByteVolatile(long index);

   void putByteVolatile(long index, byte x);

   short getShortVolatile(long index);

   void putShortVolatile(long index, short x);

   char getCharVolatile(long index);

   void putCharVolatile(long index, char x);

   long getLongVolatile(long index);

   void putLongVolatile(long index, long x);

   float getFloatVolatile(long index);

   void putFloatVolatile(long index, float x);

   double getDoubleVolatile(long index);

   void putDoubleVolatile(long index, double x);

   void putOrderedInt(long index, int x);

   void putOrderedObject(long index, Object x);

   void putOrderedLong(long index, long x);

   int getAndSetInt(long index, int x);

   Object getAndSetObject(long index, Object x);

   long getAndSetLong(long index, long x);

   int getAndAddInt(long index, int x);

   long getAndAddLong(long index, long x);

   boolean compareAndSwapInt(long index, int expected, int x);

   boolean compareAndSwapObject(long index, Object expected, Object x);

   boolean compareAndSwapLong(long index, long expected, long x);

   void putOrderedByte(long index, byte x);

   void putOrderedBoolean(long index, boolean x);

   void putOrderedShort(long index, short x);

   void putOrderedChar(long index, char x);

   void putOrderedFloat(long index, float x);

   void putOrderedDouble(long index, double x);

   byte getAndSetByte(long index, byte x);

   boolean getAndSetBoolean(long index, boolean x);

   short getAndSetShort(long index, short x);

   char getAndSetChar(long index, char x);

   float getAndSetFloat(long index, float x);

   double getAndSetDouble(long index, double x);

   byte getAndAddByte(long index, byte x);

   short getAndAddShort(long index, short x);

   float getAndAddFloat(long index, float x);

   double getAndAddDouble(long index, double x);

   boolean compareAndSwapByte(long index, byte expected, byte x);

   boolean compareAndSwapBoolean(long index, boolean expected, boolean x);

   boolean compareAndSwapShort(long index, short expected, short x);

   boolean compareAndSwapChar(long index, char expected, char x);

   boolean compareAndSwapFloat(long index, float expected, float x);

   boolean compareAndSwapDouble(long index, double expected, double x);

   int getInt(long index, ByteOrder byteOrder);

   void putInt(long index, int x, ByteOrder byteOrder);

   short getShort(long index, ByteOrder byteOrder);

   void putShort(long index, short x, ByteOrder byteOrder);

   char getChar(long index, ByteOrder byteOrder);

   void putChar(long index, char x, ByteOrder byteOrder);

   long getLong(long index, ByteOrder byteOrder);

   void putLong(long index, long x, ByteOrder byteOrder);

   float getFloat(long index, ByteOrder byteOrder);

   void putFloat(long index, float x, ByteOrder byteOrder);

   double getDouble(long index, ByteOrder byteOrder);

   void putDouble(long index, double x, ByteOrder byteOrder);

   int getIntVolatile(long index, ByteOrder byteOrder);

   void putIntVolatile(long index, int x, ByteOrder byteOrder);

   short getShortVolatile(long index, ByteOrder byteOrder);

   void putShortVolatile(long index, short x, ByteOrder byteOrder);

   char getCharVolatile(long index, ByteOrder byteOrder);

   void putCharVolatile(long index, char x, ByteOrder byteOrder);

   long getLongVolatile(long index, ByteOrder byteOrder);

   void putLongVolatile(long index, long x, ByteOrder byteOrder);

   float getFloatVolatile(long index, ByteOrder byteOrder);

   void putFloatVolatile(long index, float x, ByteOrder byteOrder);

   double getDoubleVolatile(long index, ByteOrder byteOrder);

   void putDoubleVolatile(long index, double x, ByteOrder byteOrder);

   void putOrderedInt(long index, int x, ByteOrder byteOrder);

   void putOrderedLong(long index, long x, ByteOrder byteOrder);

   int getAndSetInt(long index, int x, ByteOrder byteOrder);

   long getAndSetLong(long index, long x, ByteOrder byteOrder);

   int getAndAddInt(long index, int x, ByteOrder byteOrder);

   long getAndAddLong(long index, long x, ByteOrder byteOrder);

   boolean compareAndSwapInt(long index, int expected, int x, ByteOrder byteOrder);

   boolean compareAndSwapLong(long index, long expected, long x, ByteOrder byteOrder);

   void putOrderedShort(long index, short x, ByteOrder byteOrder);

   void putOrderedChar(long index, char x, ByteOrder byteOrder);

   void putOrderedFloat(long index, float x, ByteOrder byteOrder);

   void putOrderedDouble(long index, double x, ByteOrder byteOrder);

   short getAndSetShort(long index, short x, ByteOrder byteOrder);

   char getAndSetChar(long index, char x, ByteOrder byteOrder);

   float getAndSetFloat(long index, float x, ByteOrder byteOrder);

   double getAndSetDouble(long index, double x, ByteOrder byteOrder);

   short getAndAddShort(long index, short x, ByteOrder byteOrder);

   float getAndAddFloat(long index, float x, ByteOrder byteOrder);

   double getAndAddDouble(long index, double x, ByteOrder byteOrder);

   boolean compareAndSwapShort(long index, short expected, short x, ByteOrder byteOrder);

   boolean compareAndSwapChar(long index, char expected, char x, ByteOrder byteOrder);

   boolean compareAndSwapFloat(long index, float expected, float x, ByteOrder byteOrder);

   boolean compareAndSwapDouble(long index, double expected, double x, ByteOrder byteOrder);

   byte getAndApply(long index, OpByte op);

   boolean getAndApply(long index, OpBoolean op);

   short getAndApply(long index, OpShort op);

   short getAndApply(long index, OpShort op, ByteOrder byteOrder);

   char getAndApply(long index, OpChar op);

   char getAndApply(long index, OpChar op, ByteOrder byteOrder);

   int getAndApply(long index, OpInt op);

   int getAndApply(long index, OpInt op, ByteOrder byteOrder);

   long getAndApply(long index, OpLong op);

   long getAndApply(long index, OpLong op, ByteOrder byteOrder);

   float getAndApply(long index, OpFloat op);

   float getAndApply(long index, OpFloat op, ByteOrder byteOrder);

   double getAndApply(long index, OpDouble op);

   double getAndApply(long index, OpDouble op, ByteOrder byteOrder);

   static long intAlignedIndex(final long byteIndex) {
      return byteIndex & INT_ALIGNED_MASK;
   }

   static long bitIndexOfByteInInt(final long byteIndex) {
      return bitIndexInInt(byteIndex, BIG_ENDIAN_BYTE_OFFSET);
   }

   private static long bitIndexInInt(final long byteIndex, final long bigEndianOffset) {
      final long byteInInt            = byteIndex & BYTE_INDEX_IN_INT_MASK;
      final long littleEndianBitIndex = byteInInt << BYTE_INDEX_IN_INT_MASK;
      final long bigEndianBitIndex    = bigEndianOffset - littleEndianBitIndex;

      return IS_BIG_ENDIAN ? bigEndianBitIndex : littleEndianBitIndex;
   }

   static long bitIndexOfShortInInt(final long byteIndex) {
      return bitIndexInInt(byteIndex, BIG_ENDIAN_SHORT_OFFSET);
   }

   static int byteMask(final long bitIndex) {
      return 0xFF << bitIndex;
   }

   static int shortMask(final long bitIndex) {
      return 0xFFFF << bitIndex;
   }

   static int placeByteAtIndex(final byte byteValue, final long bitIndex) {
      return (byteValue & 0xFF) << bitIndex;
   }

   static int placeShortAtIndex(final short shortValue, final long bitIndex) {
      return (shortValue & 0xFFFF) << bitIndex;
   }

   static int combineUnmaskedBits(final int valueInInt, final int unmaskedBits, final int byteMask) {
      return valueInInt | ((~byteMask) & unmaskedBits);
   }

   static byte extractByteFromInt(final int intValue, final long bitIndex, final int byteMask) {
      return (byte) ((byteMask & intValue) >> bitIndex);
   }

   static short extractShortFromInt(final int intValue, final long bitIndex, final int shortMask) {
      return (short) ((shortMask & intValue) >> bitIndex);
   }

   static short toByteOrder(final short x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Short.reverseBytes(x);
   }

   static char toByteOrder(final char x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Character.reverseBytes(x);
   }

   static float toByteOrder(final float x, final ByteOrder byteOrder) {
      return Float.intBitsToFloat(toByteOrder(Float.floatToRawIntBits(x), byteOrder));
   }

   static int toByteOrder(final int x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Integer.reverseBytes(x);
   }

   static double toByteOrder(final double x, final ByteOrder byteOrder) {
      return Double.longBitsToDouble(toByteOrder(Double.doubleToRawLongBits(x), byteOrder));
   }

   static long toByteOrder(final long x, final ByteOrder byteOrder) {
      return NATIVE_BYTE_ORDER == byteOrder ? x : Long.reverseBytes(x);
   }

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

   @FunctionalInterface
   interface Op<T> {
      T apply(final T param);
   }

   @FunctionalInterface
   interface OpByte {
      byte apply(final byte param);
   }

   @FunctionalInterface
   interface OpBoolean {
      boolean apply(final boolean param);
   }

   @FunctionalInterface
   interface OpShort {
      short apply(final short param);
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
   interface OpFloat {
      float apply(final float param);
   }

   @FunctionalInterface
   interface OpDouble {
      double apply(final double param);
   }

   @FunctionalInterface
   interface OpChar {
      char apply(final char param);
   }
}
