package p2.gui;// Java Program to create a

// blank label and add text to it.

import p2.ts.TrainSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Input extends JFrame
{

    //Variables
    private JTextArea intro;
    private JTextArea print;

    //Station
    private JTextField stationS;

    //Segment
    private JTextField segmentS;
    private JTextField sgStartStat;
    private JTextField sgEndStat;

    //Route
    private JTextField routeS;
    private JTextField rtrndTrip;
    private JTextField rtSegments;

    //Train
    private JTextField trainS;
    private JTextField trnStart;

    //Event
    private JTextField eventS;
    private JTextField compntName;
    private JTextField evtPerform;

    private JButton save;
    private JButton clear;
    private JButton clearAll;
    private JButton exit;

    private JPanel display;
    private JPanel grid;
    private JPanel button;
    private JPanel picture;
    private JPanel panel;
    private JPanel bpanel;


    //Station
    private JLabel sName;

    //Segment
    private JLabel sgName;
    private JLabel sgStart;
    private JLabel sgEnd;

    //Train
    private JLabel tName;
    private JLabel tStartTime;

    //Route
    private JLabel rtName;
    private JLabel rndTrip;
    private JLabel rtSgmnts;

    //Event
    private JLabel evtType;
    private JLabel cmpName;
    private JLabel evtPfrm;

    private JLabel photo;

    private JTable entry;

    private JScrollPane scroll;

    //Model
    DefaultTableModel model = new DefaultTableModel();

    Input()
    {

        //Set the title
        setTitle("Input Section");

        //Specify an action for the close button
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set Layout
        setLayout(new BorderLayout());

        //Set Layout
        setLayout(new BorderLayout());

        //Image Panel
        picture = new JPanel();
        photo = new JLabel();

        String imageName = "C:\\Users\\joshg\\OneDrive - The University of the West Indies, Mona Campus\\University Of The West Indies\\School Files\\Year 2 - Two\\Semester One\\Object-Oriented Programming\\Programming\\Program\\Project Two - ATMS - Modified - Josiah-John Green_JVAX\\src\\p2\\photo\\33842a0fc3c72951d40b6d6b8956c8c1.jpg";

        ImageIcon icon = new ImageIcon(imageName);
        Image image = icon.getImage();
        Image modimage = image.getScaledInstance(2000, 400, Image.SCALE_DEFAULT);
        icon = new ImageIcon(modimage);

        icon.getImage().flush();
        photo.setIcon(icon);

        picture.setBackground(Color.white);
        picture.add(photo, BorderLayout.PAGE_START);

        //Display Panel
        display = new JPanel();

        intro = new JTextArea("\n" + "\t\t\t\t\t\t\t\t\t\t" + "Entry List" + "\n");
        intro.setFont(new Font("Monospaced", Font.BOLD, 15));
        intro.setBackground(Color.white);

        print = new JTextArea("\n" + "\t\t\t\t\t\t\t\t\t" + "  Readings Displayed Here" + "\n");
        print.setFont(new Font("Monospaced", Font.PLAIN, 15));
        print.setBackground(Color.white);

        //Table

        //Table - Station
        stationS = new JTextField(2);

        //Table - Segment
        segmentS = new JTextField(2);
        sgStartStat = new JTextField(2);
        sgEndStat = new JTextField(2);

        //Table - Route
        routeS = new JTextField(2);
        rtrndTrip = new JTextField(2);
        rtSegments = new JTextField(2);

        //Table - Train
        trainS = new JTextField(2);
        trnStart = new JTextField(2);

        //Table - Event
        compntName = new JTextField(2);
        eventS = new JTextField(2);
        evtPerform = new JTextField(2);

        //Table
        entry = new JTable(model);
        entry.setFont(new Font("Monospaced", Font.BOLD, 12));

        model.addColumn("No.");
        model.addColumn("Station");
        model.addColumn("Segments");
        model.addColumn("Start Station");
        model.addColumn("End Station");
        model.addColumn("Routes");
        model.addColumn("Round Trip?");
        model.addColumn("Route Segments");
        model.addColumn("Trains");
        model.addColumn("Start Time");
        model.addColumn("Event Name");
        model.addColumn("Event Type");
        model.addColumn("Performed Event");

        entry.setBounds(30,40, 200, 300);
        entry.setBackground(Color.white);

        //Scroll Pane
        scroll = new JScrollPane(entry);

        scroll.setBackground(Color.white);
        scroll.setFont(new Font("Monospaced", Font.BOLD, 12));

        display.setBackground(Color.white);
        display.add(intro, BorderLayout.NORTH);
        display.add(scroll, BorderLayout.CENTER);
        display.setLayout(new GridLayout(3, 1));

        //Grid Panel
        grid = new JPanel();

        grid.setBackground(Color.white);
        grid.setLayout(new GridLayout(13, 2));

        //Text Panel

        //Stations
        sName = new JLabel("Enter Station Name:  ");
        sName.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Segments
        sgName = new JLabel("Enter Segment Name:  ");
        sgName.setFont(new Font("Monospaced", Font.BOLD, 12));

        sgStart = new JLabel("Enter Start Station:  ");
        sgStart.setFont(new Font("Monospaced", Font.BOLD, 12));

        sgEnd = new JLabel("Enter End Station:  ");
        sgEnd.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Routes
        rtName = new JLabel("Enter Route Name:  ");
        rtName.setFont(new Font("Monospaced", Font.BOLD, 12));

        rndTrip = new JLabel("Is This A Round Trip? (\"true\" or \"false\") :  ");
        rndTrip.setFont(new Font("Monospaced", Font.BOLD, 12));

        rtSgmnts = new JLabel("Enter The Route`s Segment (Seperate by \',\') :  ");
        rtSgmnts.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Trains
        tName = new JLabel("Enter Train Name:  ");
        tName.setFont(new Font("Monospaced", Font.BOLD, 12));

        tStartTime = new JLabel("Enter Train Start Time:  ");
        tStartTime.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Events
        evtType = new JLabel("Enter Type of the System Component (\"Station\" or \"Route\" or \"Segment\" or \"Train\"):  ");
        evtType.setFont(new Font("Monospaced", Font.BOLD, 12));

        cmpName = new JLabel("Enter Name of the System Component :  ");
        cmpName.setFont(new Font("Monospaced", Font.BOLD, 12));

        evtPfrm = new JLabel("Enter The Event to be Performed (\"Open\" or \"Close\"):  ");
        evtPfrm.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Grid - Stations
        grid.add(sName);
        grid.add(stationS);

        //Grid - Segment

        grid.add(sgName);
        grid.add(segmentS);

        grid.add(sgStart);
        grid.add(sgStartStat);

        grid.add(sgEnd);
        grid.add(sgEndStat);

        //Grid - Route

        grid.add(rtName);
        grid.add(routeS);

        grid.add(rndTrip);
        grid.add(rtrndTrip);

        grid.add(rtSgmnts);
        grid.add(rtSegments);

        //Grid- Train

        grid.add(tName);
        grid.add(trainS);

        grid.add(tStartTime);
        grid.add(trnStart);

        //Grid - Event
        grid.add(evtType);
        grid.add(eventS);

        grid.add(cmpName);
        grid.add(compntName);

        grid.add(evtPfrm);
        grid.add(evtPerform);

        //Button Panel
        button = new JPanel();

        button.setBackground(Color.white);

        save = new JButton("Save");
        save.setPreferredSize(new Dimension(50, 25));
        save.setBackground(Color.lightGray);
        save.setFont(new Font("Monospaced", Font.BOLD, 12));

        clear = new JButton("Clear");
        clear.setPreferredSize(new Dimension(50, 25));
        clear.setBackground(Color.lightGray);
        clear.setFont(new Font("Monospaced", Font.BOLD, 12));

        clearAll = new JButton("Clear All");
        clearAll.setPreferredSize(new Dimension(50, 25));
        clearAll.setBackground(Color.lightGray);
        clearAll.setFont(new Font("Monospaced", Font.BOLD, 12));

        exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(50, 25));
        exit.setBackground(Color.lightGray);
        exit.setFont(new Font("Monospaced", Font.BOLD, 12));

        button.add(save);
        save.addActionListener(this::saveActionPerformed);

        button.add(clear);
        clear.addActionListener(new clearButtonListener());

        button.add(clearAll);
        clearAll.addActionListener(new clearAllButtonListener());

        button.add(exit);
        exit.addActionListener(new exitButtonListener());

        button.setLayout(new GridLayout(1, 4));

        //Panel
        panel = new JPanel();
        panel.setBackground(Color.white);

        panel.add(display, BorderLayout.CENTER);

        panel.setLayout(new GridLayout(2, 1));

        bpanel = new JPanel();
        bpanel.setBackground(Color.white);

        bpanel.add(grid, BorderLayout.CENTER);
        bpanel.add(button, BorderLayout.SOUTH);

        bpanel.setLayout(new GridLayout(2, 1));

        //Addition
        //add(picture, BorderLayout.PAGE_START);
        add(panel, BorderLayout.CENTER);
        add(bpanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

    }

    private void saveActionPerformed(ActionEvent e)
    {

        int rowCount = model.getRowCount() + 1;

        //Append Row
        model.insertRow(model.getRowCount(), new Object[] {rowCount + ".", stationS.getText(), segmentS.getText(), sgStartStat.getText(), sgEndStat.getText(), routeS.getText(), rtrndTrip.getText(), rtSegments.getText(), trainS.getText(), trnStart.getText(), compntName.getText(), eventS.getText(), evtPerform.getText()});

        rowCount++;


        if(stationS.getText() != null && segmentS.getText() != null && sgStartStat.getText() != null && sgEndStat.getText() != null && rtSegments.getText() != null && routeS.getText() != null && rtrndTrip.getText() != null && trainS.getText() != null && trnStart.getText() != null)
        {

            //Object
            TrainSystem trains = new TrainSystem();

            //Initiating Object

            ArrayList <String> rs = new ArrayList<String>();

            String rtsegments = rtSegments.getText();

            String[] rtseg = rtsegments.split(", ");

            for (String seg : rtseg)
            {

                rs.add(seg);
                System.out.println(seg);

            }

            //Addition to Trains

            trains.addRoute(routeS.getText(), Boolean.parseBoolean(rtrndTrip.getText()), rs);

            trains.addTrain(trainS.getText(), Integer.parseInt(trnStart.getText()));

            trains.addStation(stationS.getText());

            trains.addSegment(segmentS.getText(), sgStartStat.getText(), sgEndStat.getText());

            //Create File
            Path path = Paths.get("C:\\Users\\joshg\\OneDrive - The University of the West Indies, Mona Campus\\University Of The West Indies\\School Files\\Year 2 - Two\\Semester One\\Object-Oriented Programming\\Programming\\Program\\Project Two - ATMS - Modified - Josiah-John Green_JVAX\\src\\p2\\file\\runFile");

            //Store to File
            try
            {

                String train = trains.toString();

                Files.writeString(path, train, StandardCharsets.UTF_8);


            }
            catch (IOException ex)
            {

                //Handle Exception

            }


        }


    }

    private class clearButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            stationS.setText("");
            segmentS.setText("");
            sgStartStat.setText("");
            sgEndStat.setText("");
            routeS.setText("");
            rtrndTrip.setText("");
            rtSegments.setText("");
            trainS.setText("");
            trnStart.setText("");
            eventS.setText("");
            compntName.setText("");
            evtPerform.setText("");

            JOptionPane.showMessageDialog(display, "Field Cleared!");

        }

    }

    private class clearAllButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            model.setRowCount(0);

            JOptionPane.showMessageDialog(display, "Table Cleared!");


        }

    }

    private class exitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            System.exit(0);

        }

    }

    public static void main(String[] args)
    {

        new Input();

    }


}

