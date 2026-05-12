package com.mycompany.lab6_dopzad_tcp;

import java.io.*;
import java.net.*;

public class client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9876;
    
    public static void main(String[] args) {
        int clientId;
        
        if (args.length > 0) {
            clientId = Integer.parseInt(args[0]);
        } else {
            System.err.println("Ошибка: Не указан ID клиента!");
            System.err.println("Запустите через client_runner или укажите ID: java client <1-9>");
            return;
        }
        
        if (clientId < 1 || clientId > 9) {
            System.out.println("ID клиента должен быть от 1 до 9!");
            return;
        }
        
        System.out.println("Клиент " + clientId + " пытается подключиться к серверу...");
        
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            System.out.println("Клиент " + clientId + " подключился к серверу");
            
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
            dos.writeUTF("REGISTER");
            dos.writeInt(clientId);
            dos.flush();
            System.out.println("Клиент " + clientId + " зарегистрирован на сервере");
            
            while (true) {
                String command = dis.readUTF();
                
                if (command.equals("TASK")) {
                    String taskId = dis.readUTF();
                    double lower = dis.readDouble();
                    double upper = dis.readDouble();
                    double step = dis.readDouble();
                    
                    long startTime = System.nanoTime();
                    double result = calculate(lower, upper, step);
                    long endTime = System.nanoTime();
                    
                    dos.writeUTF("RESULT");
                    dos.writeUTF(taskId);
                    dos.writeDouble(result);
                    dos.flush();
                }
            }
        } catch (ConnectException e) {
            System.err.println("Ошибка: Сервер недоступен!");
        } catch (IOException e) {
            System.err.println("Ошибка клиента " + clientId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static double calculate(double lowerLimit, double upperLimit, double step) {
    try {
        double subInterval = (upperLimit - lowerLimit) / 9;
        Thread[] threads = new Thread[9];
        RecIntegral[] calculators = new RecIntegral[9];
        
        for (int i = 0; i < 9; i++) {
            double threadLower = lowerLimit + i * subInterval;
            double threadUpper;
            
            if (i == 8) {
                threadUpper = upperLimit;
            } else {
                threadUpper = lowerLimit + (i + 1) * subInterval;
            }
            
            calculators[i] = new RecIntegral(threadLower, threadUpper, step);
            threads[i] = new Thread(calculators[i]);
            threads[i].start();
        }
        
        double total = 0.0;
        for (int i = 0; i < 9; i++) {
            threads[i].join();
            total += calculators[i].getRes();
        }
        return total;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}
}