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
    public partial class PaymentTell : Form
    {
        Donate d;
        public PaymentTell(Donate d)
        {
            this.d = d;
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/logo/telephone.png");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string tell = textBox1.Text + "-" + textBox2.Text + "-" + textBox4.Text;
            string v = DB.GetString("select telephone from [user] where no = @1", Login.no);
            if (tell.Equals(v))
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