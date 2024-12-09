using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Reflection;
using System.Security.Cryptography;

namespace Gangwon.Utils
{
    internal class DB
    {
        public static string url = "Server = localhost\\SQLEXPRESS; Database = skillCar; User id = sa; password = 1234; TrustServerCertificate = true";
        public static bool isTrue(string q, params object[] o)
        {
            bool save = false;
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++)
            {
                cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            }

            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                save = Convert.ToBoolean(sql.GetInt32(0));
            }
            return save;
        }

        public static string getString(string q, params object[] o)
        {
            string save = "";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++)
            {
                cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            }

            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                save = sql.GetString(0);
            }
            return save;
        }

        public static int getInt(string q, params object[] o)
        {
            int save = 0;
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++)
            {
                cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            }

            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                save = sql.GetInt32(0);
            }
            return save;
        }

        public static long getBigInt(string q, params object[] o)
        {
            long save = 0;
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++)
            {
                cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            }

            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                save = sql.GetInt64(0);
            }
            return save;
        }

        public static byte[] getBytes(string q, params object[] o)
        {
            byte[] save = null;
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++)
            {
                cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            }

            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                save = (byte[])sql[0];
            }
            return save;
        }

        public static object[] MainFormGetCarInfo(int brandNumber)
        {
            object[] list = new object[] { };
            string q = "select top 1 b.b_name, c.c_img, sum(d.c_no), c.c_name, count(d.c_no) as number, b.b_img, d.c_no\r\nfrom [drive] d\r\njoin car c on d.c_no = c.c_no\r\njoin brand b on b.b_no = c.b_no\r\nwhere b.b_no = @1\r\ngroup by d.c_no, b.b_name, c.c_name, c.c_img, b.b_img\r\norder by count(d.c_no) desc";
            using (var conn = new SqlConnection(url))
            {
                conn.Open();
                var cmd = new SqlCommand(q, conn);
                cmd.Parameters.AddWithValue("@1", brandNumber);
                var sql = cmd.ExecuteReader();
                if (sql.Read())
                {
                    list = new object[] { sql.GetString(0), (byte[])sql[1], sql.GetInt32(2), sql.GetString(3), sql.GetInt32(4), (byte[])sql[5], sql.GetInt32(6) };
                }
            }
            return list;
        }

        public static List<object[]> getCarInformation()
        {
            List<object[]> list = new List<object[]>();
            string q = "select b.b_img, c.c_img, c.c_name, case when c.c_division = 1 then '일반' when c.c_division = 2 then '전기' else '하이브리드' end, case when c.c_type = 1 then '세단' else 'SUV' end, b.b_no from [car] c join brand b on b.b_no = c.b_no";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { (byte[])sql[0], (byte[])sql[1], sql.GetString(2), sql.GetString(3), sql.GetString(4), sql.GetInt32(5) });
            }
            return list;
        }

        public static List<object[]> GetUserDrive(int loginNo)
        {
            List<object[]> list = new List<object[]>();
            string q = "select c.c_img, c.c_name, b.b_img, d.d_date  from [drive] d\r\njoin [user] u on d.u_no = u.u_no\r\njoin car c on c.c_no = d.c_no\r\njoin brand b on b.b_no = c.b_no\r\nwhere d.u_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", loginNo);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { (byte[])sql[0], sql.GetString(1), (byte[])sql[2], sql.GetDateTime(3).ToString("yyyy-MM-dd") });
            }
            return list;
        }

        public static List<object[]> UserPurchaseList(int loginNo)
        {
            List<object[]> list = new List<object[]>();
            string q = "SELECT \r\n    c.c_name, \r\n    c.c_img,\r\n    (SELECT o1.o_img FROM [option] o1 WHERE o1.o_no = p.option1) AS p1,\r\n    (SELECT o2.o_img FROM [option] o2 WHERE o2.o_no = p.option2 + 4) AS p2,\r\n    (SELECT o3.o_img FROM [option] o3 WHERE o3.o_no = p.option3 + 7) AS p3,\r\n    (SELECT o4.o_img FROM [option] o4 WHERE o4.o_no = p.option4 + 9) AS p4,\r\n    p.p_price,\r\n    p.p_date\r\nFROM [purchase] p\r\nJOIN car c ON c.c_no = p.c_no\r\nWHERE p.u_no = @1;";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", loginNo);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetString(0), (byte[])sql[1], (byte[])sql[2], (byte[])sql[3], (byte[])sql[4], (byte[])sql[5], sql.GetInt64(6), sql.GetDateTime(7).ToString("yyyy-MM-dd") });
            }
            return list;
        }

        public static void DML(string q, params object[] o)
        {
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++)
            {
                cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            }
            cmd.ExecuteNonQuery();
        }

        public static List<object[]> driveDate(int uno)
        {
            string q = "select u_no, d_date from [drive] where u_no != @1";
            List<object[]> list = new List<object[]>();
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", uno);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetDateTime(1) });
            }
            return list;
        }

        public static List<object[]> UserdriveDate(int uno)
        {
            string q = "select u_no, d_date from [drive] where u_no = @1";
            List<object[]> list = new List<object[]>();
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", uno);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetDateTime(1) });
            }
            return list;
        }

        public static List<object[]> Drive()
        {
            string q = "select u_no, c.c_no, c.c_img, d_date from drive d\r\njoin car c on c.c_no = d.d_no\r\nwhere u_no = 19";
            List<object[]> list = new List<object[]>();
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetInt32(1), (byte[])sql[2], sql.GetDateTime(3) });
            }
            return list;
        }

        public static List<object[]> options()
        {
            var list = new List<object[]>();
            string q = "select * from [option]";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), sql.GetInt32(2), sql.GetInt32(3), (byte[])sql[4] });
            }
            return list;
        }

        public static List<object[]> GetCarRankBrand(int b_no)
        {
            var list = new List<object[]>();
            string q = "select top 5 count(p.c_no), c.c_name, c.c_img from purchase p\r\njoin car c on c.c_no = p.c_no\r\njoin brand b on b.b_no = c.b_no\r\nwhere b.b_no = @1\r\ngroup by c.c_no, c.c_name, c.c_img\r\norder by count(p.c_no) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", b_no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), (byte[])sql[2] });
            }
            return list;
        }

        public static List<object[]> GetCarRankDivision(int division)
        {
            var list = new List<object[]>();
            string q = "select top 5 count(p.c_no), c.c_name, c.c_img from purchase p\r\njoin car c on c.c_no = p.c_no\r\njoin brand b on b.b_no = c.b_no\r\nwhere c.c_division= @1\r\ngroup by c.c_no, c.c_name, c.c_img\r\norder by count(p.c_no) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", division);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), (byte[])sql[2] });
            }
            return list;
        }

        public static List<object[]> GetCarRankType(int type)
        {
            var list = new List<object[]>();
            string q = "select top 5 count(p.c_no), c.c_name, c.c_img from purchase p\r\njoin car c on c.c_no = p.c_no\r\njoin brand b on b.b_no = c.b_no\r\nwhere c.c_type = @1\r\ngroup by c.c_no, c.c_name, c.c_img\r\norder by count(p.c_no) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", type);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), (byte[])sql[2] });
            }
            return list;
        }

        public static List<object[]> GetUserPurchaseRank()
        {
            List<object[]> list = new List<object[]>();
            string q = "select u.u_name, count(p.p_price), sum(p.p_price), u.u_email, u.u_phone from purchase p\r\njoin [user] u on u.u_no = p.u_no\r\ngroup by u.u_name, u.u_email, u.u_phone\r\norder by sum(p.p_price) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetString(0), sql.GetInt32(1), sql.GetInt64(2), sql.GetString(3), sql.GetString(4) });
            }
            return list;
        }

        public static List<object[]> GetBrandSellRank()
        {
            List<object[]> list = new List<object[]>();
            string q = "select b.b_name, b.b_img, count(p.c_no), sum(p.p_price) from purchase p\r\njoin car c on c.c_no = p.c_no\r\njoin brand b on b.b_no = c.b_no\r\ngroup by b.b_name, b.b_img, b.b_no\r\norder by sum(p.p_price) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetString(0), (byte[])sql[1], sql.GetInt32(2), sql.GetInt64(3) });
            }
            return list;
        }

        public static object[] GetBrands(int index)
        {
            object[] list = new object[] { };
            string q = "select b_name, b_vision, b_info, b_country, b_date, b_no from brand where b_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", index);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list = new object[] { sql.GetString(0), sql.GetString(1), sql.GetString(2), sql.GetString(3), sql.GetDateTime(4).ToString("yyyy-MM-dd"), sql.GetInt32(5) };
            }
            return list;
        } 

        public static List<byte[]> RandomImage(int bno)
        {
            List<byte[]> bytes = new List<byte[]>();
            string q = "select c_img, c_no, c_name from car where b_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", bno);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                bytes.Add((byte[])sql[0]);
            }
            return bytes;
        }

        public static object[] result(List<byte[]> bytes, int bno)
        {
            object[] list = new object[] { };

            string q = "select c_img, c_no, c_name from car where b_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", bno);
            var sql = cmd.ExecuteReader();
            var r = new Random();
            if (sql.Read())
            {
                int random = r.Next(0, bytes.Count);
                list = new object[] { bytes[random], sql.GetString(2) };
            }
            return list;
        }

        public static object[] carInfo(int cno)
        {
            object[] list = new object[] { };

            string q = "select c_name, c_price, c_explain from car where c_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", cno);
            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                list = new object[] { sql.GetString(0), sql.GetInt64(1), sql.GetString(2) };
            }
            return list;
        }
    }
}
