package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(solutions(1, 0, -1));
        System.out.println(solutions(1, 0, 0));
        System.out.println(solutions(1, 0, 1));

        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));

        System.out.println(checkPerfect(6));
        System.out.println(checkPerfect(28));
        System.out.println(checkPerfect(496));
        System.out.println(checkPerfect(12));
        System.out.println(checkPerfect(97));

        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
        System.out.println(flipEndChars("z"));

        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("CD5C5C"));

        System.out.println(same(new int[]{1, 3, 4, 4, 4}, new int[]{2, 5, 7}));
        System.out.println(same(new int[]{9, 8, 7, 6}, new int[]{4, 4, 3, 1}));
        System.out.println(same(new int[]{2}, new int[]{3, 3, 3, 3, 3}));

        System.out.println(isKaprekar(3));
        System.out.println(isKaprekar(5));
        System.out.println(isKaprekar(297));

        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));

        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));

        System.out.println(rightTriangle(3, 4, 5));
        System.out.println(rightTriangle(145, 105, 100));
        System.out.println(rightTriangle(70, 130, 110));


    }

    public static int solutions(int a, int b, int c){
        // применяя формулу дискриминанта и используя глубинные знания математики...
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d == 0) {
            return 1;
        }
        if (d > 0) {
            return 2;
        }
        return 0;
    }

    public static int findZip(String str) {
        // если есть zip и он не единственный
        if(str.contains("zip") && str.indexOf("zip") != str.lastIndexOf("zip")) {
            // возвращаем индекс второго zip, начиная искать с того места где был первый
            return str.indexOf("zip", str.indexOf("zip") + "zip".length());
        }
        return -1;
    }

    public static boolean checkPerfect(int n){
        HashSet<Integer> dividers = new HashSet<>(); // используем множество для того чтобы были уникальные значения
        for (int i = 1; i < n/2; i++) { // ищем делители, добавляем по-парно, можно искать до половины числа
            if (n % i == 0) {
                dividers.add(i);
                if (i != 1) { // по условию в делителях не должно быть самого числа, его мы исключаем
                    dividers.add(n / i);
                }
            }
        }
        return dividers.stream().mapToInt(a -> a).sum() == n;
    }

    public static String flipEndChars(String s){
        if (s.length() < 2) return "Incompatible.";
        if (s.charAt(0) == s.charAt(s.length()-1)) return "Two's a pair.";

        return s.charAt(s.length() - 1) + s.substring(1, s.length() - 1) + s.charAt(0);
    }

    public static boolean isValidHexCode(String s){
        return s.toUpperCase().matches("#[A-F0-9]{0,6}");
    }

    public static boolean same(int[] a, int[] b){
        // пара множеств для уникальных элементов
        HashSet<Integer> aa = new HashSet<>();
        HashSet<Integer> bb = new HashSet<>();
        // перекладываем туда элементы
        Arrays.stream(a).forEach(aa::add);
        Arrays.stream(b).forEach(bb::add);
        // сравниваем размеры
        return aa.size() == bb.size();
    }

    public static boolean isKaprekar(int a){
        String t = String.valueOf(a * a); // для возможности разбить на части сделаем это String
        String a1 = "0"; // пусть по умолчанию правая часть =0, а левая =квадрату
        String a2 = t;
        if (!(t.length() == 1)) {  // если случай не тривиальный, то разбиваем на две части
            a1 = t.substring(0, t.length() / 2); //
            a2 = t.substring(t.length()/ 2);
        }
        return Integer.parseInt(a1) + Integer.parseInt(a2) == a;
    }

    public static String longestZero(String s){
           return s.contains("0") ? Arrays.stream(s.split("1"))
                   .reduce((a, b) -> a.length() >= b.length() ? a : b)
                   .get() : "";

    }


    public static boolean isPrime(int n) {
        // вспомогательная функция чтобы не делать это в основной функции
        for (int i = 2; i < n; i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public static int nextPrime(int n) {
        if (isPrime(n)) return n;

        int k = n;
        for (int i = n; i < n * n; i++) {
            if (isPrime(i)) {
                k = i;
                break;
            }
        }
        return k;
    }
    public static boolean rightTriangle(int a, int b, int c) {
//        по простой формуле, только сразу все в квадрат возвел
         int [] t = Arrays.stream(new int[]{a, b, c}).map(value -> value*value).toArray();
         int l = Arrays.stream(t).max().getAsInt();

         int sum = 0;
         for (int i: t) {
             if (i != l){
                 sum += i;
             }
         }


       return sum == l;
    }





}

