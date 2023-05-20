package p2.gui;// Java Program to create a

// blank label and add text to it.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class

InputUI extends JFrame
{

    private JFrame frame;

    private JTextArea intro;

    private JButton run;
    private JButton exit;

    private JPanel display;
    private JPanel buttons;
    private JPanel picture;
    private JLabel photo;

    InputUI()
    {

        //Set the frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set the title
        frame.setTitle("Input Menu");

        //Specify an action for the close button
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set Layout
        frame.setLayout(new BorderLayout());

        //Image Panel
        picture = new JPanel();
        photo = new JLabel();

        String imageName = "C:\\Users\\joshg\\OneDrive - The University of the West Indies, Mona Campus\\University Of The West Indies\\School Files\\Year 2 - Two\\Semester One\\Object-Oriented Programming\\Programming\\Program\\Project Two - ATMS - Modified - Josiah-John Green_JVAX\\src\\p2\\photo\\502eeb0ea626ee985645d08893fd84a0.jpg";

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

        intro = new JTextArea("\nInput Screen" + "\n");
        intro.setBackground(Color.white);
        intro.setFont(new Font("Monospaced", Font.BOLD, 15));

        display.setBackground(Color.white);
        display.add(intro, BorderLayout.CENTER);

        run = new JButton("1. Run");
        run.setBackground(Color.lightGray);
        run.setFont(new Font("Monospaced", Font.BOLD, 12));

        exit = new JButton("2. Exit");
        exit.setBackground(Color.lightGray);
        exit.setFont(new Font("Monospaced", Font.BOLD, 12));

        //Button Panel

        buttons = new JPanel();

        buttons.add(run, BorderLayout.AFTER_LINE_ENDS);
        run.setPreferredSize(new Dimension(150, 50));
        run.addActionListener(new runButtonListener());

        buttons.add(exit, BorderLayout.PAGE_END);
        exit.setPreferredSize(new Dimension(150, 50));
        exit.addActionListener(new exitButtonListener());

        buttons.setLayout(new GridLayout(2, 1));

        frame.add(picture, BorderLayout.PAGE_START);
        frame.add(display, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {

        new InputUI();

    }


    private class runButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            frame.setVisible(false);

            new Run();

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