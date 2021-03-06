package org.example.presentation.view;

import org.example.presentation.controller.RegularUserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterView extends JPanel {
    private ArrayList<JButton> buttons;
    private ArrayList<JTextField> fields;
    private int nrWrongLabels;
    /** the list of labels which will be used for showing error messages */
    private ArrayList<JLabel> wrongLabels;

    /**
     * Creates a new instance of ClientsViewPanel.
     * @param height height of the panel
     * @param width width of the panel
     */
    public RegisterView(int height, int width, RegularUserController regularUserController, MainFrame mainFrame)
    {
        super();
        this.setBounds(0,0, height, width);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        buttons=new ArrayList<>();

        for(int i=0;i<2;i++)
        {
            JButton button=new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("TimesRoman",20,20));
            button.addActionListener(new ButtonsListenerRegister(this,regularUserController, mainFrame));
            buttons.add(button);
            this.add(button);
        }

        buttons.get(0).setText("LOG IN");
        buttons.get(0).setBounds(690,550, 150, 50);
        buttons.get(1).setText("SIGN UP");
        buttons.get(1).setBounds(850, 550, 150, 50);


        int nrDataLabels = 4;
        JLabel[] dataLabels = new JLabel[nrDataLabels];
        for(int i = 0; i< nrDataLabels; i++)
        {
            dataLabels[i]=new JLabel();
            dataLabels[i].setFont(new Font("TimesRoman",20,20));
            dataLabels[i].setForeground(Color.WHITE);
            this.add(dataLabels[i]);
            dataLabels[i].setVisible(true);
        }


        dataLabels[0].setText("Name:");
        dataLabels[0].setBounds(400,250,180,30);
        dataLabels[1].setText("Email:");
        dataLabels[1].setBounds(400,330,180,30);
        dataLabels[2].setText("Password:");
        dataLabels[2].setBounds(400,410,180,30);
        dataLabels[3].setText("Repeat password:");
        dataLabels[3].setBounds(400,490,180,30);

        fields = new ArrayList<>();

        for(int i=0;i<2;i++)
        {
            JTextField field=new JTextField();
            field.setFont(new Font("TimesRoman",20,20));
            field.setForeground(Color.WHITE);
            field.setOpaque(false);
            this.add(field);
            fields.add(field);
        }

        for(int i=2;i<4;i++)
        {
            JPasswordField field=new JPasswordField();
            field.setFont(new Font("TimesRoman",20,20));
            field.setForeground(Color.WHITE);
            field.setOpaque(false);
            this.add(field);
            fields.add(field);
        }

        fields.get(0).setBounds(620,245,440,45);
        fields.get(1).setBounds(620,325,440,45);
        fields.get(2).setBounds(620,405,440,45);
        fields.get(3).setBounds(620,485,440,45);

        nrWrongLabels=5;
        wrongLabels=new ArrayList<>();

        for(int i=0;i<nrWrongLabels;i++)
        {
            JLabel wrongLabel;
            wrongLabel=new JLabel();
            wrongLabel.setFont(new Font("TimesRoman",20,20));
            wrongLabel.setForeground(Color.RED);
            this.add(wrongLabel);
            wrongLabels.add(wrongLabel);
            wrongLabel.setVisible(false);
        }

        wrongLabels.get(0).setText("*please insert a valid name");
        wrongLabels.get(0).setBounds(1120,330,440,30);
        wrongLabels.get(1).setText("*please insert a valid password");
        wrongLabels.get(1).setBounds(1120,330,440,30);
        wrongLabels.get(2).setText("*passwords don't match");
        wrongLabels.get(2).setBounds(1120,330,440,30);
        wrongLabels.get(3).setText("*please insert a valid email");
        wrongLabels.get(3).setBounds(1120,330,440,30);
        wrongLabels.get(4).setText("*email already exists");
        wrongLabels.get(4).setBounds(1120,330,440,30);
    }

    public void setWrongLabelVisible(boolean visible, int nrOfTheLabel, boolean all)
    {
        if(all)
            for(int i=0;i<nrWrongLabels;i++)
                wrongLabels.get(i).setVisible(visible);
        else
            wrongLabels.get(nrOfTheLabel).setVisible(visible);
    }

    class ButtonsListenerRegister implements ActionListener {
        private RegisterView view;
        private RegularUserController controller;
        private MainFrame mainFrame;
        public ButtonsListenerRegister(RegisterView view, RegularUserController controller, MainFrame mainFrame){
            super();
            this.view=view;
            this.controller=controller;
            this.mainFrame=mainFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource(); int row; view.setWrongLabelVisible(false, 0,true); int result;
            if(event==view.buttons.get(0)) //log in
            {
                mainFrame.setPanel(0);
            }
            else
            if(event==view.buttons.get(1)) //sign up
            {
                String name = view.fields.get(0).getText();
                String email = view.fields.get(1).getText();
                String password = view.fields.get(2).getText();
                String repeatedPassword=view.fields.get(3).getText();

                try{
                    controller.addRegularUser(name, email, password, repeatedPassword);
                }
                catch (Exception ex){
                    if(ex.getMessage().equals("name"))
                        view.setWrongLabelVisible(true,0,false);
                    if(ex.getMessage().equals("password"))
                        view.setWrongLabelVisible(true,1,false);
                    if(ex.getMessage().equals("repeatedPassword"))
                        view.setWrongLabelVisible(true,2,false);
                    if(ex.getMessage().equals("email"))
                        view.setWrongLabelVisible(true,3,false);
                    if(ex.getMessage().equals("email exists"))
                        view.setWrongLabelVisible(true,4,false);
                }
            }
        }
    }
}
