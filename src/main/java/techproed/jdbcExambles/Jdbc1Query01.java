package techproed.jdbcExambles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;

public class Jdbc1Query01 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1) Ilgili driver'i yuklemeliyiz
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2) Baglanti olusturmaliyiz
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","hr","albarto");

		// 3) SQL komutlari icin bir Statement nesnesi olustur.
		Statement st = con.createStatement(); // SQL ifadeleri yazmak icin olusturduk
		
		// 4) SQL ifadeleri yazabiliriz. 
		//   (Personel tablosundaki personel_id'si 7369 olan personelin adini sorgulayiniz)
		
		ResultSet isim = st.executeQuery("SELECT perso_isim FROM perso WHERE perso_id=7369");
		// SQL'den veri almak icin ustteki satiri yazdik
		
		// 5) Sonuclari aldik ve isledik.
		while(isim.next()) {  // alacagimiz bilginin parametresi neyse o parametre kullanilir.
			System.out.println("Personel Adi: " + isim.getString("perso_isim"));		
			System.out.println("Personel Adi: " + isim.getString(1) + "Maas: " + isim.getInt(2));	
		}
		
		// Olusturulan nesneleri bellekten kaldiralim
		
		con.close();
		st.close();
		isim.close();
		
		
	}

}
