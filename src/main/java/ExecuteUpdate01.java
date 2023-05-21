import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sss230628.");
        Statement statement = connection.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.
        String sql = "update companies set number_of_employees = 16000 where number_of_employees < (select avg(number_of_employees) from companies);";
        int guncellenenSatirSayisi = statement.executeUpdate(sql);//executeUpdate() methodu guncellenen satir sayisini int deger olarak doner
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Guncelleme sonrasi datayi okumak icin DQL(select) kullaniyoruz
        String sql2 = "select * from companies";
        ResultSet resultSet2 = statement.executeQuery(sql2);
        while(resultSet2.next()){
            System.out.println(resultSet2.getObject("company_id")+ "--" + resultSet2.getObject("company")
                               + "--" + resultSet2.getObject("number_of_employees"));
        }
        connection.close();
        statement.close();
    }
}
