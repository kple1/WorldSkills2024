using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon.ChildPanel
{
    public partial class DayPanel : UserControl
    {
        public DayPanel(Color dayBack, Color back, int day)
        {
            InitializeComponent();
            Dock = DockStyle.Fill;
            label1.BackColor = dayBack;
            BackColor = back;
            label1.Text = day.ToString();
        }

        public PictureBox GetPictureBox()
        {
            return pictureBox1;
        }
    }
}
