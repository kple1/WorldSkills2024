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
    public partial class Donate : Form
    {
        public Donate(string btName)
        {
            InitializeComponent();
            button4.Text = btName;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new SM("적립").Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            new SM("기부").Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (button4.Text.Equals("기부하기"))
            {
                if (FindNumber.GetPrice(one.Text) + FindNumber.GetPrice(two.Text) == 0)
                {
                    Msg.fail("기부할 금액을 입력해주세요.");
                }
                else if (DB.GetString("select Card from [user] where no = @1", Login.no).Equals("null"))
                {
                    new PaymentTell(this).Show();
                }
                else
                {
                    new PaymentCard(this).Show();
                }
            }
        }
    }
}
