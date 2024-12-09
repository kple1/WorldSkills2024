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
    public partial class NewsInfo : Form
    {
        int no;
        bool bookMarkToggle = true;
        public NewsInfo(string np_icon, string np_name, string ago, string n_detail, string view_path)
        {
            InitializeComponent();
            tableLayoutPanel1.Paint += (s, e) =>
            {
                var g = e.Graphics;
                g.DrawLine(new Pen(Color.IndianRed), 0, 0, tableLayoutPanel1.Width, 0);
            };

            no = DB.GetInt("select n_no from news where n_detail = @1", n_detail);
            var comment = new NewsViewControllerIcon("datafiles/icon/comment.png", $"{DB.GetInt("select count(n_no) from comment where n_no = @1", no)}");
            comment.GetPictureBox().Click += (s, e) =>
            {
                view.Visible = false;
                var panel = new Comment(no.ToString());
                panel.GetExitButton().Click += (s1, e1) =>
                {
                    panel.Visible = false;
                    view.Visible = true;
                };

                Controls.Add(panel);
                new Comment(no.ToString()).Show();
            };
            tableLayoutPanel1.Controls.Add(comment);

            var bookMark = new NewsViewControllerIcon("datafiles/icon/nobookmark.png", "북마크");
            bookMark.GetPictureBox().Click += (s, e) =>
            {
                tableLayoutPanel1.Controls.RemoveAt(1);
                if (bookMarkToggle)
                {
                    bookMarkToggle = false;
                    bookMark = new NewsViewControllerIcon("datafiles/icon/bookmark2.png", "북마크");
                    Msg.fail("켜짐");
                    //DB
                }
                else
                {
                    bookMarkToggle = true;
                    bookMark = new NewsViewControllerIcon("datafiles/icon/nobookmark.png", "북마크");
                    Msg.fail("꺼짐");
                    //DB
                }
                tableLayoutPanel1.Controls.Add(bookMark);
                tableLayoutPanel1.Invalidate();
            };
            tableLayoutPanel1.Controls.Add(bookMark);

            pictureBox1.Image = Image.FromFile(np_icon);
            label1.Text = np_name;
            label2.Text = ago + "일 전";
            label3.Text = n_detail;
            pictureBox2.Image = Image.FromFile(view_path);
        }

        bool toggleSub = true;
        private void button1_Click(object sender, EventArgs e)
        {
            var addSub = "";
            var getSub = DB.GetString("select u_sub from [user] where u_no = @1", Login.no);
            if (toggleSub)
            {
                toggleSub = false;
                button1.BackColor = Color.DarkGray;
                button1.Text = "구독 취소";
                if (getSub.Equals("null"))
                {
                    addSub = no.ToString();
                }
                else
                {
                    addSub = getSub + $",{no.ToString()}";
                }
                DB.DML("update [user] set u_sub = @1 where u_no = @2", addSub, Login.no);
            }
            else
            {
                toggleSub = true;
                button1.BackColor = Color.IndianRed;
                button1.Text = "구독";

                var result = new List<string>();
                string[] list = getSub.Split(',');
                for (int i = 0; i < list.Length; i++)
                {
                    if (!list[i].Equals(no.ToString())) {
                        result.Add(list[i]);
                    }
                }
                DB.DML("update [user] set u_sub = @1 where u_no = @2", string.Join(",", result), Login.no);
            }
        }
    }
}
