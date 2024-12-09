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
    public partial class NewspaperInfo : Form
    {
        public NewspaperInfo(string path, string np_name, string subs)
        {
            InitializeComponent();
            label1.Text = np_name;
            label2.Text = subs;
            pictureBox1.Image = Image.FromFile(path);
        }
    }
}
