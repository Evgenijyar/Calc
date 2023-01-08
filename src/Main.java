import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;
public class Main {
    public static void main(String[] args) {
        Scanner expr = new Scanner(System.in);
        System.out.println(calc(expr.nextLine()));
    }
    public static String calc(String s) {
        boolean rom = false; // флаг системы счисления, по умолчанию арабск.
        if (s.contains(".")||s.contains(",")) return "Исключение"; // проверка целочисленности
        String [] expr1 = s.split(" ");
        if (expr1.length<3) return "Исключение"; // проверка наличия пробелов
        if (expr1[0].contains("I")||expr1[0].contains("V")||expr1[0].contains("X")) { // определение системы счисления первого числа
            rom = true; // если первое число римск. - переключаем флаг на римск. систему
            if (!expr1[2].contains("I")&&!expr1[2].contains("V")&&!expr1[2].contains("X")) return "Исключение"; // если первое число римск., а второе арабск.
        }
        else {
            if (expr1[2].contains("I")||expr1[2].contains("V")||expr1[2].contains("X")) return "Исключение"; // если первое число арабск., а второе римск.
        }
        if (!rom){ // вычисление выражения с арабскими числами
            int a = Integer.parseInt(expr1[0]);
            int b = Integer.parseInt(expr1[2]);
            int c = 101; // индикатор некорректного знака операции
            if (a<1||a>10||b<1||b>10) return "Исключение"; // проверка диапазона введённых чисел
            if (Objects.equals(expr1[1], "+")) c = a + b;
            else {
                if (Objects.equals(expr1[1], "-")) c = a - b;
                else {
                    if (Objects.equals(expr1[1], "*")) c = a * b;
                    else {
                        if (Objects.equals(expr1[1], "/")) c = a / b;
                    }
                }
            }
            if (c==101) return "Исключение"; // если некорректный знак операции
            else return String.valueOf(c);
        }
        else { // вычисление выражения с римскими числами
            int a = romToAr(expr1[0]);
            int b = romToAr(expr1[2]);
            if (a<1||b<1) return "Исключение"; // проверка диапазона введённых чисел
            if (b>=a && Objects.equals(expr1[1], "-")) return "Исключение"; // проверка на положительный результат при вычитании
            int c = 101; // индикатор некорректного знака операции
            if (Objects.equals(expr1[1], "+")) c = a + b;
            else {
                if (Objects.equals(expr1[1], "-")) c = a - b;
                else {
                    if (Objects.equals(expr1[1], "*")) c = a * b;
                    else {
                        if (Objects.equals(expr1[1], "/")) c = a / b;
                    }
                }
            }
            if (c == 101||c == 0) return "Исключение"; // если некорректный знак или нулевой результат при делении
            else return arToRom(c);
        }
    }
    public static int romToAr (String r){ // конвертер римск. в арабск.
        String [] romArray = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X"}; // номер позиции в строке соответствует значению римского числа
        return Arrays.asList(romArray).indexOf(r);
}
    public static String arToRom (int ar){ // конвертер арабск. в римск.
        TreeMap<Integer, String> arKeys = new TreeMap<>(); // мап с основными паттернами римск. чисел
        arKeys.put(1, "I");
        arKeys.put(4, "IV");
        arKeys.put(5, "V");
        arKeys.put(9, "IX");
        arKeys.put(10, "X");
        arKeys.put(40, "XL");
        arKeys.put(50, "L");
        arKeys.put(90, "XC");
        arKeys.put(100, "C");
        String rom = "";
        int arKey;
        do { // собираем по паттернам римск. число
            arKey = arKeys.floorKey(ar); // ищем ближайший паттерн меньше остатка от конвертируемого числа
            rom += arKeys.get(arKey); // дополняем найденным паттерном результ. строку
            ar -= arKey; // уменьшаем остаток от конвертируемого числа на величину найденного паттерна
        } while (ar != 0);
        return rom;
    }
}
