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
    public partial class NewsViewControllerIcon : UserControl
    {
        public NewsViewControllerIcon(string icon, string comment)
        {
            InitializeComponent();
            Dock = DockStyle.Fill;
            pictureBox1.Image = Image.FromFile(icon);
            label1.Text = comment;
        }

        public PictureBox GetPictureBox()
        {
            return pictureBox1;
        }
    }
}
