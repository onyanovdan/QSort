package com.company;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(QSort.sort(Set.of(5, 4, 1, 2, 0, -7), Integer::compareTo));
        System.out.println(QSort.sort(List.of(5, 4, 2, 0, -7), Integer::compareTo));
        System.out.println(QSort.sort(List.of("5", "4", "2", "0"), String::compareTo));
        System.out.println(QSort.sort(List.of(buildMyObj(4, "5"), buildMyObj(0, "4"), buildMyObj(10, "2"), buildMyObj(10, "4")),
                Main::compareMyObj));
    }

    private static int compareMyObj(MyObj f, MyObj s) {
        var strComp = f.b.compareTo(s.b);
        if (strComp != 0) {
            strComp /= Math.abs(strComp);
        }
        var intComp = Integer.compare(f.a, s.a);
        if (intComp != 0) {
            intComp /= Math.abs(intComp);
        }
        return intComp < 0 ? strComp - 2 :
                intComp > 0 ? strComp + 2 : strComp;
    }

    private static MyObj buildMyObj(int a, String b) {
        return new MyObj(a, b);
    }

    private static class MyObj {
        private final int a;
        private final String b;
        private MyObj(int a, String b) {
            this.a = a;
            this.b = b;
        }
        public int getA() {
            return a;
        }
        public String getB() {
            return b;
        }

        @Override
        public String toString() {
            return "{a=" + a + ", b=" + b + "}";
        }
    }
}
