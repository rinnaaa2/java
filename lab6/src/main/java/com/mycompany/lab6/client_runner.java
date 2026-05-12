package com.mycompany.lab6;

public class client_runner {
    public static void main(String[] args) {
        System.out.println("Запуск 9 клиентов");
        System.out.println("Убедитесь, что сервер уже запущен!");
        
        Thread[] threads = new Thread[9];
        
        for (int i = 1; i <= 9; i++) {
            final int clientId = i;
            final String[] clientArgs = {String.valueOf(clientId)};
            
            threads[i-1] = new Thread(() -> {
                System.out.println("Запуск клиента " + clientId + "...");
                client.main(clientArgs);
            });
            threads[i-1].start();
            
            // Задержка 1 секунда между запусками
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Все 9 клиентов запущены!");
        System.out.println("Сервер и клиенты работают. Для остановки нажмите красную кнопку Stop в NetBeans");
        
        // Ждем завершения всех клиентов
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}