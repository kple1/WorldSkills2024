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
    public partial class Login : Form
    {
        public static int no = 1; //임시
        public static string name;
        public static string number;
        public Login()
        {
            InitializeComponent();
            Placeholder.Set(textBox1, "아이디를 입력해주세요.");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (!FinalVerfy.successVefy)
            {
                Msg.fail("본인인증 완료 후 로그인 해주세요.");
            }
            else
            {
                Msg.ok($"{name}님, 환영합니다.");
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBox1.Text))
            {
                Msg.fail("빈칸이 있습니다.");
            }
            else if (!DB.isTrue("select count(*) from [user] where id = @1", textBox1.Text))
            {
                Msg.fail("아이디를 확인해주세요.");
            }
            else if (DB.isTrue("select count(*) from [user] where id = @1", textBox1.Text))
            {
                Msg.ok("본인인증을 시작하겠습니다.");
                Hide();
                new FirstIdentityVerfication().Show();

                no = DB.GetInt("select u_no from [user] where id = @1", textBox1.Text);
                name = DB.GetString("select u_name from [user] where u_no = @1", no);
                number = DB.GetString("select u_number from [user] where u_no = @1", no);
            }
        }
    }
}
