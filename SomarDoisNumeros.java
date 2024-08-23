import java.util.*;
public class SomarDoisNumeros{
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int num1, num2,result;
        System.out.println("Digite um numero: ");
       num1 =  scanner.nextInt();
       System.out.println("Digite outro numero: ");
       num2 =  scanner.nextInt();
       result = num1 + num2;
       System.out.println("Soma:" + result);
    }
}