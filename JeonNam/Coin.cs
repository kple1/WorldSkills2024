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
    public partial class Coin : Form
    {
        Form f;
        int max;
        public Coin(int min, int max, Form f)
        {
            InitializeComponent();
            this.f = f;
            this.max = max;

            pictureBox1.Image = Image.FromFile("datafiles/Coin.gif");

            var r = new Random();
            label1.Text = $"{r.Next(min, max)}원이\n적립됩니다.";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (max == 20)
            {
                f.Close();
            }
            Close();
        }
    }
}
