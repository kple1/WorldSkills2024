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
    public partial class SecondIdentityVerfication : Form
    {

        public static int verfyCode;
        Random random = new Random();
        int randomVerfyCode;
        public SecondIdentityVerfication()
        {
            InitializeComponent();

            Placeholder.Set(textBox1, "성명입력");
            Placeholder.Set(textBox2, "●●●●●●");
            Placeholder.Set(textBox3, "숫자만 입력");
            textBox4.Text = GetVerfyCode().ToString();
            Placeholder.Set(textBox5, "보안문자 입력");

            pictureBox1.Image = Image.FromFile("datafiles/icon/reset.png");
        }

        int GetVerfyCode()
        {
            return randomVerfyCode = random.Next(100000, 999999);
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            Placeholder.Set(textBox4, GetVerfyCode().ToString());
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (!textBox1.Text.Equals(Login.name))
            {
                Msg.fail("성명을 확인해주세요.");
            }
            else if (!textBox3.Text.Equals(Login.number.Replace("-", "").Trim()))
            {
                Msg.fail("전화번호를 확인해주세요.");
            }
            else if (!textBox5.Text.Equals(textBox4.Text)) 
            {
                Msg.fail("보안인증 실패");
            }
            else
            {
                verfyCode = random.Next(100000, 999999);
                Msg.ok($"인증번호 {verfyCode}을 입력해주세요.");
                Close();
                new FinalVerfy().Show();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
            new FirstIdentityVerfication().Show();
        }
    }
}
