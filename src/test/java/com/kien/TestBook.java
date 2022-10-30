package com.kien;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import sun.misc.Unsafe;

@TestMethodOrder(OrderAnnotation.class)
public class TestBook {

    static ArrayList<Integer> testArrayListReflection;
    static ArrayList<Integer> testArrayListIterator;
    static Unsafe unsafe;
    static Field arrayField;
    static Object[] elementData;

    //@RepeatedTest(100)
    public void testArrayListReflection()
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        testArrayListReflection = new ArrayList<>(IntStream.range(1, 1000000).boxed().toList());
        

        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafe = (Unsafe) f.get(null);
        
        arrayField = ArrayList.class.getDeclaredField("elementData");

        elementData = (Object[]) unsafe.getObject(testArrayListReflection, unsafe.objectFieldOffset(arrayField));
        for (int i = 0; i < elementData.length; i++) {

        }

    }

    @RepeatedTest(100)
    public void testArrayListIterator() {
        testArrayListIterator = new ArrayList<>(IntStream.range(1, 1000000).boxed().toList());
        for(Object integer: testArrayListIterator){

        }
    }
}
