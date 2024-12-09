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
    public partial class MenuIcon : UserControl
    {
        public MenuIcon(string name, string path)
        {
            InitializeComponent();

            Anchor = (AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Right | AnchorStyles.Left);

            pictureBox1.Image = Image.FromFile(path);
            label1.Text = name;
        }

        public PictureBox GetPictureBox()
        {
            return pictureBox1;
        }
    }
}
