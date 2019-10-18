/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorappserver;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

/**
 *
 * @author Mark
 */
public class CalculatorAppServer {
    private static ServerSocket servSock;
    private static final int PORT = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Opening port");
        try{
            servSock = new ServerSocket(PORT);
        }
        catch(IOException e){
            System.out.println("Can't attatch port");
            System.exit(1);
        }
        do{
            run();
        }
        while(true);  
    }
    private static void run(){
        Socket link = null;
        try{
            link = servSock.accept();
            DataInputStream in = new DataInputStream(link.getInputStream());
            DataOutputStream out = new DataOutputStream(link.getOutputStream());
            while(true){
                String calculation = in.readUTF();
                if (calculation.equals("stop")){
                    break;
                }
                System.out.println("The calculation is"+ calculation);
                
                int result = 0;
                
                
                StringTokenizer st = new StringTokenizer(calculation);
                
                
                int operand = Integer.parseInt(st.nextToken());
                
                
                String operation = st.nextToken();
               
                int operand2 = Integer.parseInt(st.nextToken());
                
                if (operation.equals("-")){
                    result = operand - operand2;
                }
                else if(operation.equals("/")){
                    result = operand / operand2;
                }
                else if(operation.equals("+")){
                    result = operand + operand2;
                }
                else if(operation.equals("*")){
                    result = operand*operand2;
                }
                out.writeUTF(Integer.toString(result));
            }
        
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try {
                System.out.println("\n* Closing connection... *");
                link.close();				    //Step 5.
            }
            catch(IOException e)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
         }
    }
    
    
}
