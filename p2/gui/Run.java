package p2.gui;// Java Program to create a

// blank label and add text to it.

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.*;

import p2.logging.Simulator;


public class Run extends JFrame
{

    private JFrame frame;

    private JTextArea intro;
    private JTextArea displayArea;

    private JPanel display;

    private JPanel picture;

    private JLabel photo;

    private Scanner file;
    private StringBuilder sb;

    Run()
    {

        //Set the frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set the title
        frame.setTitle("Entry Menu");

        //Specify an action for the close button
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set Layout
        frame.setLayout(new BorderLayout());

        //Image Panel
        picture = new JPanel();
        photo = new JLabel();

        String imageName = "C:\\Users\\joshg\\OneDrive - The University of the West Indies, Mona Campus\\University Of The West Indies\\School Files\\Year 2 - Two\\Semester One\\Object-Oriented Programming\\Programming\\Program\\Project Two - ATMS - Modified - Josiah-John Green_JVAX\\src\\p2\\photo\\253de59d59105f24e3d2183d1c83f01e.jpg";

        ImageIcon icon = new ImageIcon(imageName);
        Image image = icon.getImage();
        Image modimage = image.getScaledInstance(2000, 350, Image.SCALE_DEFAULT);
        icon = new ImageIcon(modimage);

        icon.getImage().flush();
        photo.setIcon(icon);

        picture.setBackground(Color.white);
        picture.add(photo, BorderLayout.PAGE_START);

        //Display Panel
        display = new JPanel();

        displayArea = new JTextArea();
        displayArea.setBackground(Color.white);
        displayArea.setFont(new Font("Monospaced", Font.BOLD, 15));

        //File Creation
        try
        {

            file = new Scanner(new File("C:\\Users\\joshg\\OneDrive - The University of the West Indies, Mona Campus\\University Of The West Indies\\School Files\\Year 2 - Two\\Semester One\\Object-Oriented Programming\\Programming\\Program\\Project Two - ATMS - Modified - Josiah-John Green_JVAX\\src\\p2\\file\\simulatorFile"));

            sb = new StringBuilder();

            while(file.hasNextLine())
            {

                sb.append(file.nextLine() + "\n");


            }

            displayArea.setText(String.valueOf(sb));


        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();

        }

        display.setBackground(Color.white);
        display.add(displayArea, BorderLayout.CENTER);

        frame.add(picture, BorderLayout.PAGE_START);
        frame.add(display, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {

        new Run();

    }

}