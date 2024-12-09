using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class Login : Form
    {
        public static string name;
        public static int no;
        public Login()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            pictureBox2.Image = Image.FromFile("datafiles/image/main.png");
            Animation.ChangeBackgroundColor(button1);
            Animation.ChangeBackgroundColor(button2);
            Animation.ChangeBackgroundColor(button4);
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }

        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        int width = 317;

        private void button2_Click(object sender, EventArgs e)
        {
            //panel2 max 671, 317

            timer1.Interval = 10;
            timer2.Interval = 10;

            if (button2.Text.Equals("비밀번호 찾기"))
            {
                button2.Text = "닫기";
                label1.Text = "비밀번호 찾기";

                if (timer2.Enabled) timer2.Stop(); 
                timer1.Tick += (s1, e1) =>
                {
                    if (width + 2 <= 671) width += 2; else timer1.Stop();
                    panel2.Size = new Size(width, 224);
                };
                timer1.Start();
            }
            else
            {
                button2.Text = "비밀번호 찾기";
                label1.Text = "로그인";

                if (timer1.Enabled) timer1.Stop();
                timer2.Tick += (s1, e1) =>
                {
                    if (width - 2 >= 317) width -= 2; else timer2.Stop();
                    panel2.Size = new Size(width, 224);
                };
                timer2.Start();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBox1.Text) || string.IsNullOrEmpty(textBox2.Text))
            {
                Msg.fail("빈칸이 있습니다.");
            }
            else if (textBox1.Text.Equals("admin") && textBox2.Text.Equals("1234"))
            {
                Msg.ok("관리자님 환영합니다.");
                Hide();
                //Show Admin Form
            }
            else if (!DB.isTrue("select count(*) from [user] where u_id = @1 and u_pw = @2", textBox1.Text, textBox2.Text))
            {
                Msg.fail("아이디 또는 비밀번호가 일치하지 않습니다.");
            }
            else
            {
                name = DB.getString("select u_name from [user] where u_id = @1", textBox1.Text);
                no = DB.getInt("select u_no from [user] where  u_id = @1", textBox1.Text);
                Msg.ok($"{name}님 환영합니다.");
                Hide();
                new Main().Show();
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBox7.Text) || string.IsNullOrEmpty(textBox8.Text) || string.IsNullOrEmpty(textBox9.Text) || string.IsNullOrEmpty(textBox10.Text))
            {
                Msg.fail("빈칸이 있습니다.");
            }
            else if (!DB.isTrue("select count(*) from [user] where u_id = @1 and u_name = @2 and u_phone = @3 and u_email = @4",textBox10.Text, textBox9.Text, textBox8.Text, textBox7.Text))
            {
                Msg.fail("일치하는 회원정보가 없습니다.");
            }
            else
            {
                string pw = DB.getString("select u_pw from [user] where u_id = @1", textBox7.Text);
                Msg.ok($"{name}님의 비밀번호는 : '{pw}'");
            }
        }
    }
}
