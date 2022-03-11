package org.example.presentation.view;

        import org.example.business.model.VacationPackage;
        import org.example.presentation.controller.RegularUserController;

        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

public class RegularUserView extends JPanel {
    private ArrayList<JButton> buttons;
    private ArrayList<JTextField> fields;
    private int nrWrongLabels;
    private ArrayList<JLabel> wrongLabels;

    private JTable availablePackagesTable;
    private JTable myBookedPackagesTable;
    private DefaultTableModel tableModelAvailablePackages;
    private DefaultTableModel tableModelBookedPackages;

    /**
     * Creates a new instance of ClientsViewPanel.
     * @param height height of the panel
     * @param width width of the panel
     */
    public RegularUserView(int height, int width, RegularUserController regularUserController)
    {
        super();
        this.setBounds(0,0, height, width);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        buttons=new ArrayList<>();

        for(int i=0;i<4;i++)
        {
            JButton button=new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("TimesRoman",20,20));
            button.addActionListener(new ButtonsListenerRegularUser(this,regularUserController));
            buttons.add(button);
            this.add(button);
        }

        buttons.get(0).setText("LOG OUT");
        buttons.get(0).setBounds(1400,30, 150, 50);
        buttons.get(1).setText("FILTER");
        buttons.get(1).setBounds(550,645, 150, 50);
        buttons.get(2).setText("BOOK");
        buttons.get(2).setBounds(850, 645, 150, 50);
        buttons.get(3).setText("VIEW ALL");
        buttons.get(3).setBounds(550,845, 150, 50);


        int nrDataLabels = 6;
        JLabel[] dataLabels = new JLabel[nrDataLabels];
        for(int i = 0; i< nrDataLabels; i++)
        {
            dataLabels[i]=new JLabel();
            dataLabels[i].setFont(new Font("TimesRoman",20,20));
            dataLabels[i].setForeground(Color.WHITE);
            this.add(dataLabels[i]);
            dataLabels[i].setVisible(true);
        }

        dataLabels[0].setText("Available packages:");
        dataLabels[0].setBounds(100,60,200,30);
        dataLabels[1].setText("My booked packages:");
        dataLabels[1].setBounds(900,60,200,30);
        dataLabels[2].setText("Destination:");
        dataLabels[2].setBounds(60,650,150,30);
        dataLabels[3].setText("Price:");
        dataLabels[3].setBounds(60,700,150,30);
        dataLabels[4].setText("Start date:");
        dataLabels[4].setBounds(60,750,150,30);
        dataLabels[5].setText("End date:");
        dataLabels[5].setBounds(60,800,150,30);

        fields = new ArrayList<>();

        for(int i=0;i<4;i++)
        {
            JTextField field=new JTextField();
            field.setFont(new Font("TimesRoman",20,20));
            field.setForeground(Color.WHITE);
            field.setOpaque(false);
            this.add(field);
            fields.add(field);
        }


        fields.get(0).setBounds(210,645,300,40);
        fields.get(1).setBounds(210,695,300,40);
        fields.get(2).setBounds(210,745,300,40);
        fields.get(3).setBounds(210,795,300,40);

        nrWrongLabels=6;
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

        wrongLabels.get(0).setText("*please select a package");
        wrongLabels.get(0).setBounds(850,750,440,30);
        wrongLabels.get(1).setText("*vacation package already booked");
        wrongLabels.get(1).setBounds(850,750,440,30);
        wrongLabels.get(2).setText("*please fill in the filters");
        wrongLabels.get(2).setBounds(850,750,440,30);
        wrongLabels.get(3).setText("*invalid price");
        wrongLabels.get(3).setBounds(850,750,440,30);
        wrongLabels.get(4).setText("*invalid start date");
        wrongLabels.get(4).setBounds(850,750,440,30);
        wrongLabels.get(5).setText("*invalid end date");
        wrongLabels.get(5).setBounds(850,750,440,30);

        Border border = BorderFactory.createEmptyBorder();

        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.DARK_GRAY);
        tablePanel.setBounds(50,100,750, 500);
        tablePanel.setBorder(border);

        tableModelAvailablePackages= new DefaultTableModel();
        tableModelAvailablePackages.addColumn("id");
        tableModelAvailablePackages.addColumn("name");
        tableModelAvailablePackages.addColumn("start date");
        tableModelAvailablePackages.addColumn("end date");
        tableModelAvailablePackages.addColumn("price");
        tableModelAvailablePackages.addColumn("details");
        tableModelAvailablePackages.addColumn("destination");
        tableModelAvailablePackages.addColumn("destination_id");

        availablePackagesTable=new JTable();
        availablePackagesTable.setModel(tableModelAvailablePackages);
        availablePackagesTable.setBackground(Color.DARK_GRAY);
        availablePackagesTable.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        availablePackagesTable.setForeground(Color.WHITE);
        availablePackagesTable.setFont(new Font("TimesRoman",20,20));
        availablePackagesTable.setRowHeight(40);
        availablePackagesTable.getTableHeader().setFont(new Font("TimesRoman",20,20));
        availablePackagesTable.getTableHeader().setBackground(Color.DARK_GRAY);
        availablePackagesTable.getTableHeader().setForeground(Color.WHITE);
        availablePackagesTable.setPreferredScrollableViewportSize(new Dimension(750,500));
        availablePackagesTable.setFillsViewportHeight(true);

        availablePackagesTable.getColumnModel().getColumn(0).setWidth(0);
        availablePackagesTable.getColumnModel().getColumn(0).setMinWidth(0);
        availablePackagesTable.getColumnModel().getColumn(0).setMaxWidth(0);
        availablePackagesTable.getColumnModel().getColumn(7).setWidth(0);
        availablePackagesTable.getColumnModel().getColumn(7).setMinWidth(0);
        availablePackagesTable.getColumnModel().getColumn(7).setMaxWidth(0);

        JScrollPane scrollPane = new JScrollPane(availablePackagesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(720,490));
        scrollPane.setBorder(border);
        tablePanel.add(scrollPane);
        this.add(tablePanel);

        JPanel tablePanel2 = new JPanel();
        tablePanel2.setBackground(Color.DARK_GRAY);
        tablePanel2.setBounds(800,100,750, 500);
        tablePanel2.setBorder(border);

        tableModelBookedPackages= new DefaultTableModel();
        tableModelBookedPackages.addColumn("id");
        tableModelBookedPackages.addColumn("name");
        tableModelBookedPackages.addColumn("start date");
        tableModelBookedPackages.addColumn("end date");
        tableModelBookedPackages.addColumn("price");
        tableModelBookedPackages.addColumn("details");
        tableModelBookedPackages.addColumn("destination");
        tableModelBookedPackages.addColumn("destination_id");

        myBookedPackagesTable=new JTable();
        myBookedPackagesTable.setModel(tableModelBookedPackages);
        myBookedPackagesTable.setBackground(Color.DARK_GRAY);
        myBookedPackagesTable.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        myBookedPackagesTable.setForeground(Color.WHITE);
        myBookedPackagesTable.setFont(new Font("TimesRoman",20,20));
        myBookedPackagesTable.setRowHeight(40);
        myBookedPackagesTable.getTableHeader().setFont(new Font("TimesRoman",20,20));
        myBookedPackagesTable.getTableHeader().setBackground(Color.DARK_GRAY);
        myBookedPackagesTable.getTableHeader().setForeground(Color.WHITE);
        myBookedPackagesTable.setPreferredScrollableViewportSize(new Dimension(750,500));
        myBookedPackagesTable.setFillsViewportHeight(true);

        myBookedPackagesTable.getColumnModel().getColumn(0).setWidth(0);
        myBookedPackagesTable.getColumnModel().getColumn(0).setMinWidth(0);
        myBookedPackagesTable.getColumnModel().getColumn(0).setMaxWidth(0);
        myBookedPackagesTable.getColumnModel().getColumn(7).setWidth(0);
        myBookedPackagesTable.getColumnModel().getColumn(7).setMinWidth(0);
        myBookedPackagesTable.getColumnModel().getColumn(7).setMaxWidth(0);

        JScrollPane scrollPane2 = new JScrollPane(myBookedPackagesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setPreferredSize(new Dimension(720,490));
        scrollPane2.setBorder(border);
        tablePanel2.add(scrollPane2);
        this.add(tablePanel2);


    }

    public void setWrongLabelVisible(boolean visible, int nrOfTheLabel, boolean all)
    {
        if(all)
            for(int i=0;i<nrWrongLabels;i++)
                wrongLabels.get(i).setVisible(visible);
        else
            wrongLabels.get(nrOfTheLabel).setVisible(visible);
    }

    public void updateTableAvailabePackages(ArrayList<VacationPackage> data)
    {
        int nr= tableModelAvailablePackages.getRowCount();
        for(int i=nr-1;i>=0;i--)
            tableModelAvailablePackages.removeRow(i);

        Object[] values= new Object[8];
        for (int i=0;i< data.size();i++) {
            VacationPackage vacationPackage= data.get(i);
            values[0] = vacationPackage.getId();
            values[1]=vacationPackage.getName();
            values[2]=vacationPackage.getStartDate();
            values[3]=vacationPackage.getEndDate();
            values[4]=vacationPackage.getPrice();
            values[5]=vacationPackage.getDetails();
            values[6]=vacationPackage.getVacationDestination().getName();
            values[7] = vacationPackage.getVacationDestination().getId();
            tableModelAvailablePackages.addRow(values);
        }
    }

    public void updateTableBookedPackages(ArrayList<VacationPackage> data)
    {
        int nr= tableModelBookedPackages.getRowCount();
        for(int i=nr-1;i>=0;i--)
            tableModelBookedPackages.removeRow(i);

        Object[] values= new Object[8];
        for (int i=0;i< data.size();i++) {
            VacationPackage vacationPackage= data.get(i);
            values[0] = vacationPackage.getId();
            values[1]=vacationPackage.getName();
            values[2]=vacationPackage.getStartDate();
            values[3]=vacationPackage.getEndDate();
            values[4]=vacationPackage.getPrice();
            values[5]=vacationPackage.getDetails();
            values[6]=vacationPackage.getVacationDestination().getName();
            values[7] = vacationPackage.getVacationDestination().getId();
            tableModelBookedPackages.addRow(values);
        }
    }

    class ButtonsListenerRegularUser implements ActionListener {
        private RegularUserView view;
        private RegularUserController controller;

        public ButtonsListenerRegularUser(RegularUserView view, RegularUserController controller) {
            super();
            this.view = view;
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object event = e.getSource();
            int row;
            view.setWrongLabelVisible(false, 0, true);
            int result;
            if (event == view.buttons.get(0)) //log out
            {
                controller.logOut();
            }
            else
            if (event == view.buttons.get(1)) //filter
            {
                String destination = view.fields.get(0).getText();
                String price = view.fields.get(1).getText();
                String startDate = view.fields.get(2).getText();
                String endDate = view.fields.get(3).getText();
                if(destination == null || destination.equals(""))
                    destination=null;
                if(price == null || price.equals(""))
                    price=null;
                if(startDate == null || startDate.equals(""))
                    startDate=null;
                if(endDate == null || endDate.equals(""))
                    endDate=null;

                if(destination==null && price == null && startDate == null && endDate == null)
                    view.setWrongLabelVisible(true,2,false);
                else
                    controller.filterAvailablePackages(destination, price, startDate, endDate);

            }
            else
            if (event == view.buttons.get(2)) //book
            {
                if((row=view.availablePackagesTable.getSelectedRow())>=0){
                    long package_id = (long)view.availablePackagesTable.getValueAt(row,0);
                    controller.bookPackage(package_id);
                }
                else
                    view.setWrongLabelVisible(true,0,false);
            }
            else
            if (event == view.buttons.get(3)) //view all
            {
                controller.getAllPackages();
            }
        }
    }
}
