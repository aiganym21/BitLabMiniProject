import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListStudentsPage extends JPanel {

    private MainFrame parent;

    private JLabel label;
    private JButton back;

    private String header[] = {"Name","Surname","Age"};
    private JTable table;
    private JScrollPane scrollPane;


    public ListStudentsPage(MainFrame parent) {

        this.parent = parent;

        setSize(500,500);
        setLayout(null);

        label = new JLabel("SECOND PAGE");
        label.setSize(300,30);
        label.setLocation(100,100);
        add(label);

        back = new JButton("Back");
        back.setSize(300,30);
        back.setLocation(100,150);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.getSecondPage().setVisible(false);
                parent.getMainMenuPage().setVisible(true);
            }
        });

        //Создаем таблицу
        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);

        //Создаем панель для прокрутки
        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300,200);
        scrollPane.setLocation(100,200);
        add(scrollPane);

    }

    public void generateTable(ArrayList<Student> students){

        //Создаем двумерный массив из нашего массива объектов, чтобы JTable смог его обработать
        //players.length - количество строк, 3 - это количество колонок.
        Object data[][] = new Object[students.size()][4];

        for(int i =0;i<students.size();i++){
            data[i][0] = students.get(i).getName();
            data[i][1] = students.get(i).getSurname();
            data[i][2] = students.get(i).getAge();
        }

        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);

    }
}
