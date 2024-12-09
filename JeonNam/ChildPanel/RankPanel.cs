using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JeonNam.ChildPanel
{
    public partial class RankPanel : UserControl
    {
        public RankPanel(string path, int max, int length)
        {
            InitializeComponent();
            Dock = DockStyle.Top;
            pictureBox1.Image = Image.FromFile(path);

            var bit = new Bitmap(path);
            var color = bit.GetPixel(1, 1);
            panel1.BackColor = color;

            float size = 0;
            if (length == 0)
            {
                size = 0;
            }
            else
            {
                size = panel1.Width * ((float)length / max);
            }
            panel1.Width = Convert.ToInt32(size);
        }
    }
}
