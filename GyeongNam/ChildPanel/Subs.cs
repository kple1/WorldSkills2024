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
    public partial class Subs : UserControl
    {
        public Subs(string path, string newspapername)
        {
            InitializeComponent();
            Dock = DockStyle.Top;
            pictureBox1.Image = Image.FromFile(path);
            label1.Text = newspapername;
        }

        public Label GetLabel()
        {
            return label1;
        }
    }
}
