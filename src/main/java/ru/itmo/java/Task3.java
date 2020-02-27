package ru.itmo.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {

        if (inputArray == null || inputArray.length == 0) {
            return new int[0];
        }

        int[] ans = new int[inputArray.length];

        ans[0] = inputArray[inputArray.length - 1];
        System.arraycopy(inputArray, 0, ans, 1, inputArray.length - 1);

        return ans;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    long getMaxProduct(int[] inputArray) {

        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }

        if (inputArray.length == 1) {
            return inputArray[0];
        }

        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int a : inputArray) {
            if (a > max1) {
                max2 = max1;
                max1 = a;
            }
            else if (a > max2) {
                max2 = a;
            }

            if (a < min1) {
                min2 = min1;
                min1 = a;
            }
            else if (a < min2) {
                min2 = a;
            }
        }

        if ((long) max1 * max2 > (long) min1 * min2) {
            return (long) max1 * max2;
        }
        else {
            return (long) min1 * min2;
        }
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {

        if (input == null || input.length() == 0) {
            return 0;
        }

        String newInput = input.toLowerCase();
        int    amt      = 0;

        for (char c : newInput.toCharArray()) {
            if (c == 'a' || c == 'b') {
                ++amt;
            }
        }

        return amt * 100 / input.length();
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {

        if (input == null) {
            return false;
        }

        for (int i = 0; i < input.length() / 2; ++i) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {

        if (input == null || input.length() == 0) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        char lastChar = '`';
        int  lastAmt  = 0;
        for (char c : input.toCharArray()) {
            if (c != lastChar) {
                if (lastAmt != 0) {
                    ans.append(lastChar).append(lastAmt);
                }

                lastChar = c;
                lastAmt  = 1;
            }
            else {
                ++lastAmt;
            }
        }
        ans.append(lastChar).append(lastAmt);

        return ans.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {

        if (one == null || two == null || one.length() != two.length() || one.length() == 0) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < one.length(); ++i) {
            if (map1.get(one.charAt(i)) != null) {
                map1.put(one.charAt(i), map1.get(one.charAt(i)) + 1);
            }
            else {
                map1.put(one.charAt(i), 1);
            }

            if (map2.get(two.charAt(i)) != null) {
                map2.put(two.charAt(i), map2.get(two.charAt(i)) + 1);
            }
            else {
                map2.put(two.charAt(i), 1);
            }
        }

        for (var entry : map1.entrySet()) {
            if (!entry.getValue().equals(map2.get(entry.getKey()))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {

        if (s == null || s.length() == 0) {
            return false;
        }

        char[] string = s.toCharArray();
        Arrays.sort(string);
        for (int i = 1; i < string.length; ++i) {
            if (string[i] == string[i - 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {

        if (m == null || m.length == 0 || m[0].length == 0) {
            return new int[][]{{}, {}};
        }

        int[][] matrix = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j) {
                matrix[i][j] = m[j][i];
            }
        }

        return matrix;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {

        if (inputStrings == null || inputStrings.length == 0) {
            return "";
        }
        if (separator == null) {
            separator = ' ';
        }

        StringBuilder builder = new StringBuilder();
        for (String string : inputStrings) {
            builder.append(string).append(separator);
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {

        if (inputStrings == null || inputStrings.length == 0 || prefix == null) {
            return 0;
        }

        int ans = 0;
        for (String string : inputStrings) {
            if (string.startsWith(prefix)) {
                ++ans;
            }
        }

        return ans;
    }
}
