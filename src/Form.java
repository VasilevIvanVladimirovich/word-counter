import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Form extends JFrame implements ActionListener {
    private JLabel label;
    private JButton run;
    private JButton buttonFileChoosen;
    private JLabel labelTitle;

    private File inputFile;
    private int countWordInText;

    public Form() {
        super("Моя программа");
        setBounds(300, 300, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setBorder(new EmptyBorder(0, 0, 0, 0));
        labelTitle = new JLabel("Счётчик слов");
        top.add(labelTitle);

        JPanel inputFile = new JPanel();
        inputFile.setLayout(new FlowLayout());
        buttonFileChoosen = new JButton("Выбрать файл");
        buttonFileChoosen.setPreferredSize(new Dimension(200, 50));
        inputFile.add(buttonFileChoosen);
        label = new JLabel("Выберете файл");
        inputFile.add(label);
        buttonFileChoosen.addActionListener(this);

        JPanel bottom = new JPanel();
        run = new JButton("Посчитать слова");
        run.setPreferredSize(new Dimension(200, 50));
        bottom.add(run);
        run.addActionListener(this);

        add(top, BorderLayout.NORTH);
        add(inputFile, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Выбрать файл")) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                inputFile = fileopen.getSelectedFile();
                try {

                    label.setText(inputFile.getName());
                    FileProcessing fp = new FileProcessing(inputFile);
                    countWordInText = fp.getCountWordInText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ae.getActionCommand().equals("Посчитать слова")) {
            JOptionPane.showMessageDialog(null, "Слов в файле: " + countWordInText);
        }
    }
}
