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
                    if (!getFileExtension(inputFile).equals("txt")) throw new Exception();
                    label.setText(inputFile.getName());
                    FileProcessing fp = new FileProcessing(inputFile);
                    countWordInText = fp.getCountWordInText();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    label.setText("Формат не читается");
                }
            }
        }
        if (ae.getActionCommand().equals("Посчитать слова")) {
            if (countWordInText==0) JOptionPane.showMessageDialog(null,"Пусто");
            else JOptionPane.showMessageDialog(null, "Слов в файле: " + countWordInText);
            label.setText("Выберете файл");
            inputFile = null;
            countWordInText=0;
            System.gc();
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}
