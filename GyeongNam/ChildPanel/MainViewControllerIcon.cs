using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GyeongNam.ChildPanel
{
    public partial class MainViewControllerIcon : UserControl
    {
        public MainViewControllerIcon(string path, string name)
        {
            InitializeComponent();
            Dock = DockStyle.Fill;
            pictureBox1.Image = Image.FromFile("datafiles/icon/" + path);
            label1.Text = name;
        }

        public PictureBox GetIcon()
        {
            return pictureBox1;
        }

        public void SetLine(Color color)
        {
            Paint += (s, e) =>
            {
                var g = e.Graphics;
                g.DrawLine(new Pen(color, 1), 0, 0, Width, 0);
            };
            Invalidate();
        }
    }
}
