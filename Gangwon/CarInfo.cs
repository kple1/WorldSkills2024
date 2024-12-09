using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class CarInfo : Form
    {
        public static int cno;
        string name;
        public CarInfo(int cno)
        {
            InitializeComponent();

            object[] info = DB.carInfo(cno);
            name = info[0].ToString();

            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            carName.Text = info[0].ToString();
            carPrice.Text = info[1].ToString();
            carComment.Text = info[2].ToString();
            pictureBox2.Image = Image.FromFile($"datafiles/image/carInfo/{name}.gif");
        }

        //견적 구매
        private void button1_Click(object sender, EventArgs e)
        {
            Close();
            new Estimate(name, DB.getBytes("select c_img from car where c_name = @1", name)).Show();
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

        //시승 일정
        private void button2_Click(object sender, EventArgs e)
        {
            Close();
            int c_no = DB.getInt("select c_no from car where c_name = @1", name);
            new Calendar(0, c_no).Show();
        }
    }
}
