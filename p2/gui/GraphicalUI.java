package p2.gui;// Java Program to create a

// blank label and add text to it.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalUI extends JFrame
{

    private JFrame frame;

    private JTextArea intro;
    private JButton f_input;
    private JButton m_entry;
    private JButton exit;
    private JPanel display;

    private JPanel buttons;
    private JPanel picture;

    private JLabel photo;

    GraphicalUI()
    {

        //Frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set the title
        frame.setTitle("Automatic Train System");

        //Specify an action for the close button
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Set Layout
        frame.setLayout(new BorderLayout());

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

        // Display Panel
        display = new JPanel();

        intro = new JTextArea("\n  Automatic Train System"
                + "\nSelect From The Following:" + "\n");
        intro.setFont(new Font("Monospaced", Font.BOLD, 15));

        display.setBackground(Color.white);
        display.add(intro, BorderLayout.CENTER);

        // Button Panel
        buttons = new JPanel();

        f_input = new JButton("1. File Input");
        f_input.setFont(new Font("Monospaced", Font.BOLD, 12));
        f_input.setBackground(Color.lightGray);

        m_entry = new JButton("2. Manual Entry");
        m_entry.setFont(new Font("Monospaced", Font.BOLD, 12));
        m_entry.setBackground(Color.lightGray);

        exit = new JButton("3. Exit");
        exit.setFont(new Font("Monospaced", Font.BOLD, 12));
        exit.setBackground(Color.lightGray);

        buttons.add(f_input, BorderLayout.AFTER_LINE_ENDS);
        f_input.setPreferredSize(new Dimension(150, 50));
        f_input.addActionListener(new inputButtonListener());

        buttons.add(m_entry, BorderLayout.CENTER);
        m_entry.setPreferredSize(new Dimension(150, 50));
        m_entry.addActionListener(new entryButtonListener());

        buttons.add(exit, BorderLayout.PAGE_END);
        exit.setPreferredSize(new Dimension(150, 50));
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

        new GraphicalUI();

    }

    private class entryButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {

            frame.setVisible(false);

            new EntryUI();

        }

    }

    private class inputButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            frame.setVisible(false);

            new InputUI();

        }

    }

    private class exitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                System.exit(0);

        }

    }

}