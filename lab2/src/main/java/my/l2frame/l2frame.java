/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.l2frame;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
//
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Марина
 */
public class l2frame extends javax.swing.JFrame { //наследование
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(l2frame.class.getName());
    private DefaultTableModel tableModel;
    private ArrayList<RecIntegral> dataList;
    /**
     * Creates new form l1frame
     */
    public l2frame() {
        initComponents();
        tableModel = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        dataList = new ArrayList<>();
        //
        tableModel.addTableModelListener(e -> {
        if (e.getType() == TableModelEvent.UPDATE && e.getColumn() <= 2 && e.getFirstRow() >= 0) {
            tableModel.setValueAt("", e.getFirstRow(), 3);
            updateCollection();
            }
        });
    }
   
    @SuppressWarnings("unchecked")
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
        Vnesti.addActionListener(this::VnestiActionPerformed);

        Rasschet.setText("Рассчитать");
        Rasschet.addActionListener(this::RasschetActionPerformed);

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
        Zapolnit.addActionListener(this::ZapolnitActionPerformed);

        Ochistit.setText("Очистить");
        Ochistit.addActionListener(this::OchistitActionPerformed);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Ochistit)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Zapolnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ydalit)
                        .addGap(30, 30, 30))))
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
                    .addComponent(Vnesti))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rasschet))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ydalit)
                    .addComponent(Zapolnit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ochistit)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    dataList.clear(); // Очищаем коллекцию
    
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        try {
            // Получаем значения из таблицы
            double lowerLimit = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
            double upperLimit = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            double step = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
            
            // Получаем результат (может быть пустым)
            Object resultObj = tableModel.getValueAt(i, 3);
            boolean hasResult = resultObj != null && !resultObj.toString().trim().isEmpty();
            
            RecIntegral rec;
            if (hasResult) {
                // Если есть результат - сохраняем его
                double result = Double.parseDouble(resultObj.toString().trim().replace(',', '.'));
                rec = new RecIntegral(lowerLimit, upperLimit, step, result);
            } else {
                // Если нет результата
                rec = new RecIntegral(lowerLimit, upperLimit, step);
            }
            dataList.add(rec);
            
        } catch (Exception e) {
            // Пропускаем некорректные строки
            System.out.println("Ошибка в строке " + i + ": " + e.getMessage());
        }
    }
    
    System.out.println("Сохранено в коллекцию: " + dataList.size() + " записей");
    }
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    //кнопка рассчитать
    private void RasschetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RasschetActionPerformed
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Выберите строку!", 
            "Предупреждение", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        // Читаем значения из таблицы
        double lowerLimit = Double.parseDouble(
            tableModel.getValueAt(selectedRow, 0).toString());
        double upperLimit = Double.parseDouble(
            tableModel.getValueAt(selectedRow, 1).toString());
        double step = Double.parseDouble(
            tableModel.getValueAt(selectedRow, 2).toString());
        
        ValidData.validate(lowerLimit, upperLimit, step);
        
//        if (lowerLimit >= upperLimit) {
//            javax.swing.JOptionPane.showMessageDialog(this, 
//                "Нижний предел должен быть меньше верхнего!\n" +
//                "Текущие значения: " + lowerLimit + " и " + upperLimit,
//                "Ошибка", 
//                javax.swing.JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        
//        if (step <= 0) {
//            javax.swing.JOptionPane.showMessageDialog(this, 
//                "Шаг должен быть положительным!\n" +
//                "Текущий шаг: " + step,
//                "Ошибка", 
//                javax.swing.JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        
        // Вычисляем интеграл
        RecIntegral rec = new RecIntegral(lowerLimit, upperLimit, step);
        double result = rec.calculateIntegral();
        
        // Сохраняем результат
        tableModel.setValueAt(String.format("%.6f", result), selectedRow, 3);
        
        //tableModel.fireTableDataChanged();
         if (jTable1.getCellEditor() != null) {
            jTable1.getCellEditor().stopCellEditing();
        }
        // Обновляем коллекцию
        updateCollection();
               
    } catch (ValidData e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Ошибка!\n" +
            "Пожалуйста, введите корректное число.", 
            "Ошибка", 
            javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_RasschetActionPerformed

    private void jTable1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1ComponentHidden

    private void VnestiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VnestiActionPerformed
    // Проверяем, есть ли данные в полях ввода
    boolean hasFieldData = !jTextField1.getText().trim().isEmpty() ||
                           !jTextField2.getText().trim().isEmpty() ||
                           !jTextField3.getText().trim().isEmpty();
    
    if (hasFieldData) {
        //добавление новой строки
        try {
            double[] values = ValidData.validateAndParse(
                jTextField2.getText(),  // нижний предел
                jTextField1.getText(),  // верхний предел
                jTextField3.getText()   // шаг
            );
            
            double lowerLimit = values[0];
            double upperLimit = values[1];
            double step = values[2];
            
//            double lowerLimit = Double.parseDouble(jTextField2.getText().trim());
//            double upperLimit = Double.parseDouble(jTextField1.getText().trim());
//            double step = Double.parseDouble(jTextField3.getText().trim());
           
//            if (lowerLimit >= upperLimit) {
//                javax.swing.JOptionPane.showMessageDialog(this, 
//                    "Нижний предел должен быть меньше верхнего!", 
//                    "Ошибка", 
//                    javax.swing.JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            
//            if (step <= 0) {
//                javax.swing.JOptionPane.showMessageDialog(this, 
//                    "Шаг должен быть положительным числом!", 
//                    "Ошибка", 
//                    javax.swing.JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            
            // Добавляем новую строку (слушатель сам обновит коллекцию)
            tableModel.addRow(new Object[]{lowerLimit, upperLimit, step, ""});
            
            updateCollection();
            
             System.out.println("Коллекция теперь содержит: " + dataList.size() + " записей");
            // Очищаем поля
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            
        } catch (ValidData e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Ошибка! Введите корректные числовые значения!", 
                "Ошибка ввода", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } else {
        //просто синхронизируем коллекцию
        updateCollection();
        javax.swing.JOptionPane.showMessageDialog(this,
            "Коллекция синхронизирована!\nЗаписей: " + dataList.size(),
            "Информация",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_VnestiActionPerformed

    private void ydalitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ydalitActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Пожалуйста, выберите строку для удаления!", 
            "Предупреждение", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Удалить выбранную строку?",
            "Подтверждение",
            javax.swing.JOptionPane.YES_NO_OPTION);
        
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
    
                tableModel.removeRow(selectedRow);//удаление строки
            }
    }//GEN-LAST:event_ydalitActionPerformed

    private void ZapolnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZapolnitActionPerformed
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
            "'Нет' - добавить данные из коллекции в конец таблицы\n" +
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
    }//GEN-LAST:event_ZapolnitActionPerformed

    private void OchistitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OchistitActionPerformed
        if (tableModel.getRowCount() > 0) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Очистить таблицу?\n\n" +
            "Данные останутся в коллекции.\n" +
            "Вы сможете восстановить их кнопкой 'Заполнить'.\n\n" +
            "Продолжить?",
            "Подтверждение очистки",
            javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            // Очищаем таблицу
            tableModel.setRowCount(0);
            
            javax.swing.JOptionPane.showMessageDialog(this,
                "Таблица очищена!\n\n" +
                "Нажмите 'Заполнить' для восстановления данных.",
                "Успех",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Таблица уже пуста!",
            "Информация",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_OchistitActionPerformed

    
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
        java.awt.EventQueue.invokeLater(() -> new l2frame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ochistit;
    private javax.swing.JButton Rasschet;
    private javax.swing.JButton Vnesti;
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
