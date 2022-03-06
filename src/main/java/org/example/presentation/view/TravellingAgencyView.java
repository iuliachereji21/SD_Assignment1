package org.example.presentation.view;

        import org.example.business.model.VacationDestination;
        import org.example.business.model.VacationPackage;

        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;
        import java.awt.event.ActionListener;
        import java.beans.PropertyDescriptor;
        import java.lang.reflect.Field;
        import java.lang.reflect.Method;
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

        /**
         * Creates a new instance of ClientsViewPanel.
         * @param height height of the panel
         * @param width width of the panel
         */
        public TravellingAgencyView(int height, int width)
        {
            super();
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
                buttons.add(button);
                this.add(button);
            }

            buttons.get(0).setText("LOG OUT");
            buttons.get(0).setBounds(1400,30, 150, 50);

            buttons.get(1).setText("EDIT");
            buttons.get(1).setBounds(70, 600, 150, 50);
            buttons.get(2).setText("DELETE");
            buttons.get(2).setBounds(230, 600, 150, 50);
            buttons.get(3).setText("ADD");
            buttons.get(3).setBounds(70, 770, 150, 50);
            buttons.get(4).setText("SAVE");
            buttons.get(4).setBounds(230, 770, 150, 50);


            buttons.get(5).setText("EDIT");
            buttons.get(5).setBounds(1000, 600, 150, 50);
            buttons.get(6).setText("DELETE");
            buttons.get(6).setBounds(1160, 600, 150, 50);
            buttons.get(7).setText("ADD");
            buttons.get(7).setBounds(1000, 770, 150, 50);
            buttons.get(8).setText("SAVE");
            buttons.get(8).setBounds(1160, 770, 150, 50);

            nrFields=7;
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
            //fields.get(5).setEditable(false);


            Border border = BorderFactory.createEmptyBorder();

            JPanel tablePanel = new JPanel();
            tablePanel.setBackground(Color.DARK_GRAY);
            tablePanel.setBounds(50,100,350, 500);
            tablePanel.setBorder(border);

            tableModelDestinations= new DefaultTableModel();
            tableModelDestinations.addColumn("name");
            tableModelPackages= new DefaultTableModel();
            tableModelPackages.addColumn("destination");
            tableModelPackages.addColumn("name");
            tableModelPackages.addColumn("start date");
            tableModelPackages.addColumn("end date");
            tableModelPackages.addColumn("max nr people");
            tableModelPackages.addColumn("price");
            tableModelPackages.addColumn("status");

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

            JScrollPane scrollPane2 = new JScrollPane(packagesTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane2.setPreferredSize(new Dimension(1070,490));
            scrollPane2.setBorder(border);
            tablePanel2.add(scrollPane2);
            this.add(tablePanel2);

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

            wrongLabels.get(0).setText("*please introduce a valid destination");
            wrongLabels.get(0).setBounds(60,730,440,30);
            wrongLabels.get(1).setText("*please introduce a valid phone number");
            wrongLabels.get(1).setBounds(1120,450,440,30);
            wrongLabels.get(2).setText("*please introduce a valid email");
            wrongLabels.get(2).setBounds(1120,530,440,30);
            wrongLabels.get(3).setText("*please introduce a valid date");
            wrongLabels.get(3).setBounds(1120,610,440,30);
            wrongLabels.get(4).setText("*please select a row");
            wrongLabels.get(4).setBounds(970,200,440,30);
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

            Object[] values= new Object[1];
            for (int i=0;i< data.size();i++) {
                values[0] = data.get(i).getName();
                tableModelDestinations.addRow(values);
            }
        }

        public void updateTablePackages(ArrayList<VacationPackage> data)
        {
            int nr= tableModelPackages.getRowCount();
            for(int i=nr-1;i>=0;i--)
                tableModelPackages.removeRow(i);

            Object[] values= new Object[7];
            for (int i=0;i< data.size();i++) {
                VacationPackage vacationPackage= data.get(i);
                values[0] = vacationPackage.getVacationDestination().getName();
                values[1]=vacationPackage.getName();
                values[2]=vacationPackage.getStartDate();
                values[3]=vacationPackage.getEndDate();
                values[4]=vacationPackage.getMaxNrPeople();
                values[5]=vacationPackage.getPrice();
                values[6]=vacationPackage.getStatus();
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


//        public void updateFieldsToEdit(int row, boolean filled)
//        {
//            if(filled)
//            {
//                fields.get(0).setText((String)(clientsTable.getValueAt(row,1)));
//                fields.get(1).setText((String)(clientsTable.getValueAt(row,2)));
//                fields.get(2).setText((String)(clientsTable.getValueAt(row,3)));
//                fields.get(3).setText((String)(clientsTable.getValueAt(row,4)));
//                fields.get(4).setText((String)(clientsTable.getValueAt(row,5)));
//                fields.get(5).setText(String.valueOf(clientsTable.getValueAt(row,0)));
//            }
//            else
//            {
//                fields.get(0).setText(null);
//                fields.get(1).setText(null);
//                fields.get(2).setText(null);
//                fields.get(3).setText(null);
//                fields.get(4).setText(null);
//                fields.get(5).setText(null);
//            }
//        }

    }
