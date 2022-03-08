package org.example.presentation.view;

        import org.example.business.model.VacationDestination;
        import org.example.business.model.VacationPackage;
        import org.example.presentation.controller.TravellingAgencyController;

        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

    public class TravellingAgencyView extends JPanel {
        private int nrButtons;
        /** the list of buttons in the view. */
        public ArrayList<JButton> buttons;
        /** the table where the clients will be displayed */
        public JTable destinationsTable;
        public JTable packagesTable;
        private DefaultTableModel tableModelDestinations;
        private DefaultTableModel tableModelPackages;
        private int nrFields;
        /** the list of text fields in the view. */
        public ArrayList<JTextField> fields;
        private int nrWrongLabels;
        /** the list of labels which will be used for showing error messages */
        public ArrayList<JLabel> wrongLabels;
        private TravellingAgencyController travellingAgencyController;

        /**
         * Creates a new instance of ClientsViewPanel.
         * @param height height of the panel
         * @param width width of the panel
         */
        public TravellingAgencyView(int height, int width, TravellingAgencyController travellingAgencyController)
        {
            super();
            this.travellingAgencyController=travellingAgencyController;
            this.setBounds(0,0, height, width);
            this.setLayout(null);
            this.setBackground(Color.DARK_GRAY);
            nrButtons=9;
            buttons=new ArrayList<>();

            for(int i=0;i<nrButtons;i++)
            {
                JButton button=new JButton();
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("TimesRoman",20,20));
                button.addActionListener(new ButtonsListenerTravellingAgency(this,travellingAgencyController));
                buttons.add(button);
                this.add(button);
            }

            buttons.get(0).setText("LOG OUT");
            buttons.get(0).setBounds(1400,30, 150, 50);

            //buttons.get(1).setText("EDIT");
            //buttons.get(1).setBounds(70, 600, 150, 50);
            buttons.get(2).setText("DELETE");
            buttons.get(2).setBounds(230, 600, 150, 50);
            buttons.get(3).setText("ADD");
            buttons.get(3).setBounds(70, 600, 150, 50);


            buttons.get(5).setText("EDIT");
            buttons.get(5).setBounds(1000, 770, 150, 50);
            buttons.get(6).setText("DELETE");
            buttons.get(6).setBounds(1160, 600, 150, 50);
            buttons.get(7).setText("ADD");
            buttons.get(7).setBounds(1000, 600, 150, 50);
            buttons.get(8).setText("SAVE");
            buttons.get(8).setBounds(1160, 770, 150, 50);

            nrFields=9;
            fields = new ArrayList<>();

            for(int i=0;i<nrFields;i++)
            {
                JTextField field=new JTextField();
                field.setFont(new Font("TimesRoman",20,20));
                field.setForeground(Color.WHITE);
                field.setOpaque(false);
                this.add(field);
                fields.add(field);
            }


            fields.get(0).setBounds(60,685,320,45);

            fields.get(1).setBounds(430,685,150,45);
            fields.get(2).setBounds(582,685,150,45);
            fields.get(3).setBounds(734,685,150,45);
            fields.get(4).setBounds(886,685,150,45);
            fields.get(5).setBounds(1038,685,150,45);
            fields.get(6).setBounds(1190,685,150,45);
            fields.get(7).setBounds(1324,685,0,0);
            fields.get(7).setVisible(false);
            fields.get(8).setBounds(1324,685,0,0);
            fields.get(8).setVisible(false);
            //fields.get(5).setEditable(false);


            Border border = BorderFactory.createEmptyBorder();

            JPanel tablePanel = new JPanel();
            tablePanel.setBackground(Color.DARK_GRAY);
            tablePanel.setBounds(50,100,350, 500);
            tablePanel.setBorder(border);

            tableModelDestinations= new DefaultTableModel();
            tableModelDestinations.addColumn("id");
            tableModelDestinations.addColumn("name");

            tableModelPackages= new DefaultTableModel();
            tableModelPackages.addColumn("id");
            tableModelPackages.addColumn("name");
            tableModelPackages.addColumn("start date");
            tableModelPackages.addColumn("end date");
            tableModelPackages.addColumn("max nr people");
            tableModelPackages.addColumn("price");
            tableModelPackages.addColumn("details");
            tableModelPackages.addColumn("destination");
            tableModelPackages.addColumn("status");
            tableModelPackages.addColumn("destination_id");


            destinationsTable=new JTable();
            destinationsTable.setModel(tableModelDestinations);
            destinationsTable.setBackground(Color.DARK_GRAY);
            destinationsTable.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            destinationsTable.setForeground(Color.WHITE);
            destinationsTable.setFont(new Font("TimesRoman",20,20));
            destinationsTable.setRowHeight(40);
            destinationsTable.getTableHeader().setFont(new Font("TimesRoman",20,20));
            destinationsTable.getTableHeader().setBackground(Color.DARK_GRAY);
            destinationsTable.getTableHeader().setForeground(Color.WHITE);
            destinationsTable.setPreferredScrollableViewportSize(new Dimension(350,500));
            destinationsTable.setFillsViewportHeight(true);

            destinationsTable.getColumnModel().getColumn(0).setWidth(0);
            destinationsTable.getColumnModel().getColumn(0).setMinWidth(0);
            destinationsTable.getColumnModel().getColumn(0).setMaxWidth(0);

            JScrollPane scrollPane = new JScrollPane(destinationsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setPreferredSize(new Dimension(320,490));
            scrollPane.setBorder(border);
            tablePanel.add(scrollPane);
            this.add(tablePanel);


            JPanel tablePanel2 = new JPanel();
            tablePanel2.setBackground(Color.DARK_GRAY);
            tablePanel2.setBounds(420,100,1100, 500);
            tablePanel2.setBorder(border);

            packagesTable=new JTable();
            packagesTable.setModel(tableModelPackages);
            packagesTable.setBackground(Color.DARK_GRAY);
            packagesTable.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            packagesTable.setForeground(Color.WHITE);
            packagesTable.setFont(new Font("TimesRoman",20,20));
            packagesTable.setRowHeight(40);
            packagesTable.getTableHeader().setFont(new Font("TimesRoman",20,20));
            packagesTable.getTableHeader().setBackground(Color.DARK_GRAY);
            packagesTable.getTableHeader().setForeground(Color.WHITE);
            packagesTable.setPreferredScrollableViewportSize(new Dimension(1100,500));
            packagesTable.setFillsViewportHeight(true);


            packagesTable.getColumnModel().getColumn(0).setWidth(0);
            packagesTable.getColumnModel().getColumn(0).setMinWidth(0);
            packagesTable.getColumnModel().getColumn(0).setMaxWidth(0);

            packagesTable.getColumnModel().getColumn(9).setWidth(0);
            packagesTable.getColumnModel().getColumn(9).setMinWidth(0);
            packagesTable.getColumnModel().getColumn(9).setMaxWidth(0);

            JScrollPane scrollPane2 = new JScrollPane(packagesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane2.setPreferredSize(new Dimension(1070,490));
            scrollPane2.setBorder(border);
            tablePanel2.add(scrollPane2);
            this.add(tablePanel2);

            nrWrongLabels=10;
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

            wrongLabels.get(0).setText("*please introduce a valid destination");
            wrongLabels.get(0).setBounds(60,730,440,30);
            wrongLabels.get(1).setText("*please select a destination");
            wrongLabels.get(1).setBounds(430,750,440,30);
            wrongLabels.get(2).setText("*please introduce a valid name");
            wrongLabels.get(2).setBounds(430,750,440,30);
            wrongLabels.get(3).setText("*please introduce a valid start date");
            wrongLabels.get(3).setBounds(430,750,440,30);
            wrongLabels.get(4).setText("*please introduce a valid end date");
            wrongLabels.get(4).setBounds(430,750,440,30);
            wrongLabels.get(5).setText("*please introduce a valid number of people");
            wrongLabels.get(5).setBounds(430,750,440,30);
            wrongLabels.get(6).setText("*please introduce a valid price");
            wrongLabels.get(6).setBounds(430,750,440,30);
            wrongLabels.get(7).setText("*please introduce valid details");
            wrongLabels.get(7).setBounds(430,750,440,30);
            wrongLabels.get(8).setText("*please select a vacation package");
            wrongLabels.get(8).setBounds(430,750,440,30);
            wrongLabels.get(9).setText("*please select a destination");
            wrongLabels.get(9).setBounds(60,730,440,30);

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

            dataLabels[0].setText("Vacation Destinations");
            dataLabels[0].setBounds(100,60,200,30);
            dataLabels[1].setText("Vacation Packages");
            dataLabels[1].setBounds(800,60,200,30);
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
        public void updateTableDestinations(ArrayList<VacationDestination> data)
        {
            int nr= tableModelDestinations.getRowCount();
            for(int i=nr-1;i>=0;i--)
                tableModelDestinations.removeRow(i);

            Object[] values= new Object[2];
            for (int i=0;i< data.size();i++) {
                values[0] = data.get(i).getId();
                values[1] = data.get(i).getName();
                tableModelDestinations.addRow(values);
            }
        }

        public void updateTablePackages(ArrayList<VacationPackage> data)
        {
            int nr= tableModelPackages.getRowCount();
            for(int i=nr-1;i>=0;i--)
                tableModelPackages.removeRow(i);

            Object[] values= new Object[10];
            for (int i=0;i< data.size();i++) {
                VacationPackage vacationPackage= data.get(i);
                values[0] = vacationPackage.getId();
                values[1]=vacationPackage.getName();
                values[2]=vacationPackage.getStartDate();
                values[3]=vacationPackage.getEndDate();
                values[4]=vacationPackage.getMaxNrPeople();
                values[5]=vacationPackage.getPrice();
                values[6]=vacationPackage.getDetails();
                values[7]=vacationPackage.getVacationDestination().getName();
                values[8]=vacationPackage.getStatus();
                values[9] = vacationPackage.getVacationDestination().getId();
                tableModelPackages.addRow(values);
            }
        }

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


        public void updateFieldsToEdit(int row, boolean filled)
        {
            if(filled)
            {
                fields.get(1).setText((String)(packagesTable.getValueAt(row,1)));
                fields.get(2).setText((String)(packagesTable.getValueAt(row,2)));
                fields.get(3).setText((String)(packagesTable.getValueAt(row,3)));
                fields.get(4).setText(String.valueOf(packagesTable.getValueAt(row,4)));
                fields.get(5).setText(String.valueOf(packagesTable.getValueAt(row,5)));
                fields.get(6).setText((String)(packagesTable.getValueAt(row,6)));
                fields.get(7).setText(String.valueOf(packagesTable.getValueAt(row,9)));
                fields.get(8).setText(String.valueOf(packagesTable.getValueAt(row,0)));
            }
            else
            {
                fields.get(0).setText(null);
                fields.get(1).setText(null);
                fields.get(2).setText(null);
                fields.get(3).setText(null);
                fields.get(4).setText(null);
                fields.get(5).setText(null);
                fields.get(6).setText(null);
                fields.get(7).setText(null);
                fields.get(8).setText(null);
            }
        }

        class ButtonsListenerTravellingAgency implements ActionListener {
            private TravellingAgencyView view;
            private TravellingAgencyController controller;
            public ButtonsListenerTravellingAgency(TravellingAgencyView view, TravellingAgencyController controller){
                super();
                this.view=view;
                this.controller=controller;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                Object event=e.getSource(); int row; TravellingAgencyView.this.setWrongLabelVisible(false, 0,true); int result;
                if(event==view.buttons.get(0)) //log out
                {
                    controller.logOut();

                }
                else
                if(event==view.buttons.get(2)) //delete destination
                {
                    if((row=view.destinationsTable.getSelectedRow())>=0){
                        long destination_id = (long)view.destinationsTable.getValueAt(row,0);
                        controller.deleteVacationDestination(destination_id);
                    }
                    else view.setWrongLabelVisible(true,9,false);
                }
                else
                if(event==view.buttons.get(3)) //add destination
                {
                    controller.addVacationDestination(view.fields.get(0).getText());
                }
//
                else
                if(event==TravellingAgencyView.this.buttons.get(5)) //edit package
                {
                    if((row=view.packagesTable.getSelectedRow())>=0){
                        long package_id = (long)view.packagesTable.getValueAt(row,0);
                        view.updateFieldsToEdit(row,true);
                    }
                    else view.setWrongLabelVisible(true,8,false);
                }
                else
                if(event==view.buttons.get(6)) //delete package
                {
                    if((row=view.packagesTable.getSelectedRow())>=0){
                        long package_id = (long)view.packagesTable.getValueAt(row,0);
                        controller.deleteVacationPackage(package_id);
                    }
                    else view.setWrongLabelVisible(true,8,false);
                }
                else
                if(event==TravellingAgencyView.this.buttons.get(7)) //add package
                {
                    if((row=view.destinationsTable.getSelectedRow())>=0){
                        String name = view.fields.get(1).getText();
                        String start_date = view.fields.get(2).getText();
                        String end_date = view.fields.get(3).getText();
                        String nr_people = view.fields.get(4).getText();
                        String price = view.fields.get(5).getText();
                        String details = view.fields.get(6).getText();
                        long destination_id = (long)view.destinationsTable.getValueAt(row,0);
                        controller.addVacationPackage(name, start_date, end_date, nr_people,price,details, destination_id);
                    }
                    else view.setWrongLabelVisible(true,1,false);

                }
                else
                if(event==view.buttons.get(8)) //save package
                {
                    String name = view.fields.get(1).getText();
                    String start_date = view.fields.get(2).getText();
                    String end_date = view.fields.get(3).getText();
                    String nr_people = view.fields.get(4).getText();
                    String price = view.fields.get(5).getText();
                    String details = view.fields.get(6).getText();
                    long package_id = Long.parseLong(view.fields.get(8).getText());
                    controller.updateVacationPackage(name, start_date, end_date, nr_people,price,details, package_id);
                }
            }
        }

    }
