package exo1;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Article> articles = new ArrayList<>();

        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://localhost:3308/Shop";
        String user = "root";
        String password = "fms";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(url, user, password)) {

            String insertSql = "INSERT INTO t_articles (Description, Brand, UnitaryPrice) VALUES (?, ?, ?)";
            try (PreparedStatement ps = c.prepareStatement(insertSql)) {
                ps.setString(1, "Ecran 27 pouces");
                ps.setString(2, "Samtech");
                ps.setDouble(3, 299.99);
                ps.executeUpdate();
            }


            String deleteSql = "DELETE FROM t_articles WHERE IdArticle = ?";
            try (PreparedStatement ps = c.prepareStatement(deleteSql)) {
                ps.setInt(1, 13); 
                ps.executeUpdate();
            }


            String selectSql = "SELECT * FROM t_articles";
            try (Statement s = c.createStatement();
                 ResultSet r = s.executeQuery(selectSql)) {

                while (r.next()) {
                    Article a = new Article(
                            r.getInt("IdArticle"),
                            r.getString("Description"),
                            r.getString("Brand"),
                            r.getDouble("UnitaryPrice")
                    );
                    articles.add(a);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Article a : articles) {
            System.out.println(
                a.getIdArticle() + " "
                + a.getDescription() + " | "
                + a.getBrand() + " = "
                + a.getUnitaryPrice() + "€"
            );
        }
    }
}
