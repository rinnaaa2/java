package com.mycompany.lab6;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    private static final int SERVER_PORT = 9876;
    private static final int CLIENT_BASE_PORT = 9877;
    private static final int THREADS = 9;
    
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
        
        int clientPort = CLIENT_BASE_PORT + clientId;
        
        System.out.println("Клиент " + clientId + " пытается запуститься на порту " + clientPort);
        
        DatagramSocket socket = null;
        try {
            // Пытаемся открыть сокет
            socket = new DatagramSocket(clientPort);
            System.out.println("Клиент " + clientId + " успешно запущен на порту " + clientPort);
            
            InetAddress server = InetAddress.getByName("localhost");//определяем адрес сервера
            
            // пакет для регистрации
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeUTF("REGISTER");
            dos.writeInt(clientId);
            byte[] data = baos.toByteArray();
            socket.send(new DatagramPacket(data, data.length, server, SERVER_PORT)); //отправка пакета
            
            System.out.println("Клиент " + clientId + " зарегистрирован на сервере");
            
            // Ждем задачи
            byte[] buffer = new byte[4096];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
                DataInputStream dis = new DataInputStream(bais);
                
                String command = dis.readUTF();
                
                if (command.equals("TASK")) {
                    String taskId = dis.readUTF();
                    int targetId = dis.readInt();
                    
                    if (targetId != clientId) {
                        System.out.println("Клиент " + clientId + ": задача для клиента " + targetId + ", пропускаю");
                        continue;
                    }
                    
                    double lower = dis.readDouble();
                    double upper = dis.readDouble();
                    double step = dis.readDouble();
                    
                    System.out.println("Клиент " + clientId + " получил задачу: [" + lower + ", " + upper + "]");
                    
                    long startTime = System.nanoTime();
                    double result = calculate(lower, upper, step);
                    long endTime = System.nanoTime();
                    
                    // Отправляем результат после вычисления
                    baos = new ByteArrayOutputStream();
                    dos = new DataOutputStream(baos);
                    dos.writeUTF("RESULT");
                    dos.writeUTF(taskId);
                    dos.writeInt(clientId);
                    dos.writeDouble(result);
                    data = baos.toByteArray();
                    socket.send(new DatagramPacket(data, data.length, server, SERVER_PORT));
                    
                    System.out.println("Клиент " + clientId + " отправил результат: " + result + 
                                     " (время: " + (endTime - startTime)/1000000 + " мс)");
                }
            }
        } catch (BindException e) {
            System.err.println("Ошибка: Порт " + clientPort + " уже занят!");
            System.err.println("Возможно, сервер или другой клиент уже используют этот порт.");
        } catch (Exception e) {
            System.err.println("Ошибка клиента " + clientId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static double calculate(double lowerLimit, double upperLimit, double step) {
        try {
            double interval = (upperLimit - lowerLimit) / 9;
            Thread[] threads = new Thread[9];
            RecIntegral[] calculators = new RecIntegral[9];
            
            for (int i = 0; i < 9; i++) {
                double threadLower = lowerLimit + i * interval;
                double threadUpper = threadLower + interval;
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