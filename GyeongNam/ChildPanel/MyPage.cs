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

namespace GyeongNam.ChildPanel
{
    public partial class MyPage : UserControl
    {
        Main main;
        public MyPage(Main main)
        {
            InitializeComponent();
            Dock = DockStyle.Fill;

            this.main = main;

            pictureBox1.Image = Image.FromFile("datafiles/icon/mypage.png");
            pictureBox2.Image = Image.FromFile("datafiles/icon/nobookmark.png");
            pictureBox4.Image = Image.FromFile("datafiles/icon/comment.png");
            pictureBox3.Image = Image.FromFile("datafiles/icon/sign out.png");

            label1.Text = DB.GetString("select u_name from [user] where u_no = @1", Login.no) + " 님,";
            label2.Text = DB.GetString("select np_name from newspaper where np_no = @1", DB.GetInt("select np_no from [user] where u_no = @1", Login.no)) + " 소속";
        }

        private void pictureBox3_Click(object sender, EventArgs e)
        {
            Msg.ok("로그아웃 되었습니다.");
            main.Close();
            new Login().Show();
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            main.Close();
            new BookmarkCommentInfo("bookmark").Show();
        }

        private void pictureBox4_Click(object sender, EventArgs e)
        {
            main.Close();
            new BookmarkCommentInfo("comment").Show();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            main.Close();
            new Subscribe("sd").Show();
        }
    }
}
