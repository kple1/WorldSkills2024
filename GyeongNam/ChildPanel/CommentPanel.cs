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
    public partial class CommentPanel : UserControl
    {
        string name;
        public CommentPanel(string name, string ago, string comment)
        {
            InitializeComponent();
            this.name = name;

            Dock = DockStyle.Top;
            pictureBox1.Image = Image.FromFile("datafiles/icon/user.png");
            pictureBox2.Image = Image.FromFile("datafiles/icon/dot.png");
            label1.Text = name;
            label2.Text = ago;
            label3.Text = comment;
        }

        public PictureBox GetOption()
        {
            return pictureBox2;
        }

        public string GetName()
        {
            return name;
        }
    }
}