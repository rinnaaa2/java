package com.mycompany.lab6_dopzad_tcp;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.*;
import java.net.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author Марина
 */
public class server extends javax.swing.JFrame { //наследование
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(server.class.getName());
    private DefaultTableModel tableModel;
    private ArrayList<RecIntegral> dataList;

    private RecIntegral editingRecord = null; //ссылка
    private int editingRow = -1;

    //TCP
    private ServerSocket serverSocket;
    private static final int SERVER_PORT = 9876;
    private static final int EXPECTED_CLIENTS = 9; 

    private Map<Integer, Socket> clients = new HashMap<>();
    private Map<String, double[]> tasks = new HashMap<>(); // id задачи - промежуточный результат
    private Map<String, Integer> taskRows = new HashMap<>(); // id задачи - строка в таблице
    private Map<Integer, DataOutputStream> clientsOutputs = new HashMap<>();
    private final double[] totalResult = {0.0};
    private final int[] completedSteps = {0};
    private int totalSteps = 0;
    private int finalSelectRow = -1;

    public server() {
    initComponents();
    tableModel = (javax.swing.table.DefaultTableModel) jTable1.getModel();
    dataList = new ArrayList<>();
    //сохраняем старые значения
    jTable1.addPropertyChangeListener(evt -> {
        if ("tableCellEditor".equals(evt.getPropertyName())) {
            if (jTable1.isEditing()) {
                editingRow = jTable1.getEditingRow();
                int editingCol = jTable1.getEditingColumn();

                if (editingRow >= 0 && editingCol >= 0 && editingCol <= 2) {
                    editingRecord = findRecordForTableRow(editingRow);
                    System.out.println("Начато редактирование. Запись в коллекции: " +
                                     (editingRecord != null ? "найдена" : "НЕ найдена"));
                }
            }
        }
    });


    tableModel.addTableModelListener(e -> {
        if (e.getType() == TableModelEvent.UPDATE && e.getColumn() <= 2 && e.getFirstRow() >= 0) {
            // Очищаем результат
            tableModel.setValueAt("", e.getFirstRow(), 3);

            if (editingRecord != null) {
                updateEditingRecord(e.getFirstRow());
                editingRecord = null;
                editingRow = -1; // Сбрасываем после обновления
            }
        }
    });
    startServer();
    }

private void startServer() {
    try {
        serverSocket = new ServerSocket(SERVER_PORT);
        new Thread(() -> {
            System.out.println("Сервер запущен на порту " + SERVER_PORT);
            
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                    String command = dis.readUTF();
                    if (command.equals("REGISTER")) {
                        final int clientId = dis.readInt();
                        clients.put(clientId, clientSocket);
                        clientsOutputs.put(clientId, dos);
                        
                        System.out.println("Клиент " + clientId + " подключился");
                        System.out.println("Подключено клиентов: " + clients.size() + " из " + EXPECTED_CLIENTS);
                        
                        final DataInputStream finaldis = dis;
                        new Thread(() -> {
                            try {
                                while (true) {
                                    String cmd = finaldis.readUTF();
                                    
                                    if (cmd.equals("RESULT")) {
                                        String taskId = finaldis.readUTF();
                                        double result = finaldis.readDouble();
                                        
                                        double[] taskData = tasks.get(taskId);
                                        if (taskData != null) {
                                            synchronized (taskData) {
                                                taskData[0] += result;
                                                taskData[1]++;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Клиент " + clientId + " отключился");
                                clients.remove(clientId);
                                clientsOutputs.remove(clientId);
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
// Поиск записи в коллекции по строке таблицы
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        Vnesti = new javax.swing.JButton();
        Rasschet = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ydalit = new javax.swing.JButton();
        Zapolnit = new javax.swing.JButton();
        Ochistit = new javax.swing.JButton();
        Vnesti1 = new javax.swing.JButton();
        Vnesti2 = new javax.swing.JButton();
        Vnesti3 = new javax.swing.JButton();
        Vnesti4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(153, 0, 204));

        jLabel1.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N
        jLabel1.setText("Function: cos (x^2)");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.SE_RESIZE_CURSOR));

        jLabel3.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        jLabel3.setText("Данные для интегрирования:");

        jLabel4.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        jLabel4.setText("Верхний предел:");

        jLabel5.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        jLabel5.setText("Нижний предел:");

        jLabel6.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        jLabel6.setText("Шаг:");

        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        Vnesti.setText("Внести ");
        Vnesti.addActionListener(this::Vnesti);

        Rasschet.setText("Рассчитать");
        Rasschet.addActionListener(this::Rasschet);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Нижний предел", "Верхний предел", "Шаг", "Результат"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column){
                return column != 3;
            }
        });
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTable1ComponentHidden(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        ydalit.setText("Удалить строку");
        ydalit.addActionListener(this::ydalitActionPerformed);

        Zapolnit.setText("Заполнить");
        Zapolnit.addActionListener(this::Zapolnit);

        Ochistit.setText("Очистить");
        Ochistit.addActionListener(this::Ochistit);

        Vnesti1.setText("Загрузка из .txt ");
        Vnesti1.addActionListener(this::Zagruzka_txt);

        Vnesti2.setText("Загрузка из .ser");
        Vnesti2.addActionListener(this::Zagruzka_bin);

        Vnesti3.setText("Сохранение в .txt");
        Vnesti3.addActionListener(this::Save_txt);

        Vnesti4.setText("Сохранение в .ser");
        Vnesti4.addActionListener(this::Save_bin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Vnesti)
                    .addComponent(Rasschet))
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Zapolnit)
                    .addComponent(Ochistit))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Vnesti2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Vnesti4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Vnesti1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Vnesti3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ydalit)
                        .addGap(30, 30, 30))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Vnesti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rasschet))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ydalit)
                    .addComponent(Zapolnit)
                    .addComponent(Vnesti1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Vnesti3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ochistit)
                    .addComponent(Vnesti2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Vnesti4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(95, 95, 95))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private RecIntegral findRecordForTableRow(int rowIndex) {
    try {
        double lowerLimit = Double.parseDouble(tableModel.getValueAt(rowIndex, 0).toString());
        double upperLimit = Double.parseDouble(tableModel.getValueAt(rowIndex, 1).toString());
        double step = Double.parseDouble(tableModel.getValueAt(rowIndex, 2).toString());

        // Ищем запись с такими же параметрами
        for (RecIntegral rec : dataList) {
            if (rec.getLowerLimit() == lowerLimit &&
                rec.getUpperLimit() == upperLimit &&
                rec.getStep() == step) {
                return rec;
            }
        }

        return null;

    } catch (Exception e) {
        return null;
    }
}

// Обновление редактируемой записи
private void updateEditingRecord(int rowIndex) {
    try {
        double newLowerLimit = Double.parseDouble(tableModel.getValueAt(rowIndex, 0).toString());
        double newUpperLimit = Double.parseDouble(tableModel.getValueAt(rowIndex, 1).toString());
        double newStep = Double.parseDouble(tableModel.getValueAt(rowIndex, 2).toString());

        editingRecord.setLowerLimit(newLowerLimit);
        editingRecord.setUpperLimit(newUpperLimit);
        editingRecord.setStep(newStep);
        editingRecord.clearResult();

        System.out.println("Запись в коллекции обновлена: " +
                         newLowerLimit + " | " + newUpperLimit + " | " + newStep);
    } catch (Exception e) {
        System.out.println("Ошибка при обновлении записи: " + e.getMessage());
    }
}

    // Метод для обновления таблицы из коллекции
    private void updateTableFromList() {
        tableModel.setRowCount(0);
        for (RecIntegral rec : dataList) {
            String resultStr = rec.hasResult() ?
                String.format("%.6f", rec.getResult()).replace('.','.') : "";
            tableModel.addRow(new Object[]{
                rec.getLowerLimit(),
                rec.getUpperLimit(),
                rec.getStep(),
                resultStr
            });
        }
    }

    private void updateCollection() {

    for (int i = 0; i < tableModel.getRowCount(); i++) {
        try {
            double lowerLimit = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            double upperLimit = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            double step = Double.parseDouble(tableModel.getValueAt(i, 2).toString());

            Object resultObj = tableModel.getValueAt(i, 3);
            boolean hasResult = resultObj != null && !resultObj.toString().trim().isEmpty();

            // Ищем существующую запись
            RecIntegral existingRec = findExistingRecord(lowerLimit, upperLimit, step);

            if (existingRec != null) {
                // Обновляем результат, если он есть
                if (hasResult) {
                    double result = Double.parseDouble(resultObj.toString().trim().replace(',', '.'));
                    existingRec.setResult(result);
                }
            } else {
                // Добавляем новую запись
                RecIntegral rec;
                if (hasResult) {
                    double result = Double.parseDouble(resultObj.toString().trim().replace(',', '.'));
                    rec = new RecIntegral(lowerLimit, upperLimit, step, result);
                } else {
                    rec = new RecIntegral(lowerLimit, upperLimit, step);
                }
                dataList.add(rec);
            }

        } catch (Exception e) {
            System.out.println("Ошибка в строке " + i + ": " + e.getMessage());
        }
    }

    System.out.println("Сохранено в коллекцию: " + dataList.size() + " записей");
}

private RecIntegral findExistingRecord(double lowerLimit, double upperLimit, double step) {
    for (RecIntegral rec : dataList) {
        if (rec.getLowerLimit() == lowerLimit &&
            rec.getUpperLimit() == upperLimit &&
            rec.getStep() == step) {
            return rec;
        }
    }
    return null;
}

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    //кнопка рассчитать
    private void Rasschet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rasschet
        int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Выберите строку!", "Предупреждение", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (clients.size() < EXPECTED_CLIENTS) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Подключено только " + clients.size() + " из " + EXPECTED_CLIENTS + " клиентов!");
        return;
    }

    final double lowerLimit = Double.parseDouble(tableModel.getValueAt(selectedRow, 0).toString());
    final double upperLimit = Double.parseDouble(tableModel.getValueAt(selectedRow, 1).toString());
    final double step = Double.parseDouble(tableModel.getValueAt(selectedRow, 2).toString());

    Rasschet.setEnabled(false);

    final int finalSelectedRow = selectedRow;
    new Thread(() -> {
        double totalResult = 0.0;
        double x = lowerLimit;
        int stepNumber = 0;
        int totalSteps = (int) Math.ceil((upperLimit - lowerLimit) / step);
        
        while (x < upperLimit) {
            double nextX = Math.min(x + step, upperLimit);
            double[] stepResult = {0.0};
            int[] responsesReceived = {0};
            
            String taskId = UUID.randomUUID().toString();
            tasks.put(taskId, new double[]{0.0, 0.0});  
            
            double subInterval = (nextX - x) / 9;
            
            //9 задач для шага
            for (int i = 0; i < 9; i++) {
                int clientId = i + 1;
                DataOutputStream dos = clientsOutputs.get(clientId);
                
                if (dos != null) {
                    double clientLower = x + i * subInterval;
                    double clientUpper = (i == 8) ? nextX : x + (i + 1) * subInterval;
                    
                    try {
                        dos.writeUTF("TASK");
                        dos.writeUTF(taskId);
                        dos.writeDouble(clientLower);
                        dos.writeDouble(clientUpper);
                        dos.writeDouble(subInterval / 9);  
                        dos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
            // Ждем завершения этого шага
            double[] taskData = tasks.get(taskId);
            while (taskData != null && taskData[1] < EXPECTED_CLIENTS) {
                try {
                    Thread.sleep(10);  
                } catch (InterruptedException e) {
                    break;
                }
            }
            
            if (taskData != null) {
                totalResult += taskData[0];
                tasks.remove(taskId);
            }
            
            x = nextX;
            stepNumber++;
        }
        
        final double finalResult = totalResult;
        SwingUtilities.invokeLater(() -> {
            tableModel.setValueAt(String.format("%.6f", finalResult), finalSelectedRow, 3);
            Rasschet.setEnabled(true);
            updateCollection();
        });
        System.out.println("ВСЕ ШАГИ ЗАВЕРШЕНЫ! Итог: " + finalResult);
    }).start();
    }//GEN-LAST:event_Rasschet

    private void jTable1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentHidden
    }//GEN-LAST:event_jTable1ComponentHidden

    private void Vnesti(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Vnesti

    boolean hasFieldData = !jTextField1.getText().trim().isEmpty() ||
                           !jTextField2.getText().trim().isEmpty() ||
                           !jTextField3.getText().trim().isEmpty();

    if (hasFieldData) {
        try {
            RecIntegral rec = new RecIntegral(
                jTextField2.getText(),  // нижний предел
                jTextField1.getText(),  // верхний предел
                jTextField3.getText()   // шаг
            );

            tableModel.addRow(new Object[]{
                rec.getLowerLimit(),
                rec.getUpperLimit(),
                rec.getStep(),
                ""
            });

            dataList.add(rec);

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");

        } catch (ValidData e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Ошибка ввода",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Нет данных для добавления.\n" +
            "Коллекция содержит: " + dataList.size() + " записей",
            "Информация",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    }//GEN-LAST:event_Vnesti

    private void ydalitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ydalitActionPerformed
        int selectedRow = jTable1.getSelectedRow();

    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Пожалуйста, выберите строку для удаления!",
            "Предупреждение",
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Удалить выбранную строку?\n" +
            "(из таблицы и из коллекции)",
            "Подтверждение",
            javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        try {
            double lowerLimit = Double.parseDouble(tableModel.getValueAt(selectedRow, 0).toString());
            double upperLimit = Double.parseDouble(tableModel.getValueAt(selectedRow, 1).toString());
            double step = Double.parseDouble(tableModel.getValueAt(selectedRow, 2).toString());

            RecIntegral toRemove = findExistingRecord(lowerLimit, upperLimit, step);
            if (toRemove != null) {
                dataList.remove(toRemove);
                System.out.println("Запись удалена из коллекции: " + lowerLimit + " | " + upperLimit + " | " + step);
            }
        } catch (Exception e){
            System.out.println("Ошибка при чтении строки для удаления из коллекции: " + e.getMessage());
        }

        // Удаляем только из таблицы
        tableModel.removeRow(selectedRow);
        System.out.println("Строка удалена из таблицы. В таблице осталось: " + tableModel.getRowCount() + " строк");
        System.out.println("Коллекция не изменилась: " + dataList.size() + " записей");
        }
    }//GEN-LAST:event_ydalitActionPerformed

    private void Zapolnit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zapolnit
        if (dataList.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Коллекция пуста! Нечего восстанавливать.\n\n",
            "Информация",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Если в таблице уже есть данные
    if (tableModel.getRowCount() > 0) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "В таблице уже есть " + tableModel.getRowCount() + " записей!\n\n" +
            "Что вы хотите сделать?\n" +
            "'Да' - заменить текущие данные данными из коллекции\n" +
            "Отмена",
            "Восстановление данных",
            javax.swing.JOptionPane.YES_NO_CANCEL_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // Заменяем данные
            tableModel.setRowCount(0);
            updateTableFromList();

        } else if (confirm == javax.swing.JOptionPane.NO_OPTION){

            // Добавляем данные
            for (RecIntegral rec : dataList) {
                String resultStr = rec.hasResult() ?
                    String.format("%.6f", rec.getResult()).replace('.',',') : "";
                tableModel.addRow(new Object[]{
                    rec.getLowerLimit(),
                    rec.getUpperLimit(),
                    rec.getStep(),
                    resultStr
                });
            }
        }
    }else{
            updateTableFromList();

            }
        javax.swing.JOptionPane.showMessageDialog(this,
            "Таблица восстановлена!\n" +
            "Записей загружено: " + dataList.size(),
            "Успех",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_Zapolnit

    private void Ochistit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ochistit
        if (tableModel.getRowCount() > 0) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Очистить таблицу?\n\n" +
            "Данные останутся в коллекции.\n" +
            "Вы сможете восстановить их кнопкой 'Заполнить'.\n\n" +
            "Продолжить?",
            "Подтверждение очистки",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // Очищаем только таблицу
            tableModel.setRowCount(0);

            javax.swing.JOptionPane.showMessageDialog(this,
                "Таблица очищена!\n\n" +
                "Коллекция содержит: " + dataList.size() + " записей\n\n",
                "Успех",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Таблица уже пуста!\n" +
            "Коллекция содержит: " + dataList.size() + " записей",
            "Информация",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Ochistit

    private void Zagruzka_txt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zagruzka_txt
       javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
    if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
        java.io.FileReader myfile = null;
        try {
            myfile = new java.io.FileReader(fc.getSelectedFile().getAbsolutePath());
            StringBuilder content = new StringBuilder();
            int c;
            while ((c = myfile.read()) != -1) {
                content.append((char) c);
            }

            String[] lines = content.toString().split("\n");
            int addedCount = 0;
            int skippedCount = 0;

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    try {
                        double lower = Double.parseDouble(parts[0]);
                        double upper = Double.parseDouble(parts[1]);
                        double step = Double.parseDouble(parts[2]);

                        if (findExistingRecord(lower, upper, step) != null) {
                            skippedCount++;
                            continue;
                        }

                        RecIntegral rec;
                        if (parts.length >= 4 && !parts[3].trim().isEmpty()) {
                            double result = Double.parseDouble(parts[3]);
                            rec = new RecIntegral(lower, upper, step, result);
                        } else {
                            rec = new RecIntegral(lower, upper, step);
                        }

                        // Добавляем в коллекцию и таблицу
                        dataList.add(rec);
                        String resultStr = rec.hasResult() ?
                            String.format("%.6f", rec.getResult()).replace('.', '.') : "";
                        tableModel.addRow(new Object[]{
                            rec.getLowerLimit(),
                            rec.getUpperLimit(),
                            rec.getStep(),
                            resultStr
                        });
                        addedCount++;

                    } catch (NumberFormatException e) {
                        skippedCount++;
                        System.out.println("Пропущена строка: " + line + " - " + e.getMessage());
                    }
                }
            }

            String message = "Загружено новых записей: " + addedCount;
            if (skippedCount > 0) {
                message += "\nПропущено (дубликаты): " + skippedCount;
            }
            javax.swing.JOptionPane.showMessageDialog(this, message);

        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Ошибка чтения файла: " + ex.getMessage());
        } finally {
            try {
                if (myfile != null) myfile.close();
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    }//GEN-LAST:event_Zagruzka_txt

    //@SuppressWarnings("unchecked")
    private void Zagruzka_bin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Zagruzka_bin

    javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
    javax.swing.filechooser.FileNameExtensionFilter filter =
        new javax.swing.filechooser.FileNameExtensionFilter("Бинарные файлы (*.ser)", "ser");
    fc.setFileFilter(filter);

    if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(fc.getSelectedFile().getAbsolutePath())));
            ArrayList<RecIntegral> loadedList = (ArrayList<RecIntegral>) in.readObject();

            int addedCount = 0;
            int skippedCount = 0;

            for (RecIntegral rec : loadedList) {
                if (findExistingRecord(rec.getLowerLimit(), rec.getUpperLimit(), rec.getStep()) != null) {
                    skippedCount++;
                    continue;
                }

                dataList.add(rec);
                String resultStr = rec.hasResult() ?
                    String.format("%.6f", rec.getResult()).replace('.', '.') : "";
                tableModel.addRow(new Object[]{
                    rec.getLowerLimit(),
                    rec.getUpperLimit(),
                    rec.getStep(),
                    resultStr
                });
                addedCount++;
            }

            String message = "Загружено новых записей: " + addedCount;
            if (skippedCount > 0) {
                message += "\nПропущено дубликатов: " + skippedCount;
            }
            javax.swing.JOptionPane.showMessageDialog(this, message);

        } catch (java.io.IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
        } finally {
            try {
                if (in != null) in.close();
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    }//GEN-LAST:event_Zagruzka_bin

    private void Save_txt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_txt
        if (dataList.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Нет данных для сохранения!");
            return;
        }

        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();

    // Устанавливаем фильтр только для .txt файлов
        javax.swing.filechooser.FileNameExtensionFilter filter =
            new javax.swing.filechooser.FileNameExtensionFilter("Текстовые файлы (*.txt)", "txt");
        fc.setFileFilter(filter);

        if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.FileWriter myfile = null;
            try {
            // Получаем файл
                java.io.File file = fc.getSelectedFile();
                String filePath = file.getAbsolutePath();

            // Добавляем .txt если нет расширения
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    filePath = filePath + ".txt";
                }

                myfile = new java.io.FileWriter(filePath);
                for (RecIntegral rec : dataList) {
                     String line = rec.getLowerLimit() + ";" + rec.getUpperLimit() + ";" + rec.getStep() + ";" + (rec.hasResult() ? rec.getResult() : "") + "\n";
                    myfile.write(line);
                }

                javax.swing.JOptionPane.showMessageDialog(this, "Сохранено в: " + filePath);

            } catch (java.io.IOException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            } finally {
                try {
                    if (myfile != null) myfile.close();
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_Save_txt

    private void Save_bin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_bin
        if (dataList.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Нет данных для сохранения!");
            return;
        }

        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();

        javax.swing.filechooser.FileNameExtensionFilter filter =
            new javax.swing.filechooser.FileNameExtensionFilter("Бинарные файлы (*.ser)", "ser");
        fc.setFileFilter(filter);

        if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            ObjectOutputStream out = null;
            try {
                java.io.File file = fc.getSelectedFile();
                String filePath = file.getAbsolutePath();

                if (!filePath.toLowerCase().endsWith(".ser")) {
                    filePath = filePath + ".ser";
                }

                out = new ObjectOutputStream(new BufferedOutputStream(
                    new FileOutputStream(filePath)));
                out.writeObject(dataList);

                javax.swing.JOptionPane.showMessageDialog(this, "Сохранено в: " + filePath);

            } catch (java.io.IOException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            } finally {
                try {
                    if (out != null) out.close();
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_Save_bin

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new server().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ochistit;
    private javax.swing.JButton Rasschet;
    private javax.swing.JButton Vnesti;
    private javax.swing.JButton Vnesti1;
    private javax.swing.JButton Vnesti2;
    private javax.swing.JButton Vnesti3;
    private javax.swing.JButton Vnesti4;
    private javax.swing.JButton Zapolnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton ydalit;
    // End of variables declaration//GEN-END:variables
}
