package Querys;
import java.util.Scanner;

public class AddUser {

    public static void main(String args[]){
        String cnnStr = "jdbc:sqlserver://arnold-tracker.database.windows.net:1433;database=arnold-tracker;user=team20@arnold-tracker;password={ArnOld2021$};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Answer the next questions in grams");
        System.out.println("What is your desired daily protien intake");
        String gProtien=scanner.nextLine();
        System.out.println("What is your desired daily carb intake");
        String gCarbs=scanner.nextLine();
        System.out.println("What is your desired daily fat intake");
        String gFat= scanner.nextLine();
        ExampleQuery.newUser(gProtien,gCarbs,gFat,cnnStr);
    }
}
