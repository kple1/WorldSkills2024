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
    public partial class PaymentCard : Form
    {
        Donate d;
        public PaymentCard(Donate d)
        {
            this.d = d;
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/logo/card.png");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string num = textBox1.Text + "-" + textBox2.Text + "-" + textBox3.Text + "-" + textBox4.Text;
            if (DB.GetString("select Card from [user] where user_no = @1", Login.no).Equals(num))
            {
                new Main().Show();
            }
            else
            {
                Msg.fail("회원정보가 일치하지 않아 처음 화면으로 돌아갑니다.");
                new Main().Show();
            }
            d.Close();
            Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
