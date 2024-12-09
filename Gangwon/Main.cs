using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class Main : Form
    {
        public Main()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            pictureBox2.Image = Image.FromFile("datafiles/image/MainGif.gif");
            list = DB.MainFormGetCarInfo(1);
            Setting(list);
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
            Application.Exit();
        }


        object[] list;
        int count = 1;
        //right
        private void label7_Click(object sender, EventArgs e)
        {
            if (count + 1 != 7) ++count;
            list = DB.MainFormGetCarInfo(count);
            Setting(list);
        }

        private void label8_Click(object sender, EventArgs e)
        {
            if (count - 1 != 0) --count;
            list = DB.MainFormGetCarInfo(count);
            Setting(list);
        }

        void Setting(object[] list)
        {
            brandName.Text = list[0].ToString() + " 최다 시승일정 차";
            
            var stream = new MemoryStream((byte[])list[1]);
            carImage.Image = new Bitmap(stream);

            totalDrive.Text = " 총 시승 일정: " + list[2].ToString();
            carName.Text = list[3].ToString();
            countDrive.Text = list[4].ToString() + "회";

            var brandLogo = new MemoryStream((byte[])list[5]);
            brandImage.Image = new Bitmap(brandLogo);

            Invalidate();
        }

        private void label4_Click(object sender, EventArgs e)
        {
            Close();
            new CarSearch(0).Show();
        }

        private void label5_Click(object sender, EventArgs e)
        {
            Close();
            new MyPage().Show();
        }

        private void label3_Click(object sender, EventArgs e)
        {
            Close();
            new CarRank().Show();
        }

        private void label6_Click(object sender, EventArgs e)
        {
            Close();
            new Brand().Show();
        }
    }
}
