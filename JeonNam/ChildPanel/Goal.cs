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
using System.Windows.Forms.Design;

namespace JeonNam.ChildPanel
{
    public partial class Goal : UserControl
    {
        Timer timer;
        int count = 1;
        public Goal(Main main)
        {
            InitializeComponent();
            
            Dock = DockStyle.Fill;
            
            pictureBox1.Controls.Add(pictureBox2);
            pictureBox2.BackColor = Color.Transparent;
            pictureBox2.Dock = DockStyle.Fill;

            pictureBox1.Image = Image.FromFile("datafiles/메인1.png");
            pictureBox2.Image = Image.FromFile("datafiles/메인.png");

            timer = new Timer();
            timer.Interval = 2000;
            timer.Tick += (s, e) =>
            {
                if (count == 3)
                {
                    count = 1;

                }
                else
                {
                    ++count;
                }
                pictureBox1.Image = Image.FromFile($"datafiles/메인{count}.png");
            };
            timer.Start();

            for (int i = 0; i < 17; i++)
            {
                int capture = i;

                var pc = new PictureBox();
                pc.Click += (s, e) =>
                {
                    var data = DB.GetObject("select * from [goals] where No = @1", capture + 1); 

                    main.Hide();
                    if (data.Read()) new GoalInfo((capture + 1).ToString(), data.GetString(2), data.GetString(1), $"datafiles/목표/{capture + 1}.png", $"datafiles/목표/{capture + 1}.jpg", data.GetString(3), data.GetString(4)).Show();
                };
                pc.Image = Image.FromFile($"datafiles/목표/{i + 1}.png");
                pc.SizeMode = PictureBoxSizeMode.StretchImage;
                tableLayoutPanel1.Controls.Add(pc);
            }
        }
    }
}
