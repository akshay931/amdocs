import java.util.*;
import java.sql.*;

class banking {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amdocs?characterEncoding=utf8",
                    "root", "akshay");
            // Statement stmt = con.createStatement();

            System.out.println("1.Opening Bank Account\n2.Check Balance\n3.Withdrawal\n4.Deposit");
            int a = sc.nextInt();
            if (a == 1) {
                System.out.println("enter your name: ");
                String name = sc.next();
                // long theRandomNum = (long) (Math.random() * Math.pow(10, 10));
                double number = Math.floor(Math.random() * (8 * Math.pow(10, 8))) + Math.pow(10, (8));

                String query = "insert into banking (ac_number,balance,name) values(?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setDouble(1, number);
                preparedStmt.setInt(2, 0);
                preparedStmt.setString(3, name);
                preparedStmt.execute();
                con.close();
                System.out.println("your account has been opened and generated account number is " + (int) number);

            }
            if (a == 2) {
                System.out.println("enter your account number to check available balance: ");
                double number = sc.nextDouble();
                String query = "select balance from banking where ac_number = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setDouble(1, number);
                ResultSet rs = preparedStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("available balance is " + rs.getInt("balance"));
                } else {
                    System.out.println("Invalid Account number");
                }
                con.close();

            }
            if (a == 4) {
                System.out.println("enter your account number to deposit: ");
                double number = sc.nextDouble();

                System.out.println("enter amount to deposit: ");
                int deposit = sc.nextInt();
                String query = "update banking set balance = balance + ? where ac_number = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setDouble(1, deposit);
                preparedStmt.setDouble(2, number);
                int rs1 = preparedStmt.executeUpdate();

                // -------------------------------------------
                query = "select balance from banking where ac_number = ?";
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setDouble(1, number);
                ResultSet rs = preparedStmt.executeQuery();
                int total = 0;
                if (rs.next()) {
                    total = rs.getInt("balance");
                    System.out.println("total available balance after deposit is " + total);

                } else {
                    System.out.println("Invalid Account number");
                }

                con.close();

            }
            if (a == 3) {
                System.out.println("enter your account number to withdrawal: ");
                double number = sc.nextDouble();

                System.out.println("enter amount to withdrawal: ");
                int deposit = sc.nextInt();
                String query = "update banking set balance = balance - ? where ac_number = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setDouble(1, deposit);
                preparedStmt.setDouble(2, number);
                int rs1 = preparedStmt.executeUpdate();

                // -------------------------------------------
                query = "select balance from banking where ac_number = ?";
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setDouble(1, number);
                ResultSet rs = preparedStmt.executeQuery();
                int total = 0;
                if (rs.next()) {
                    total = rs.getInt("balance");
                    System.out.println("total available balance after withdrawal is " + total);
                } else {
                    System.out.println("Invalid Account number");
                }

                con.close();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}