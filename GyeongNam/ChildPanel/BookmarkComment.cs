using GyeongNam.Utils;
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
    public partial class BookmarkComment : UserControl
    {
        public BookmarkComment(string title, string path, string ago, string count)
        {
            InitializeComponent();
            Dock = DockStyle.Fill;

            pictureBox1.Image = Image.FromFile(path);

            label1.Text = title;
            label2.Text = "중앙일보";
            label3.Text = ago;
            label4.Text = count;
        }
    }
}
