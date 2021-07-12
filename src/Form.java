import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Form extends JFrame implements ActionListener {
    private File inputFile;
    public Form() {
        super("Моя программа");
        setBounds(300, 300, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setBorder(new EmptyBorder(0, 0, 0, 0));
        JLabel labelTitle = new JLabel("Счётчик слов");
        top.add(labelTitle);

        JPanel inputFile = new JPanel();
        JButton buttonFileChoosen = new JButton("Выбрать файл");
        buttonFileChoosen.setPreferredSize(new Dimension(200, 50));
        inputFile.add(buttonFileChoosen);
        buttonFileChoosen.addActionListener(this);

        JPanel bottom = new JPanel();
        JButton run = new JButton("Посчитать слова");
        run.setPreferredSize(new Dimension(200, 50));
        bottom.add(run);
        run.addActionListener(this);

        add(top, BorderLayout.NORTH);
        add(inputFile, BorderLayout.CENTER);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Выбрать файл")) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                inputFile = fileopen.getSelectedFile();
                new ReadFile(inputFile);
            }
        }
    }
}
