package p2.gui;// Java Program to create a

// blank label and add text to it.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class

EntryUI extends JFrame
{

    private JFrame frame;

    private JTextArea intro;

    private JButton input;
    private JButton print;
    private JButton exit;

    private JPanel display;
    private JPanel buttons;
    private JPanel picture;

    private JLabel photo;

    EntryUI()
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

        intro = new JTextArea("Entry Screen" + "\n");
        intro.setBackground(Color.white);
        intro.setFont(new Font("Monospaced", Font.BOLD, 15));

        display.setBackground(Color.white);
        display.add(intro, BorderLayout.CENTER);

        input = new JButton("1. Input");
        input.setBackground(Color.lightGray);
        input.setFont(new Font("Monospaced", Font.BOLD, 12));

        print = new JButton("2. Print");
        print.setBackground(Color.lightGray);
        print.setFont(new Font("Monospaced", Font.BOLD, 12));

        exit = new JButton("3. Exit");
        exit.setBackground(Color.lightGray);
        exit.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Button Panel
        buttons = new JPanel();

        buttons.add(input, BorderLayout.AFTER_LINE_ENDS);
        input.setPreferredSize(new Dimension(105, 50));
        input.addActionListener(new inputButtonListener());

        buttons.add(print, BorderLayout.AFTER_LINE_ENDS);
        print.setPreferredSize(new Dimension(105, 50));
        print.addActionListener(new printButtonListener());

        buttons.add(exit, BorderLayout.PAGE_END);
        exit.setPreferredSize(new Dimension(105, 50));
        exit.addActionListener(new exitButtonListener());

        buttons.setLayout(new GridLayout(3, 1));
        buttons.setBackground(Color.lightGray);

        frame.add(picture, BorderLayout.PAGE_START);
        frame.add(display, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {

        new EntryUI();

    }

    private class inputButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            frame.setVisible(false);

            new Input();

        }

    }

    private class printButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            frame.setVisible(false);

            new Print();

        }

    }

    private class exitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            frame.setVisible(false);

            new GraphicalUI();

        }

    }

}