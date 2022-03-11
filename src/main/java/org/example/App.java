package org.example;

import org.example.presentation.controller.RegularUserController;
import org.example.presentation.controller.TravellingAgencyController;
import org.example.presentation.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        RegularUserController regularUserController = new RegularUserController();
        TravellingAgencyController travellingAgencyController = new TravellingAgencyController();
        MainFrame mainFrame= new MainFrame("Booking",travellingAgencyController, regularUserController);
    }
}
