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
    public partial class News : Form
    {
        public News(string title, string path)
        {
            InitializeComponent();
            panel1.AutoScroll = false;
            panel1.HorizontalScroll.Enabled = false;
            panel1.AutoScroll = true;

            Name = title;
            label1.Text = title;
            label2.Text = "뉴스";
            pictureBox1.Image = Image.FromFile(path);
        }

        private void panel1_Scroll(object sender, ScrollEventArgs e)
        {
            var verticalScroll = panel1.VerticalScroll;
            if (verticalScroll.Value + verticalScroll.LargeChange >= verticalScroll.Maximum)
            {
                if (Login.no == 0)
                {
                    Msg.fail("로그인을 하지 않아 적립금을 받을 수 없습니다.");
                }
                else
                {
                    new Coin(1, 10, this).Show();
                }
            }
        }
    }
}
