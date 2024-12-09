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

namespace JeonNam
{
    public partial class Login : Form
    {
        public static int no = 11;
        public static string name;
        public Login()
        {
            InitializeComponent();
            Icon = FormIcon.Set();
        }

        private void textBox1_Click(object sender, EventArgs e)
        {
            label3.ForeColor = Color.Gray;
            panel2.BackColor = Color.Gray;
            label2.ForeColor = Color.RoyalBlue;
            panel1.BackColor = Color.RoyalBlue;
        }

        private void textBox2_Click(object sender, EventArgs e)
        {
            label2.ForeColor = Color.Gray;
            panel1.BackColor = Color.Gray;
            label3.ForeColor = Color.RoyalBlue;
            panel2.BackColor = Color.RoyalBlue;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (DB.GetInt("select count(*) from [user] where Id = @1", textBox1.Text) == 0)
            {
                Msg.fail("존재하지 않는 회원입니다.");
            }
            else if (DB.GetInt("select count(*) from [user] where pw = @1 and id = @2", textBox2.Text, textBox1.Text) == 0)
            {
                Msg.fail("비밀번호가 일치하지 않습니다.");
            }
            else
            {
                string nam = DB.GetString("select name from [user] where Id = @1", textBox1.Text);
                name = nam;
                Msg.ok($"{nam}({textBox1.Text})님 환영합니다.");
                new Main().Show();
                Close();
            }
        }
    }
}
