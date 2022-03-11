package org.example.presentation.view;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.presentation.controller.RegularUserController;
import org.example.presentation.controller.TravellingAgencyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInView extends JPanel {
    private ArrayList<JButton> buttons;
    private ArrayList<JTextField> fields;
    private int nrWrongLabels;
    private ArrayList<JLabel> wrongLabels;

    /**
     * Creates a new instance of ClientsViewPanel.
     * @param height height of the panel
     * @param width width of the panel
     */
    public LogInView(int height, int width, RegularUserController regularUserController, TravellingAgencyController travellingAgencyController, MainFrame mainFrame)
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
            button.addActionListener(new ButtonsListenerLogIn(this,regularUserController, travellingAgencyController, mainFrame));
            buttons.add(button);
            this.add(button);
        }

        buttons.get(0).setText("LOG IN");
        buttons.get(0).setBounds(850,500, 150, 50);
        buttons.get(1).setText("REGISTER");
        buttons.get(1).setBounds(650, 500, 150, 50);


        int nrDataLabels = 2;
        JLabel[] dataLabels = new JLabel[nrDataLabels];
        for(int i = 0; i< nrDataLabels; i++)
        {
            dataLabels[i]=new JLabel();
            dataLabels[i].setFont(new Font("TimesRoman",20,20));
            dataLabels[i].setForeground(Color.WHITE);
            this.add(dataLabels[i]);
            dataLabels[i].setVisible(true);
        }

        dataLabels[0].setText("Email:");
        dataLabels[0].setBounds(470,330,150,30);
        dataLabels[1].setText("Password:");
        dataLabels[1].setBounds(470,410,150,30);

        fields = new ArrayList<>();

        JTextField field=new JTextField();
        field.setFont(new Font("TimesRoman",20,20));
        field.setForeground(Color.WHITE);
        field.setOpaque(false);
        this.add(field);
        fields.add(field);

        JPasswordField field2=new JPasswordField();
        field2.setFont(new Font("TimesRoman",20,20));
        field2.setForeground(Color.WHITE);
        field2.setOpaque(false);
        this.add(field2);
        fields.add(field2);


        fields.get(0).setBounds(620,325,440,45);
        fields.get(1).setBounds(620,405,440,45);

        nrWrongLabels=1;
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

        wrongLabels.get(0).setText("*wrong email or password");
        wrongLabels.get(0).setBounds(1120,330,440,30);
    }

    public void setWrongLabelVisible(boolean visible, int nrOfTheLabel, boolean all)
    {
        if(all)
            for(int i=0;i<nrWrongLabels;i++)
                wrongLabels.get(i).setVisible(visible);
        else
            wrongLabels.get(nrOfTheLabel).setVisible(visible);
    }

    class ButtonsListenerLogIn implements ActionListener {
        private LogInView view;
        private RegularUserController regularUserController;
        private TravellingAgencyController travellingAgencyController;
        private MainFrame mainFrame;
        public ButtonsListenerLogIn(LogInView view, RegularUserController regularUserController, TravellingAgencyController travellingAgencyController, MainFrame mainFrame){
            super();
            this.view=view;
            this.regularUserController=regularUserController;
            this.travellingAgencyController=travellingAgencyController;
            this.mainFrame=mainFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource(); int row; view.setWrongLabelVisible(false, 0,true); int result;
            if(event==view.buttons.get(0)) //log in
            {
                String email = view.fields.get(0).getText();
                String password = view.fields.get(1).getText();

                if(email.contains("@agency.com")){
                    TravellingAgency agency = travellingAgencyController.findAgencyByEmailAndPassword(email, password);
                    if(agency==null)
                        view.setWrongLabelVisible(true,0,false);
                    else mainFrame.setPanel(2);
                }
                else{
                    RegularUser user = regularUserController.findUserByEmailAndPassword(email, password);
                    if(user==null)
                        view.setWrongLabelVisible(true,0,false);
                    else mainFrame.setPanel(3);
                }
            }
            else
            if(event==view.buttons.get(1)) //register
            {
                mainFrame.setPanel(1);
            }
        }
    }

}
