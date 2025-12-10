package org.mulyichat.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatGui {

    public static void printMessage(JTextField textField, GridBagConstraints gbcPanelMain, JPanel panelMain){
        if (!textField.getText().isEmpty()){
            // panel-обертка для сообщений
            JPanel panelMessage = new JPanel(new BorderLayout());
            panelMessage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
            panelMessage.setBackground(Color.PINK);

            // ---

            JLabel label = new JLabel(textField.getText());

            label.setHorizontalAlignment(SwingConstants.RIGHT); // выравнивание справа
            panelMessage.add(label, BorderLayout.EAST);

            gbcPanelMain.gridy = panelMain.getComponentCount();
            panelMain.add(panelMessage, gbcPanelMain);
            panelMain.revalidate();
            panelMain.repaint();

            textField.setText("");
        }
    }


    public static void main(String[] args) {
        // настраиваем окно
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chat");
        frame.setSize(500, 400);
        frame.setLayout(new GridBagLayout()); // типо display: grid на манер css
        // Layout - объект, который занимается размещением объектов на окне

        // ---SIDEBAR---
        JPanel panelSideBar = new JPanel();
        panelSideBar.setBackground(Color.BLUE);

        GridBagConstraints gbcSidebar = new GridBagConstraints();
        gbcSidebar.fill = GridBagConstraints.BOTH; // разрешаем размещать и по горизонтали и по вертикали

        gbcSidebar.gridx = 0;
        gbcSidebar.weighty = 1.0;
        gbcSidebar.weightx = 0.2;
        gbcSidebar.gridheight = GridBagConstraints.REMAINDER;
        panelSideBar.setBackground(Color.BLUE);
        frame.add(panelSideBar, gbcSidebar);

        // ---HEADER--- настройка и его размещение
        JPanel panelHeader = new JPanel();
        panelHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.fill = GridBagConstraints.BOTH; // разрешаем размещать и по горизонтали и по вертикали

        gbcHeader.weighty = 0.2;
        gbcHeader.weightx = 0.8;
        gbcHeader.gridy = 0;
        gbcHeader.gridx = 1;
        // высвечиваем "Chat"
        JLabel lblHeader = new JLabel("Chat");
        panelHeader.add(lblHeader);
        gbcHeader.anchor = GridBagConstraints.EAST;
        frame.add(panelHeader, gbcHeader);

        // ---MAIN--- занимаемся с main
        JPanel panelMain = new JPanel();

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH; // разрешаем размещать и по горизонтали и по вертикали

        gbcMain.weighty = 0.5;
        gbcMain.gridy = 1;
        gbcMain.gridx = 1;
        gbcMain.weightx = 0.8;
        panelMain.setBackground(Color.WHITE);
        gbcMain.anchor = GridBagConstraints.NORTH;

        // ---Располагаю сообщения в main справа---
        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbcPanelMain = new GridBagConstraints();
        gbcPanelMain.anchor = GridBagConstraints.NORTHEAST;

        gbcPanelMain.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelMain.gridx = 0;
        gbcPanelMain.weightx = 1.0;

        frame.add(panelMain, gbcMain);

        // ---FOOTER--- занимаемся с footer
        JPanel panelFooter = new JPanel();
        panelFooter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagConstraints gbcFooter = new GridBagConstraints();
        gbcFooter.fill = GridBagConstraints.BOTH;

        gbcFooter.weighty = 0.3;
        gbcFooter.weightx = 0.8;
        gbcFooter.gridy = 2;
        gbcFooter.gridx = 1;
        gbcFooter.anchor = GridBagConstraints.EAST;

        JTextField textField = new JTextField(40);
        panelFooter.add(textField);

        JButton button = new JButton("Send");
        panelFooter.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!textField.getText().isEmpty()){
                    printMessage(textField, gbcPanelMain, panelMain);
                }
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    printMessage(textField, gbcPanelMain, panelMain);
                }
            }
        });


        frame.add(panelFooter, gbcFooter);

        // делаем видимым окно
        frame.setVisible(true);
    }
}
