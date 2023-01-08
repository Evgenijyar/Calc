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
        boolean rom = false;
        if (s.contains(".")||s.contains(",")) return "Исключение";
        String [] expr1 = s.split(" ");
        if (expr1.length<3) return "Исключение";
        if (expr1[0].contains("I")||expr1[0].contains("V")||expr1[0].contains("X")) {
            rom = true;
            if (!expr1[2].contains("I")&&!expr1[2].contains("V")&&!expr1[2].contains("X")) return "Исключение";
        }
        else {
            if (expr1[2].contains("I")||expr1[2].contains("V")||expr1[2].contains("X")) return "Исключение";
        }
        if (!rom){
            int a = Integer.parseInt(expr1[0]);
            int b = Integer.parseInt(expr1[2]);
            int c = 21;
            if (a<1||a>10||b<1||b>10) return "Исключение";
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
            if (c==21) return "Исключение";
            else return String.valueOf(c);
        }
        else {
            int a = romToAr(expr1[0]);
            int b = romToAr(expr1[2]);
            if (a<1||b<1) return "Исключение";
            if (b>=a && Objects.equals(expr1[1], "-")) return "Исключение";
            int c = 21;
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
            if (c == 21||c == 0) return "Исключение";
            else return arToRom(c);
        }
    }
    public static int romToAr (String r){
        String [] romArray = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X"};
        return Arrays.asList(romArray).indexOf(r);
}
    public static String arToRom (int ar){
        TreeMap<Integer, String> arKeys = new TreeMap<>();
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
        do {
            arKey = arKeys.floorKey(ar);
            rom += arKeys.get(arKey);
            ar -= arKey;
        } while (ar != 0);
        return rom;
    }
}