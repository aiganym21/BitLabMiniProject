import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private int id;
    public Connection conn;

    public ClientHandler(){}

    public ClientHandler(Socket socket, int id){
        this.socket = socket;
        this.id = id;
    }

    public void run(){
        try {
            connectToDb();

            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PackageData pd = null;
            while((pd=(PackageData)inStream.readObject())!=null ){
                if(pd.getOperationType().equals("ADD_STUDENT")){
                    addStudentToDb(pd.getStudent());
                }else if(pd.getOperationType().equals("LIST_STUDENTS")){
                    PackageData response = new PackageData();
                    ArrayList<Student> students = getStudentFromDb();
                    response.setStudentList(students);
                    outputStream.writeObject(response);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addStudentToDb(Student student){
        try{
            PreparedStatement statement =
                    conn.prepareStatement("INSERT INTO student (id, name, surname, age) VALUES (NULL, ?, ?, ?) ");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getAge());
            statement.executeUpdate();
            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudentFromDb(){
        ArrayList<Student> students = new ArrayList<>();
        try{
            PreparedStatement statement =
                    conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                students.add(new Student(id,name, surname, age));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }

    public void connectToDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/jdbc_sample?useUnicode=true&serverTimezone=UTC","root", "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
