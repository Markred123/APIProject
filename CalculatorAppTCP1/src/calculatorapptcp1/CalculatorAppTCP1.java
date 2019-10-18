/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorapptcp1;
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 *
 * @author Mark
 */
public class CalculatorAppTCP1 {
    
    // declaring the variables for the host and PORT to be used later
    private static InetAddress host;
    private static final int PORT = 1000;
    

 
    public static void main(String[] args) {
        try{
            //tells the host variable to retrieve the localhost
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException e){
            //if there's an error with the host, this returns an error message
            System.out.println("Host Not Found!");
            System.exit(1);
        }
       
      
        //runs the 'run' class
        run();
      
    }
    private static void run(){
        
        Socket link = null;
 

        try{
         
            link = new Socket(host,PORT);
             // scanner declaration
            Scanner sc = new Scanner(System.in);
        
            DataInputStream in = new DataInputStream(link.getInputStream());
            DataOutputStream out = new DataOutputStream(link.getOutputStream()); 
            while(true){
                System.out.println("operand operator operand");
                String calculation = sc.nextLine();
                
                
                if(calculation.equals("stop")){
                    break;
                }
                
                
                else if(calculation.equals("")){
                    System.out.println("Invalid Input");
                }
                
                
                // this sends the info to the server
                out.writeUTF(calculation);
                
                
                String answer = in.readUTF();
                
                
                System.out.println("Answer= " + answer);
            }
            
        }
        catch(IOException e){
            e.printStackTrace();
           
        }
        finally 
        {
            try 
            {
            System.out.println("Closing connection*");
            link.close();				
            }
            catch(IOException e)
            {
                System.out.println("Unable to disconnect");
                System.exit(1);
            }
        }
        
    }
    
}
