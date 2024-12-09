using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GyeongNam.Utils
{
    internal class DB
    {
        public static string url = "Server = localhost\\SQLEXPRESS; DataBase = NEWS; User Id = sa; Password = 1234; TrustServerCertificate = true";
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

        public static int GetInt(string q, params object[] o)
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

        public static string GetString(string q, params object[] o)
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
                if (sql.IsDBNull(0))
                {
                    save = "null";
                }
                else
                {
                    save = sql.GetString(0);
                }
            }
            return save;
        }

        public static List<object[]> GetRecommendNews()
        {
            var list = new List<object[]>();
            string q = "select n_no, n_title, n_date, n_count from [news]\r\norder by cast(n_count as int) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), sql.GetString(2), Convert.ToInt32(sql.GetString(3)) });
            }
            return list;
        }

        public static List<object[]> SelectToCategory(int newsType)
        {
            var list = new List<object[]>();
            string q = "select n_no, n_title, n_date, n_count from [news] where n_type = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", newsType);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), sql.GetString(2), Convert.ToInt32(sql.GetString(3)) });
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

        public static List<object[]> GetComments(string n_no)
        {
            var list = new List<object[]>();
            string q = "select u.u_name, c_date, c_detail, n_no, c_no from [comment] c\r\njoin [user] u on u.u_no = c.u_no\r\nwhere n_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", n_no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetString(0), sql.GetString(1), sql.GetString(2), sql.GetInt32(3), sql.GetInt32(4) });
            }
            return list;
        }

        public static List<object[]> GetAlim()
        {
            var list = new List<object[]>();
            string q = "select * from [alim];";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                var bookmark = sql.IsDBNull(2) ? "null" : sql.GetString(2);
                var commet = sql.IsDBNull(4) ? "null" : sql.GetString(4);
                var b_date = sql.IsDBNull(3) ? "null" : sql.GetString(3);
                var c_date = sql.IsDBNull(5) ? "null" : sql.GetString(5);
                list.Add(new object[] { sql.GetInt32(0), sql.GetInt32(1), bookmark, b_date, commet, c_date });
            }
            return list; 
        }

        public static string[] GetBookMarkInfo(string n_no)
        {
            var list = new string[] { };
            string q = "select n_title, n_date, n_count from news where n_no = @1;";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", n_no);
            var sql = cmd.ExecuteReader();
            if (sql.Read())
            {
                list = new string[] { sql.GetString(0), sql.GetString(1), sql.GetString(2) };
            }
            return list;
        }

        public static List<object[]> GetComment()
        {
            var list = new List<object[]>();
            string q = "select * from comment where u_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[]{ sql.GetInt32(0), sql.GetString(1), sql.GetInt32(2), sql.GetInt32(3), sql.GetString(4) });
            }
            return list;
        }

        public static List<object[]> GetNewsPaper()
        {
            var list = new List<object[]>();
            string q = "select * from newspaper;";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), sql.GetString(2), sql.GetString(3) });
            }
            return list;
        }
    }
}
