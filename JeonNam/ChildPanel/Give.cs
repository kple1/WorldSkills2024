using JeonNam.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JeonNam.ChildPanel
{
    public partial class Give : UserControl
    {
        Main main;
        public Give(Main main)
        {
            this.main = main;
            InitializeComponent();
            if (Login.no == 0)
            {
                label13.Visible = true;
                label1.Text = "\\ -";
                label3.Text = "0";
                label5.Text = "\\ -";
            }
            else
            {
                label13.Visible = false;
            }
            Dock = DockStyle.Fill;

            pictureBox1.Image = Image.FromFile("datafiles/설정1.png");
            pictureBox2.Image = Image.FromFile("datafiles/메인.png");

            pictureBox1.Controls.Add(pictureBox2);
            pictureBox2.Dock = DockStyle.Fill;

            tableLayoutPanel5.Controls.Add(tableLayoutPanel6);
            tableLayoutPanel5.SetColumnSpan(tableLayoutPanel6, 2);
            tableLayoutPanel5.Controls.Add(panel2);
            tableLayoutPanel5.Controls.Add(panel3);

            var data = DB.GetObject("select point, count(distinct Goal_no), sum(Totalamount) from [donate] d\r\njoin [user] u on u.no = d.User_no\r\nwhere user_no = @1\r\ngroup by point", Login.no);
            if (data.Read())
            {
                label1.Text = $"\\ {Convert.ToInt32(data[0]):N0}";
                label3.Text = $"{data[1]}";
                label5.Text = $"\\ {Convert.ToInt32(data[2]):N0}";
            }

            var list1 = DB.GetAllGiveRank();
            var list2 = DB.GetMyGiveRank();
            for (int i = 0; i < 3; i++)
            {
                var label = new Label();
                label.Text = $"{i + 1}. {list1[i]}";
                label.Font = new Font("굴림", 12);
                label.AutoSize = false;
                label.Dock = DockStyle.Fill;
                tableLayoutPanel2.Controls.Add(label);

                var label1 = new Label();
                label1.Text = $"{i + 1}. {list2[i]}";
                label1.Font = new Font("굴림", 12);
                label1.Dock = DockStyle.Fill;
                tableLayoutPanel3.Controls.Add(label1);
            }

            var list3 = DB.GetMonthGiveRank();
            for (int i = 0; i < list3.Count; i++)
            {
                MonthGiveRankPanel panel;
                try
                {
                    panel = new MonthGiveRankPanel($"datafiles/프로필/{DB.GetInt("select no from [user] where name = @1", list3[i][0])}.jpg", list3[i][0].ToString(), $"\\ {Convert.ToInt32(list3[i][1].ToString().Substring(0, list3[i][1].ToString().Length - 5)):N0}");
                }
                catch
                {
                    panel = new MonthGiveRankPanel("none", list3[i][0].ToString(), $"\\ {Convert.ToInt32(list3[i][1].ToString().Substring(0, list3[i][1].ToString().Length - 5)):N0}");
                }
                tableLayoutPanel4.Controls.Add(panel);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            main.Close();
            new Chart().Show();
        }
    }
}
