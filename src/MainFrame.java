import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private MainMenu mainMenuPage;
    private AddStudentPage firstPage;
    private ListStudentsPage secondPage;

    public Socket socket;
    public ObjectOutputStream outStream;
    public ObjectInputStream inStream;
    private Student[] student = new Student[100];

    private int countStudents = 0;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Application");
        setSize(500, 500);
        setLayout(null);

        mainMenuPage = new MainMenu(this);
        mainMenuPage.setVisible(true);
        add(mainMenuPage);
        firstPage = new AddStudentPage(this);
        firstPage.setVisible(false);
        add(firstPage);

        secondPage = new ListStudentsPage(this);
        secondPage.setVisible(false);
        add(secondPage);
        connectToServer();
    }

    public MainMenu getMainMenuPage() {
        return mainMenuPage;
    }

    public void setMainMenuPage(MainMenu mainMenuPage) {
        this.mainMenuPage = mainMenuPage;
    }

    public AddStudentPage getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(AddStudentPage firstPage) {
        this.firstPage = firstPage;
    }

    public ListStudentsPage getSecondPage() {
        return secondPage;
    }

    public void addStudent(Student st){

        PackageData pd = new PackageData();
        pd.setOperationType("ADD_STUDENT");
        pd.setStudent(st);

        try{
            outStream.writeObject(pd);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Student> getStudents() {
        PackageData pd = new PackageData();
        pd.setOperationType("LIST_STUDENTS");
        ArrayList<Student> students = new ArrayList<>();
        try {
            outStream.writeObject(pd);
            pd = null;
            if ((pd = (PackageData) inStream.readObject()) != null) {
                 students = pd.getStudentList();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }

    public void connectToServer(){
        try{
            socket = new Socket("127.0.0.1",1998);
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
