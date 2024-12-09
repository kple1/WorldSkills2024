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
    public partial class ChooseEstimate : UserControl
    {
        byte[] picture;
        public ChooseEstimate(string name, string price, byte[] picture)
        {
            this.picture = picture;
            InitializeComponent();
            label1.Text = name;
            label2.Text = price;

            var stream = new MemoryStream(picture);
            pictureBox1.Image = new Bitmap(stream);

            Paint += (s, e) =>
            {
                e.Graphics.DrawLine(new Pen(Color.White, 2), 0, Height, Width, Height);
            };
        }

        public byte[] GetPicture()
        {
            return picture;
        }

        public void Selected()
        {
            BackColor = Color.White;
            label1.ForeColor = Color.Black;
            label2.ForeColor = Color.Black;
        }

        public void Disable()
        {
            BackColor = Color.Black;
            label1.ForeColor = Color.White;
            label2.ForeColor = Color.White;
        }
    }
}
