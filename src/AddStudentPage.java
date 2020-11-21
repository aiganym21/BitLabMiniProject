import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddStudentPage extends JPanel {
    private MainFrame parent;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel ageLabel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField ageField;
    private int studentID = 0;


    private JButton addButton;
    private JButton backButton;

    public AddStudentPage(MainFrame parent) {
        this.parent = parent;

        setSize(500, 500);
        setLayout(null);

        nameLabel = new JLabel("NAME:");
        nameLabel.setSize(80, 30);
        nameLabel.setLocation(100, 50);
        add(nameLabel);

        surnameLabel = new JLabel("SURNAME:");
        surnameLabel.setSize(80, 30);
        surnameLabel.setLocation(100, 100);
        add(surnameLabel);


        ageLabel = new JLabel("AGE:");
        ageLabel.setSize(80, 30);
        ageLabel.setLocation(100, 200);
        add(ageLabel);

        nameField = new JTextField();
        nameField.setSize(200, 30);
        nameField.setLocation(200, 50);
        add(nameField);

        surnameField = new JTextField();
        surnameField.setSize(200, 30);
        surnameField.setLocation(200, 100);
        add(surnameField);

        ageField = new JTextField();
        ageField.setSize(200, 30);
        ageField.setLocation(200, 200);
        add(ageField);

        addButton = new JButton("ADD");
        addButton.setSize(100, 30);
        addButton.setLocation(100, 300);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Student student = new Student(null, name, surname, age);
                parent.addStudent(student);
            }
        });

        backButton = new JButton("BACK");
        backButton.setSize(100, 30);
        backButton.setLocation(300, 300);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.getFirstPage().setVisible(false);
                parent.getMainMenuPage().setVisible(true);
            }
        });

    }


}
