using GyeongNam.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GyeongNam
{
    public partial class FirstIdentityVerfication : Form
    {
        int clickedAgency = 0;
        public FirstIdentityVerfication()
        {
            InitializeComponent();
            string[] iconList = new string[] { "SK", "KT", "LG", "smart" };
            var pictureBoxes = new List<PictureBox>();

            bool firstLoad = true;
            CheckBox[] cBox = new CheckBox[] { checkBox2, checkBox3, checkBox4, checkBox5 };
            foreach (var cb in cBox)
            {
                cb.CheckedChanged += (s, e) =>
                {
                    if (!cb.Checked && !firstLoad)
                    {
                        checkBox1.Checked = false;
                    } 
                    else
                    {
                        firstLoad = false;
                    }
                };
            }

            for (int i = 0; i < 4; i++)
            {
                int capture = i;

                var pic = new PictureBox();
                pic.Click += (s, e) =>
                {
                    foreach (var pb in pictureBoxes)
                    {
                        pb.Paint -= PictureBox_Paint;
                        pb.Invalidate();
                    }
                    
                    pic.Paint += PictureBox_Paint;
                    pic.Invalidate();

                    clickedAgency = capture + 1;
                };

                pic.Dock = DockStyle.Fill;
                pic.SizeMode = PictureBoxSizeMode.Zoom;
                pic.Image = Image.FromFile($"datafiles/icon/{iconList[i]}.png");
                tableLayoutPanel1.Controls.Add(pic);

                pictureBoxes.Add(pic);
            }
        }
        
        void PictureBox_Paint(object sender, PaintEventArgs e)
        {
            var pic = sender as PictureBox;
            var g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
            g.DrawEllipse(new Pen(Color.Red, 2), 5, 0, pic.Width - 15, pic.Height - 1);
        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            CheckBox[] cBox = new CheckBox[] { checkBox2, checkBox3, checkBox4, checkBox5 };
            if (checkBox1.Checked)
            {
                foreach (var cb in cBox)
                {
                    cb.Checked = true;
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (!DB.isTrue("select count(*) from [user] where agency = @1", clickedAgency))
            {
                Msg.fail("통신사를 한번 더 확인해주세요.");
            }
            else if (!checkBox1.Checked)
            {
                Msg.fail("모든 항목을 동의 해주세요.");
            }
            else
            {
                Close();
                new SecondIdentityVerfication().Show();
            }
        }
    }
}
