using JeonNam.Utils;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace JeonNam
{
    public partial class SM : Form
    {
        string formName;
        public SM(string formName)
        {
            InitializeComponent();
            this.formName = formName;
            pictureBox1.Image = Image.FromFile("datafiles/logo/donate1.png");

            if (formName.Equals("적립"))
            {
                Text = "적립";
            }
            else
            {
                Text = "기부";
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            int money = DB.GetInt("select point from [User] where No = @1", Login.no);
            if (formName.Equals("적립"))
            {
                if (money < Convert.ToInt32(textBox1.Text))
                {
                    Msg.fail("소유하고 계신 적립금보다 많습니다.");
                    textBox1.Text = "";
                }
                else
                {
                    Donate.one.Text = $"\\ {int.Parse(textBox1.Text):N0}";
                    Donate.result.Text = $"\\ {FindNumber.GetPrice(Donate.one.Text) + FindNumber.GetPrice(Donate.two.Text):N0}";
                    Close();
                }
            }
            else
            {
                if (Convert.ToInt32(textBox1.Text) > 1_000_000)
                {
                    Msg.fail("1,000,000(백만원)을 초과할 수 없습니다.");
                    textBox1.Text = "";
                }
                else
                {
                    Donate.two.Text = $"\\ {int.Parse(textBox1.Text):N0}";
                    Donate.result.Text = $"\\ {FindNumber.GetPrice(Donate.one.Text) + FindNumber.GetPrice(Donate.two.Text):N0}";
                    Close();
                }
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
