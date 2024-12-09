using GyeongNam.ChildPanel;
using GyeongNam.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GyeongNam
{
    public partial class Subscribe : Form
    {
        public Subscribe(string state)
        {
            InitializeComponent();
            string[] png = new string[] { "경향신문", "동아일보", "매일경제", "문화일보", "조선일보", "전자신문" };
            if (state.Equals("main"))
            {
                var list = DB.GetNewsPaper();
                for (int i = 0; i < list.Count; i++)
                {
                    int capture = i;

                    string format = png.Contains(list[i][1].ToString()) ? ".png" : ".jpg";
                    var sb = new Subs($"datafiles/newspaper/{list[i][1].ToString()}{format}", list[i][1].ToString());
                    sb.GetLabel().Click += (s, e) =>
                    {
                        Close();
                        new NewspaperInfo($"datafiles/newspaper/{list[i][1].ToString()}{format}", list[i][1].ToString(), DB.GetString("select np_sub from [newspaper] where np_name = @1", list[i][1].ToString())).Show();
                    };
                    tableLayoutPanel1.Controls.Add(sb);
                }
            }
            else
            {
                Text = "구독 목록";
                var GetSubs = DB.GetString("select u_sub from [user] where u_no = @1", Login.no).Split(',');
                for (int i = 0; i < GetSubs.Length; i++)
                {
                    string np_name = DB.GetString("select np_name from [newspaper] where np_no = @1", GetSubs[i]);
                    string format = png.Contains(np_name) ? ".png" : ".jpg";
                    var sb = new Subs($"datafiles/newspaper/{np_name}{format}", np_name);
                    sb.GetLabel().Click += (s, e) =>
                    {
                        Close();
                        new NewspaperInfo($"datafiles/newspaper/{np_name}{format}", np_name, DB.GetString("select np_sub from [newspaper] where np_name = @1", np_name)).Show();
                    };
                    tableLayoutPanel1.Controls.Add(sb);
                }
            }
        }
    }
}
