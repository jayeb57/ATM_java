package ATM;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
//import javafx.scene.control.PasswordField;
import java.io.File;

public class ATM {
    //private int pin;
    private boolean running;
    private Scanner input;
    private static Scanner x;
    private String balance;
    private static double bal;
    private int choice;
    private static String ATMB;
    private static double atmBalance=1000000;
    private static double cAtmB;
    private String username;
    private String gPin;
    private String preB;
    public ATM() {
        input=new Scanner(System.in);
    }
    
    public static void main(String[] args)  {
        
        ATM bracuatm=new ATM();
        ATM bracuatmb=new ATM();
        try{
            System.out.println("BRACU ATM is starting...");
            //cAtmB=bracuatmb.loadATMB();
            bracuatm.runMachine();
            
        } catch(IOException e){
            System.out.println("An error occurred!");
        }
    }
    
    public void runMachine() throws IOException{
        
        running=true;
        
        do{
            
            System.out.println("Go to:\n1. User mode\t2. Admin mode");
            System.out.print("Enter your mode: ");
            int mode=input.nextInt();
            
            if(mode==1){
                userMode();
            }
            
            else if(mode==2){
                adminMode();
            }
            else{
                System.out.println("Wrong mode! Valid modes are only 1 and 2. Please try again.");
            }
            
        }while(running=true);
    }
    
    private void userMode(){
        System.out.println("\n*****Welcome user******\nWhat can I do for you?");
        //int choice;
        do{
            try{
                System.out.println("1.Log in\n2.Exit");
                
                System.out.print("Please enter your choice: ");
                choice=input.nextInt();
                
                if(choice==1){
                    login();
                }
                
                else if(choice==2){
                    System.out.println("Thanks for taking our service. Have a good day!");
                    System.out.println("******************************\n");
                    runMachine();
                }
                else{
                    System.out.println("Wrong input!");
                }
                if(choice!=2){
                    System.out.println("\nWhat to do next?");
                }
            }catch(IOException e){
                System.out.println("An error occurred!");
            }
        }while(choice!=2);
    }
    
    private void login(){
        String filepath="C:\\Users\\HP\\Downloads\\ATM\\ATM\\members.txt";
        boolean found = false;
        String tempUsername="";
        String tempPassword="";
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Please enter your name :");
            username=sc.nextLine();
            System.out.println("Please enter your pin :");
            String password=sc.nextLine();
            password=password.trim();
            try{
                x=new Scanner(new File(filepath));
                x.useDelimiter("[,\n]");
//            System.out.println("Please enter you userID : ");
//            tempUsername=input.nextLine();
//            System.out.println("Please enter you Pin : ");
//            tempPassword=input.nextLine();
                
                while(x.hasNext()&&!found){
                    tempUsername=x.next();
                    tempPassword=x.next();
                    tempPassword=tempPassword.trim();
                    gPin=tempPassword;
                    balance=x.next();
                    balance=balance.trim();
                    preB=balance;
                    bal=Double.parseDouble(balance);
                    //preB=String.valueOf(bal);
                    //System.out.println(bal);
                    
                    if(tempUsername.trim().equals(username.trim())&&tempPassword.trim().equals(password.trim()))
                    {
                        System.out.println("*****Login Successful!*****");
                        found=true;
                        
                    }
                }
                x.close();
                if(found==true){
                    afterLogin();
                }
                if(found==false){
                    System.out.println("Username and/or Pin Wrong");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }while(found==false);
    }
    
    private void afterLogin(){
        int dis;
        System.out.println("What would you like to do?");
        System.out.println("1.Check balance");
        System.out.println("2.Withdraw money");
        System.out.println("3.Change pin");
        System.out.println("4.Logout");
        int choice;
        double with;
        choice=input.nextInt();
        if(choice>4||choice<1){
            System.out.println("Wrong input!");
            afterLogin();
        }
        else{
            if(choice==1){
                System.out.println("Your balance is "+bal+" taka");
                System.out.println();
                afterLogin();
            }
            else if(choice==2){
                do{
                    do{
                        System.out.println("How much would you like to withdraw?");
                        with=input.nextDouble();
                        if(with<100||with>bal){
                            System.out.println("Sorry!Wrong amount!");
                            System.out.println();
                            System.out.println("Enter correct amount");
                            System.out.println();
                        }
                    }while(with<100||with>bal);
                    System.out.println("Are you sure you want to withdraw "+with+" taka?");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    dis=input.nextInt();
                    if(dis==1){
                        System.out.println();
                        System.out.println("Here is "+with+" taka");
                        System.out.println();
                        System.out.println("Your current bank balance is :"+(bal-with)+" taka");
                        System.out.println();
                        bal-=with;
                        updateBalance(bal);
                        atmBalance-=with;
                        afterLogin();
                    }
//                    else if(dis==2){
//                        System.out.println("Hope you come back again! :)");
//                        afterLogin();
//                    }
                }while(dis==2);
            }else if(choice==3){
                changePin();
            }
            else if(choice==4){
                System.out.println("*****Logout Successful!*****");
                userMode();
            }
        }
    }
    
    private void adminMode(){
        
        String filepath="C:\\Users\\HP\\Downloads\\ATM\\ATM\\admin.txt";
        boolean found = false;
        String tempUsername="";
        String tempPassword="";
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Please enter your name :");
            String username=sc.nextLine();
            System.out.println("Please enter your pin :");
            String password=sc.nextLine();
            try{
                x=new Scanner(new File(filepath));
                x.useDelimiter("[,\n]");
                
                while(x.hasNext()&&!found){
                    tempUsername=x.next();
                    tempPassword=x.next();
                    
                    if(tempUsername.trim().equals(username.trim())&&tempPassword.trim().equals(password.trim()))
                    {
                        System.out.println("Login Successful!");
                        found=true;
                        
                    }
                }
                x.close();
                if(found==true){
                    afterAdminLogin();
                }
                if(found==false){
                    System.out.println("Username and/or Pin Wrong");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }while(found==false);
    }
    
    private void afterAdminLogin(){
        
        //int choice;
        do{
            try{
                System.out.println();
                System.out.println("CURRENT ATM BALANCE IS :"+atmBalance+" TAKA.");
                System.out.println();
                
                System.out.println("what do you want to do?");
                System.out.println("1.Refill ATM");
                System.out.println("2.Exit");
                choice=input.nextInt();
                if(choice==1){
                    atmBalance=1000000;
                    System.out.println("ATM has been refilled");
                    afterAdminLogin();
                }
                else if(choice==2){
                    System.out.println("Thank you for you service");
                    runMachine();
                }
                else{
                    System.out.println("Sorry wrong input");
                }
            }catch(Exception e){
                System.out.println(e);
            } 
        }while(choice!=1||choice!=2);
    }
    
    private void changePin(){
        
        //System.out.println(gPin);
        String oldPin=gPin;
        Scanner sc=new Scanner(System.in);
        String filepath="C:\\Users\\HP\\Downloads\\ATM\\ATM\\members.txt";
        System.out.println("Please enter your new pin");
        String newPin=sc.nextLine();
        
        File newFile=new File(filepath);
        String oldC="";
        BufferedReader r=null;
        FileWriter w=null;
        
        try{
            r=new BufferedReader(new FileReader(newFile));
            String line=r.readLine();
            while(line!=null){
                oldC=oldC+line+System.lineSeparator();
                line=r.readLine();
            }
            String newC=oldC.replaceAll(oldPin,newPin);
            w=new FileWriter(newFile);
            w.write(newC);
        }catch(IOException e){
            System.out.println(e);
        }finally{
            try{
                r.close();
                w.close();
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
    
    private void updateBalance(double nbal){
        
        //System.out.println(gPin);
        String oldB=preB;
        //Scanner sc=new Scanner(System.in);
        String filepath="C:\\Users\\HP\\Downloads\\ATM\\ATM\\members.txt";
        String newB=String.valueOf(nbal);
        System.out.println(oldB);
        System.out.println(newB);
        
        File newFile=new File(filepath);
        String oldC="";
        BufferedReader r=null;
        FileWriter w=null;
        
        try{
            System.out.println(oldB);
            System.out.println(newB);
            r=new BufferedReader(new FileReader(newFile));
            String line=r.readLine();
            while(line!=null){
                oldC=oldC+line+System.lineSeparator();
                line=r.readLine();
            }
            String newC=oldC.replaceAll(oldB,newB);
            w=new FileWriter(newFile);
            w.write(newC);
        }catch(IOException e){
            System.out.println(e);
        }finally{
            try{
                r.close();
                w.close();
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }
    private void PrintReceipt(){
        do{
            try{
                System.out.println(*************************************************************);
                System.out.println("                   BRACU ATM RECEIPT                       ");
                System.out.println(*************************************************************);
                
                
                System.out.println("Date:         " + );
                System.out.println("Time:         " + );
                
                
                System.out.println("Card Number:         " + );
                
                System.out.println("Requested Amount:     "+ );
                
                System.out.println("TOTAL AMOUNT:         "+ );
                
                
               System.out.println(*************************************************************);
            }
        }
    }
    
        
//    private double loadATMB(){
//        String filepath="C:\\Users\\HP\\Downloads\\ATM\\ATM\\ATMbalance.txt";
//        try{
//            x=new Scanner(new File(filepath));
//            x.useDelimiter("[,\n]");
//            
//            while(x.hasNext()){
//                ATMB=x.next().trim();
//                atmBalance=Double.valueOf(ATMB);
//            }x.close();
//            
////            String line; int lcount=0;
////            while((line=br.readLine())!=null){
////                lcount++;
////                
////                if(lcount>1){
////                    String[] words=line.split("\t");
////                    products[lcount-2]=new Product(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]));
////                }
////            }
//        }catch(Exception e){
//            System.err.println(e);
//        }
//        return atmBalance;
//    }
}
