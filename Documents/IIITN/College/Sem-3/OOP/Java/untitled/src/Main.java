import java.util.Scanner;
import java.lang.Math;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("enter no I J K : such that I>=10 and j<=31");
        System.out.println("Enter value of I: ");
        int i = sc.nextInt();
        if(i<10){
            System.out.println("Enter value of I greater than 10 as, if the Day number is less than 10, i.e. a single digit number, the difference will be 0, and will always be divisible by k. Also hence, the day in that case will be beautiful.: ");
            return;
        }

        System.out.println("Enter value of J: ");
        int j = sc.nextInt();
        if(j>31){
            System.out.println("Enter value of J less than 31, as there are only 31 days in a month");
            return;
        }

        System.out.println("Enter value of K: ");
        int k = sc.nextInt();


        int count=0;
        for(int day=i;day<=j;day++){
            int secondDigit = day/10;
            int firstDigit = day-(secondDigit*10);
            int reverseDay = firstDigit*10 + secondDigit;
            int f = Math.abs(day-reverseDay);
            if(f%k==0){
                count++;
            }

        }
        System.out.println("The number of beautiful days between I and J are: " + count);
        System.out.println("Hence, F1 will go on movie date with F2 on " + count + " number of days.");

    }
}