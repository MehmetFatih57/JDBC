import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sss230628.");
        Statement statement = connection.createStatement();

        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "select country_name from countries where region_id=1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);

        //Datayi cagirip okumak icin executeQuery methodunu kullanmaliyiz. execute() methodu sadece true ya da false doner
        ResultSet resultSet1 = statement.executeQuery(sql1);
        while(resultSet1.next()){
            System.out.println(resultSet1.getString(1));
        }

        System.out.println("Ornek 1");
        System.out.println();

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "select country_name , country_id from countries where region_id >2; ";

        ResultSet resultSet2 = statement.executeQuery(sql2);
        while(resultSet2.next()){
            System.out.println(resultSet2.getString(1) + "--" + resultSet2.getString(2));
        }

        System.out.println("Ornek 2");
        System.out.println();

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "select * from companies where number_of_employees=(select min(number_of_employees) from companies);";

        ResultSet resultSet3 = statement.executeQuery(sql3);
        while(resultSet3.next()){
            System.out.println(resultSet3.getString(1) + "--" + resultSet3.getString(2) + "--" + resultSet3.getString(3));
        }

        System.out.println("Ornek 3");
        System.out.println();

        connection.close();
        statement.close();
    }
}
