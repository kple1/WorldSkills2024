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

namespace Gangwon.ChildPanel
{
    public partial class CarInformationPanel : UserControl
    {
        MemoryStream ms;
        CarSearch cs;
        string carName;
        public CarInformationPanel(byte[] logo, byte[] carImage, string carName, string carDivision, string carType, CarSearch carSearch)
        {
            cs = carSearch;
            this.carName = carName;
            InitializeComponent();
            Dock = DockStyle.Fill;
            pictureBox1.Controls.Add(pictureBox2);

            ms = new MemoryStream(logo);
            pictureBox2.Image = new Bitmap(ms);

            ms = new MemoryStream(carImage);
            pictureBox1.Image = new Bitmap(ms);

            label1.Text = carName;
            label2.Text = carDivision + ", " + carType;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            cs.Close();
            int c_no = DB.getInt("select c_no from car where c_name = @1", carName);
            new CarInfo(c_no).Show();
        }
    }
}