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
    public partial class NewsPaperIcon : UserControl
    {
        public NewsPaperIcon(string icon, string name)
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile(icon);
            label1.Text = name;
        }
    }
}
