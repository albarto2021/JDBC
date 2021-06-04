package techproed.jdbcExambles;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc5CRUD {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
			
		String yol = "jdbc:oracle:thin:@localhost:1521/xe"; // yol adinda bir String olusturulur. Genel kullanim bu sekilde.
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
			
		Connection con = DriverManager.getConnection(yol, "hr", "albarto");
			
		Statement st = con.createStatement();
		
		/*=======================================================================
		ORNEK1: urunler adinda bir tablo olusturalim id(NUMBER(3), 
		isim VARCHAR2(10) fiyat NUMBER(7,2) 
		========================================================================*/
//		String createQuery = "CREATE TABLE urunler("
//				  			+ " id NUMBER(3),"
//				  			+ " isim VARCHAR2(10),"
//				  			+ " fiyat NUMBER(7,2))";
//		
//		st.execute(createQuery);
//		System.out.println("urunler tablosu olusturuldu..");
		
		/*=======================================================================
		ORNEK2: urunler tablosuna asagidaki kayitlari toplu bir sekilde ekleyelim
		========================================================================*/ 
		// Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir. 
		// PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir. 
		// Bunun icin; 
		// 	1) Veri girisine uygun bir POJO(Plain Old Java Object) sinifi olusturulur.
		// 	2) POJO Class nesnelerini saklayacak bir collection olusturulur // POJO basit bir java dosyasi
		// 	3) bir dongu ile kayitlar eklenir.
		// hizli ve guvenli yontem oldugundan bu yontem cok kullaniliyor
		
		List<Urun> kayitlar = new ArrayList<>();
		kayitlar.add(new Urun(101,"laptop", 6500));
		kayitlar.add(new Urun(102,"PC", 4500));
		kayitlar.add(new Urun(103,"Telefon", 4500));
		kayitlar.add(new Urun(104,"Anakart", 1500));
		kayitlar.add(new Urun(105,"Klavye", 200));
		kayitlar.add(new Urun(106,"Fare", 100));
		
		String insertQuery = "INSERT INTO urunler VALUES(?,?,?)"; // Kactane veri varsa o kadar ? kullanliri. 3 veri var 3 ?.
		
		PreparedStatement pst = con.prepareStatement(insertQuery);
	
		for(Urun each: kayitlar) {
			pst.setInt(1,each.getId());  // kayitlardan tek tek bilgileri alir //id alir
			pst.setString(2,each.getIsim());  // isim alir
			pst.setDouble(3,each.getFiyat()); // fiyat alir
			pst.addBatch(); // adBatch() yontemi hepsini gonderir
		}
		
		int [] sonuc = pst.executeBatch();
		System.out.println(sonuc.length + " kayit eklendi..");
		
		/*=======================================================================
		 ORNEK3: urunler tablosundaki PC’nin fiyatini %10 zam yapiniz
		========================================================================*/

		String updateQuery1 = "UPDATE urunler"
				 			+ " SET fiyat = fiyat * 1.1"
				 			+ " WHERE isim='PC'";

		int s1 = st.executeUpdate(updateQuery1);
		System.out.println(s1 + " satir guncellendi..");
		
		
		/*=======================================================================
		ORNEK4: urunler tablosuna 107, Monitor, 1250 sekilnde bir kayit ekleyiniz
		========================================================================*/
		/*=======================================================================
		ORNEK5: urunler tablosundaki Klavyeyi siliniz.
		========================================================================*/
		/*=======================================================================
		ORNEK6: urunler tablosuna Marka adinda ve Default degeri ASUS olan yeni 
		bir sutun ekleyiniz.
		========================================================================*/
		
		/*=======================================================================
		ORNEK7: urunler tablosundaki kayitlari sorgulayiniz.
		========================================================================*/
				
		/*=======================================================================
		ORNEK8: urunler tablosunu siliniz.
		========================================================================*/
			

	}

}
