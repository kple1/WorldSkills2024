using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JeonNam.Utils
{
    internal class DB
    {
        public static string url = "Server = localhost\\SQLEXPRESS; DataBase = global_goals; User Id = sa; password = 1234; TrustServerCertificate = true";
   
        public static string GetString(string q, params object[] o)
        {
            var save = "";
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

        public static int GetInt(string q, params object[] o)
        {
            var save = 0;
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

        public static SqlDataReader GetObject(string q, params object[] o)
        {
            var conn = new SqlConnection(url);
            conn.Open();

            var cmd = new SqlCommand(q, conn);
            for (int i = 0; i < o.Length; i++) cmd.Parameters.AddWithValue($"@{i + 1}", o[i]);
            return cmd.ExecuteReader();
        }

        public static List<int[]> HowManyDonate()
        {
            var list = new List<int[]>();
            string q = "select goal_no, count(Goal_no) from [donate] where User_no = @1\r\ngroup by goal_no\r\nunion all\r\nselect distinct goal_no, 0 from [donate]\r\nwhere goal_no not in (select goal_no from [donate] where User_no = @2)\r\norder by goal_no asc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            cmd.Parameters.AddWithValue("@2", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new int[] { sql.GetInt32(0), sql.GetInt32(1) });
            }
            return list;
        }

        public static List<object[]> GetUpdates()
        {
            var list = new List<object[]>();
            string q = "select * from [updates]";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetString(1), sql.GetString(2) });
            }
            return list;
        }

        public static List<string> GetAllGiveRank()
        {
            var list = new List<string>();
            string q = "select top 3 g.Kor from [donate] d\r\njoin [goals] g on g.no = d.Goal_no\r\ngroup by goal_no, g.Kor\r\norder by count(Goal_no) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(sql.GetString(0));
            }
            return list;
        }

        public static List<string> GetMyGiveRank()
        {
            var list = new List<string>();
            string q = "select top 3 g.Kor from [donate] d\r\njoin [goals] g on g.no = d.Goal_no\r\nwhere d.User_no = @1\r\ngroup by goal_no, g.Kor\r\norder by count(Goal_no) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(sql.GetString(0));
            }
            return list;
        }
        
        public static List<object[]> GetMonthGiveRank()
        {
            var list = new List<object[]>();
            string q = "select top 4 u.Name, sum(Totalamount) from [donate] d\r\njoin [user] u on d.User_no = u.No\r\nwhere d.Date between '2024-08-01' and '2024-08-31'\r\ngroup by u.Name\r\norder by sum(Totalamount) desc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetString(0), sql.GetDecimal(1) });
            }
            return list;
        }

        public static List<object[]> GetSummaryMoneyGiveRank()
        {
            List<object[]> list = new List<object[]>();
            string q = "select Goal_no, sum(Totalamount) from [donate]\r\ngroup by goal_no";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetDecimal(1) });
            }
            return list;
        }

        public static List<object[]> GetUserSummaryMoneyGiveRank()
        {
            List<object[]> list = new List<object[]>();
            string q = "select Goal_no, sum(Totalamount) from [donate]\r\nwhere User_no = 11\r\ngroup by goal_no\r\nunion all\r\nselect distinct Goal_no, 0 from [donate]\r\nwhere Goal_no not in (select Goal_no from [donate] where User_no = 11)\r\norder by goal_no asc";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetDecimal(1) });
            }
            return list;
        }

        public static List<object[]> GetNowMonthGiveList()
        {
            List<object[]> list = new List<object[]>();
            string q = "select Goal_no, Date, TotalAmount from [donate] where User_no = @1 and date between '2024-08-01' and '2024-08-31'";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetDateTime(1).ToString("yyyy-MM-dd"), sql.GetDecimal(2) });
            }
            return list;
        }

        public static List<object[]> GetAllGiveList()
        {
            List<object[]> list = new List<object[]>();
            string q = "select Goal_no, Date, TotalAmount from [donate] where User_no = @1";
            var conn = new SqlConnection(url);
            conn.Open();
            var cmd = new SqlCommand(q, conn);
            cmd.Parameters.AddWithValue("@1", Login.no);
            var sql = cmd.ExecuteReader();
            while (sql.Read())
            {
                list.Add(new object[] { sql.GetInt32(0), sql.GetDateTime(1).ToString("yyyy-MM-dd"), sql.GetDecimal(2) });
            }
            return list;
        }
    }
}
