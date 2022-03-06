package org.example.presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterView extends JPanel {
    private int nrButtons;
    /** the list of buttons in the view. */
    public ArrayList<JButton> buttons;
    private int nrFields;
    /** the list of text fields in the view. */
    public ArrayList<JTextField> fields;
    private int nrWrongLabels;
    /** the list of labels which will be used for showing error messages */
    public ArrayList<JLabel> wrongLabels;

    /**
     * Creates a new instance of ClientsViewPanel.
     * @param height height of the panel
     * @param width width of the panel
     */
    public RegisterView(int height, int width)
    {
        super();
        this.setBounds(0,0, height, width);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        nrButtons=1;
        buttons=new ArrayList<>();

        for(int i=0;i<nrButtons;i++)
        {
            JButton button=new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("TimesRoman",20,20));
            buttons.add(button);
            this.add(button);
        }

        buttons.get(0).setText("REGISTER");
        buttons.get(0).setBounds(850,550, 150, 50);


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

        nrFields=4;
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

    /**
     * attempts to add an action listener to one of the button of the panel.
     * @param listener reference to the action listener.
     * @param nrOfTheButton the index of the button in the buttons array.
     */
    public void addButtonListener(ActionListener listener, int nrOfTheButton)
    {
        if(nrOfTheButton<nrButtons)
            buttons.get(nrOfTheButton).addActionListener(listener);
    }

    /**
     * @return the number of buttons the panel contains.
     */
    public int getNrButtons() {
        return nrButtons;
    }

    /**
     * receives a list of objects and creates the header of the table by accessing the fields of the elements of data through reflection, and then adds entries in the table.
     * @param data list of objects
     */

    /**
     * attempts to make a label in the panel visible or not.
     * @param visible true to be visible, false if not.
     * @param nrOfTheLabel the index of the label in the wrongLabels list.
     * @param all true if all the labels to be set visible/unvisible, false if only one of them.
     */
    public void setWrongLabelVisible(boolean visible, int nrOfTheLabel, boolean all)
    {
        if(all)
            for(int i=0;i<nrWrongLabels;i++)
                wrongLabels.get(i).setVisible(visible);
        else
            wrongLabels.get(nrOfTheLabel).setVisible(visible);
    }


}
