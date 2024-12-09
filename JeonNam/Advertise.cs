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
    public partial class Advertise : Form
    {

        Timer timer;
        int count = 5;
        public Advertise(string title, string logo, string division, string gif)
        {
            InitializeComponent();
            Name = title;
            pictureBox1.Image = Image.FromFile(logo);
            label1.Text = title;
            label2.Text = division;
            pictureBox2.Image = Image.FromFile(gif);

            timer = new Timer();
            timer.Interval = 1000;
            timer.Tick += (s, e) =>
            {
                if (count == 0)
                {
                    timer.Stop();
                    if (Login.no == 0)
                    {
                        Msg.fail("로그인을 하지 않아 적립금을 받을 수 없습니다.");
                    }
                    else
                    {
                        new Coin(1, 20, this).Show();
                    }
                }
                --count;
                label3.Text = count.ToString();
            };
            timer.Start();
        }
    }
}
