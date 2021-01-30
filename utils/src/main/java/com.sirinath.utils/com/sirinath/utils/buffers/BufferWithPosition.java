package com.sirinath.utils.buffers;

import com.sirinath.utils.UnsafeAccess;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

public interface BufferWithPosition extends Buffer {
   Unsafe UNSAFE = UnsafeAccess.UNSAFE;

   long getPositionFieldOffset();

   default <T extends AbstractArrayBuffer & BufferWithPosition> void copyToArrayBuffer(final T destBuffer,
                                                                                      final long elements) {
      copy(getPosition(), destBuffer, destBuffer.getPosition(), elements);
   }

   default <T extends AbstractArrayBuffer & BufferWithPosition> void copyToDirectBuffer(final T destBuffer,
                                                                                       final long elements) {
      copy(getPosition(), destBuffer, destBuffer.getPosition(), elements);
   }

   default long getAndDecrementPosition() {
      return UNSAFE.getAndAddLong(this, getPositionFieldOffset(), -1);
   }

   default long getAndIncrementPosition() {
      return UNSAFE.getAndAddLong(this, getPositionFieldOffset(), +1);
   }

   default long getPosition() {
      return UNSAFE.getLongVolatile(this, getPositionFieldOffset());
   }

   default void putPosition(final long position) {
      UNSAFE.putLongVolatile(this, getPositionFieldOffset(), position);
   }

   default boolean compareAndSetPosition(final long expectedPosition, final long newPosition) {
      return UNSAFE.compareAndSwapLong(this, getPositionFieldOffset(), expectedPosition, newPosition);
   }

   default long getAndAddToPosition(final long offset) {
      return UNSAFE.getAndAddLong(this, getPositionFieldOffset(), offset);
   }

   default long getAndSetPosition(final long position) {
      return UNSAFE.getAndSetLong(this, getPositionFieldOffset(), position);
   }

   default boolean getBoolean() {
      return getBoolean(getPosition());
   }

   default boolean getBooleanVolatile() {
      return getBooleanVolatile(getPosition());
   }

   default byte getByte() {
      return getByte(getPosition());
   }

   default byte getByteVolatile() {
      return getByteVolatile(getPosition());
   }

   default char getChar() {
      return getChar(getPosition());
   }

   default char getCharVolatile() {
      return getCharVolatile(getPosition());
   }

   default double getDouble() {
      return getDouble(getPosition());
   }

   default double getDoubleVolatile() {
      return getDoubleVolatile(getPosition());
   }

   default float getFloat() {
      return getFloat(getPosition());
   }

   default float getFloatVolatile() {
      return getFloatVolatile(getPosition());
   }

   default int getInt() {
      return getInt(getPosition());
   }

   default int getIntVolatile() {
      return getIntVolatile(getPosition());
   }

   default long getLong() {
      return getLong(getPosition());
   }

   default long getLongVolatile() {
      return getLongVolatile(getPosition());
   }

   default Object getObject() {
      return getObject(getPosition());
   }

   default Object getObjectVolatile() {
      return getObjectVolatile(getPosition());
   }

   default short getShort() {
      return getShort(getPosition());
   }

   default short getShortVolatile() {
      return getShortVolatile(getPosition());
   }

   default boolean compareAndSwapBoolean(boolean expected, boolean x) {
      return compareAndSwapBoolean(getPosition(), expected, x);
   }

   default boolean compareAndSwapByte(byte expected, byte x) {
      return compareAndSwapByte(getPosition(), expected, x);
   }

   default boolean compareAndSwapChar(char expected, char x) {
      return compareAndSwapChar(getPosition(), expected, x);
   }

   default boolean compareAndSwapChar(char expected, char x, ByteOrder byteOrder) {
      return compareAndSwapChar(getPosition(), expected, x, byteOrder);
   }

   default boolean compareAndSwapDouble(double expected, double x) {
      return compareAndSwapDouble(getPosition(), expected, x);
   }

   default boolean compareAndSwapDouble(double expected, double x, ByteOrder byteOrder) {
      return compareAndSwapDouble(getPosition(), expected, x, byteOrder);
   }

   default boolean compareAndSwapFloat(float expected, float x) {
      return compareAndSwapFloat(getPosition(), expected, x);
   }

   default boolean compareAndSwapFloat(float expected, float x, ByteOrder byteOrder) {
      return compareAndSwapFloat(getPosition(), expected, x, byteOrder);
   }

   default boolean compareAndSwapInt(int expected, int x) {
      return compareAndSwapInt(getPosition(), expected, x);
   }

   default boolean compareAndSwapInt(int expected, int x, ByteOrder byteOrder) {
      return compareAndSwapInt(getPosition(), expected, x, byteOrder);
   }

   default boolean compareAndSwapLong(long expected, long x) {
      return compareAndSwapLong(getPosition(), expected, x);
   }

   default boolean compareAndSwapLong(long expected, long x, ByteOrder byteOrder) {
      return compareAndSwapLong(getPosition(), expected, x, byteOrder);
   }

   default boolean compareAndSwapObject(Object expected, Object x) {
      return compareAndSwapObject(getPosition(), expected, x);
   }

   default boolean compareAndSwapShort(short expected, short x) {
      return compareAndSwapShort(getPosition(), expected, x);
   }

   default boolean compareAndSwapShort(short expected, short x, ByteOrder byteOrder) {
      return compareAndSwapShort(getPosition(), expected, x, byteOrder);
   }

   default byte getAndAddByte(byte x) {
      return getAndAddByte(getPosition(), x);
   }

   default double getAndAddDouble(double x) {
      return getAndAddDouble(getPosition(), x);
   }

   default double getAndAddDouble(double x, ByteOrder byteOrder) {
      return getAndAddDouble(getPosition(), x, byteOrder);
   }

   default float getAndAddFloat(float x) {
      return getAndAddFloat(getPosition(), x);
   }

   default float getAndAddFloat(float x, ByteOrder byteOrder) {
      return getAndAddFloat(getPosition(), x, byteOrder);
   }

   default int getAndAddInt(int x) {
      return getAndAddInt(getPosition(), x);
   }

   default int getAndAddInt(int x, ByteOrder byteOrder) {
      return getAndAddInt(getPosition(), x, byteOrder);
   }

   default long getAndAddLong(long x) {
      return getAndAddLong(getPosition(), x);
   }

   default long getAndAddLong(long x, ByteOrder byteOrder) {
      return getAndAddLong(getPosition(), x, byteOrder);
   }

   default short getAndAddShort(short x) {
      return getAndAddShort(getPosition(), x);
   }

   default short getAndAddShort(short x, ByteOrder byteOrder) {
      return getAndAddShort(getPosition(), x, byteOrder);
   }

   default boolean getAndSetBoolean(boolean x) {
      return getAndSetBoolean(getPosition(), x);
   }

   default byte getAndSetByte(byte x) {
      return getAndSetByte(getPosition(), x);
   }

   default char getAndSetChar(char x) {
      return getAndSetChar(getPosition(), x);
   }

   default char getAndSetChar(char x, ByteOrder byteOrder) {
      return getAndSetChar(getPosition(), x, byteOrder);
   }

   default double getAndSetDouble(double x) {
      return getAndSetDouble(getPosition(), x);
   }

   default double getAndSetDouble(double x, ByteOrder byteOrder) {
      return getAndSetDouble(getPosition(), x, byteOrder);
   }

   default float getAndSetFloat(float x) {
      return getAndSetFloat(getPosition(), x);
   }

   default float getAndSetFloat(float x, ByteOrder byteOrder) {
      return getAndSetFloat(getPosition(), x, byteOrder);
   }

   default int getAndSetInt(int x) {
      return getAndSetInt(getPosition(), x);
   }

   default int getAndSetInt(int x, ByteOrder byteOrder) {
      return getAndSetInt(getPosition(), x, byteOrder);
   }

   default long getAndSetLong(long x) {
      return getAndSetLong(getPosition(), x);
   }

   default long getAndSetLong(long x, ByteOrder byteOrder) {
      return getAndSetLong(getPosition(), x, byteOrder);
   }

   default Object getAndSetObject(Object x) {
      return getAndSetObject(getPosition(), x);
   }

   default short getAndSetShort(short x) {
      return getAndSetShort(getPosition(), x);
   }

   default short getAndSetShort(short x, ByteOrder byteOrder) {
      return getAndSetShort(getPosition(), x, byteOrder);
   }

   default char getChar(ByteOrder byteOrder) {
      return getChar(getPosition(), byteOrder);
   }

   default char getCharVolatile(ByteOrder byteOrder) {
      return getCharVolatile(getPosition(), byteOrder);
   }

   default double getDouble(ByteOrder byteOrder) {
      return getDouble(getPosition(), byteOrder);
   }

   default double getDoubleVolatile(ByteOrder byteOrder) {
      return getDoubleVolatile(getPosition(), byteOrder);
   }

   default float getFloat(ByteOrder byteOrder) {
      return getFloat(getPosition(), byteOrder);
   }

   default float getFloatVolatile(ByteOrder byteOrder) {
      return getFloatVolatile(getPosition(), byteOrder);
   }

   default int getInt(ByteOrder byteOrder) {
      return getInt(getPosition(), byteOrder);
   }

   default int getIntVolatile(ByteOrder byteOrder) {
      return getIntVolatile(getPosition(), byteOrder);
   }

   default long getLong(ByteOrder byteOrder) {
      return getLong(byteOrder);
   }

   default long getLongVolatile(ByteOrder byteOrder) {
      return getLongVolatile(getPosition(), byteOrder);
   }

   default short getShort(ByteOrder byteOrder) {
      return getShort(getPosition(), byteOrder);
   }

   default short getShortVolatile(ByteOrder byteOrder) {
      return getShortVolatile(getPosition(), byteOrder);
   }

   default void putBoolean(boolean x) {
      putBoolean(getPosition(), x);
   }

   default void putBooleanVolatile(boolean x) {
      putBooleanVolatile(getPosition(), x);
   }

   default void putByte(byte x) {
      putByte(getPosition(), x);
   }

   default void putByteVolatile(byte x) {
      putByteVolatile(getPosition(), x);
   }

   default void putChar(char x) {
      putChar(getPosition(), x);
   }

   default void putChar(char x, ByteOrder byteOrder) {
      putChar(getPosition(), x, byteOrder);
   }

   default void putCharVolatile(char x) {
      putCharVolatile(getPosition(), x);
   }

   default void putCharVolatile(char x, ByteOrder byteOrder) {
      putCharVolatile(getPosition(), x, byteOrder);
   }

   default void putDouble(double x) {
      putDouble(getPosition(), x);
   }

   default void putDouble(double x, ByteOrder byteOrder) {
      putDouble(getPosition(), x, byteOrder);
   }

   default void putDoubleVolatile(double x) {
      putDoubleVolatile(getPosition(), x);
   }

   default void putDoubleVolatile(double x, ByteOrder byteOrder) {
      putDoubleVolatile(getPosition(), x, byteOrder);
   }

   default void putFloat(float x) {
      putFloat(getPosition(), x);
   }

   default void putFloat(float x, ByteOrder byteOrder) {
      putFloat(getPosition(), x, byteOrder);
   }

   default void putFloatVolatile(float x) {
      putFloatVolatile(getPosition(), x);
   }

   default void putFloatVolatile(float x, ByteOrder byteOrder) {
      putFloatVolatile(getPosition(), x, byteOrder);
   }

   default void putInt(int x) {
      putInt(getPosition(), x);
   }

   default void putInt(int x, ByteOrder byteOrder) {
      putInt(getPosition(), x, byteOrder);
   }

   default void putIntVolatile(int x) {
      putIntVolatile(getPosition(), x);
   }

   default void putIntVolatile(int x, ByteOrder byteOrder) {
      putIntVolatile(getPosition(), x, byteOrder);
   }

   default void putLong(long x) {
      putLong(getPosition(), x);
   }

   default void putLong(long x, ByteOrder byteOrder) {
      putLong(getPosition(), x, byteOrder);
   }

   default void putLongVolatile(long x) {
      putLongVolatile(getPosition(), x);
   }

   default void putLongVolatile(long x, ByteOrder byteOrder) {
      putLongVolatile(getPosition(), x, byteOrder);
   }

   default void putObject(Object x) {
      putObject(getPosition(), x);
   }

   default void putObjectVolatile(Object x) {
      putObjectVolatile(getPosition(), x);
   }

   default void putShort(short x) {
      putShort(getPosition(), x);
   }

   default void putShort(short x, ByteOrder byteOrder) {
      putShort(getPosition(), x, byteOrder);
   }

   default void putShortVolatile(short x) {
      putShortVolatile(getPosition(), x);
   }

   default void putShortVolatile(short x, ByteOrder byteOrder) {
      putShortVolatile(getPosition(), x, byteOrder);
   }

   default void putOrderedBoolean(boolean x) {
      putOrderedBoolean(getPosition(), x);
   }

   default void putOrderedByte(byte x) {
      putOrderedByte(getPosition(), x);
   }

   default void putOrderedChar(char x) {
      putOrderedChar(getPosition(), x);
   }

   default void putOrderedChar(char x, ByteOrder byteOrder) {
      putOrderedChar(getPosition(), x, byteOrder);
   }

   default void putOrderedDouble(double x) {
      putOrderedDouble(getPosition(), x);
   }

   default void putOrderedDouble(double x, ByteOrder byteOrder) {
      putOrderedDouble(getPosition(), x, byteOrder);
   }

   default void putOrderedFloat(float x) {
      putOrderedFloat(getPosition(), x);
   }

   default void putOrderedFloat(float x, ByteOrder byteOrder) {
      putOrderedFloat(getPosition(), x, byteOrder);
   }

   default void putOrderedInt(int x) {
      putOrderedInt(getPosition(), x);
   }

   default void putOrderedInt(int x, ByteOrder byteOrder) {
      putOrderedInt(getPosition(), x, byteOrder);
   }

   default void putOrderedLong(long x) {
      putOrderedLong(getPosition(), x);
   }

   default void putOrderedLong(long x, ByteOrder byteOrder) {
      putOrderedLong(getPosition(), x, byteOrder);
   }

   default void putOrderedObject(Object x) {
      putOrderedObject(getPosition(), x);
   }

   default void putOrderedShort(short x) {
      putOrderedShort(getPosition(), x);
   }

   default void putOrderedShort(short x, ByteOrder byteOrder) {
      putOrderedShort(getPosition(), x, byteOrder);
   }

   default byte getAndApply(OpByte op) {
      return getAndApply(getPosition(), op);
   }

   default boolean getAndApply(OpBoolean op) {
      return getAndApply(getPosition(), op);
   }

   default short getAndApply(OpShort op) {
      return getAndApply(getPosition(), op);
   }

   default short getAndApply(OpShort op, ByteOrder byteOrder) {
      return getAndApply(getPosition(), op, byteOrder);
   }

   default char getAndApply(OpChar op) {
      return getAndApply(getPosition(), op);
   }

   default char getAndApply(OpChar op, ByteOrder byteOrder) {
      return getAndApply(getPosition(), op, byteOrder);
   }

   default int getAndApply(OpInt op) {
      return getAndApply(getPosition(), op);
   }

   default int getAndApply(OpInt op, ByteOrder byteOrder) {
      return getAndApply(op, byteOrder);
   }

   default long getAndApply(OpLong op) {
      return getAndApply(op);
   }

   default long getAndApply(OpLong op, ByteOrder byteOrder) {
      return getAndApply(op, byteOrder);
   }

   default float getAndApply(OpFloat op) {
      return getAndApply(op);
   }

   default float getAndApply(OpFloat op, ByteOrder byteOrder) {
      return getAndApply(op, byteOrder);
   }

   default double getAndApply(OpDouble op) {
      return getAndApply(op);
   }

   default double getAndApply(OpDouble op, ByteOrder byteOrder) {
      return getAndApply(op, byteOrder);
   }
}