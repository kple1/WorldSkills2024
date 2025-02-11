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
        public bool getCoin = true;
        public News(string title, string path)
        {
            InitializeComponent();
            panel1.MouseWheel += Panel1_MouseWheel;

            Name = title;
            label1.Text = title;
            label2.Text = "뉴스";
            pictureBox1.Image = Image.FromFile(path);
        }

        private void Panel1_MouseWheel(object sender, MouseEventArgs e)
        {
            CheckScrollPosition();
        }

        private void panel1_Scroll(object sender, ScrollEventArgs e)
        {
            if (e.Type == ScrollEventType.EndScroll)
            {
                CheckScrollPosition();
            }
        }

        private void panel1_MouseWheel(object sender, MouseEventArgs e)
        {
            CheckScrollPosition();
        }

        private void CheckScrollPosition()
        {
            var verticalScroll = panel1.VerticalScroll;

            if (getCoin) 
            {
                if (verticalScroll.Value + verticalScroll.LargeChange >= verticalScroll.Maximum)
                {
                    if (Login.no == 0)
                    {
                        Util.fail("로그인을 하지 않아 적립금을 받을 수 없습니다.");
                    }
                    else
                    {
                        new Coin(1, 10, this).Show();
                        getCoin = false;
                    }
                }
            }
        }
    }
}
