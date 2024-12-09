using JeonNam.Utils;
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
    public partial class GoalSelectPanel : UserControl
    {
        public GoalSelectPanel(string path, string kor, int maxSize, int size)
        {
            InitializeComponent();
            Dock = DockStyle.Top;

            var obj = new Bitmap(path);
            var color = obj.GetPixel(1, 1);

            pictureBox1.Image = Image.FromFile(path);
            label1.Text = kor;
            label1.BackColor = color;
            button1.BackColor = color;

            double length = label1.Width * ((double)size / maxSize);
            if (length == 0)
            {
                label1.Anchor = AnchorStyles.None;
            }
            label1.Width = Convert.ToInt32(length);
        }

        public Label GetLabel()
        {
            return label1;
        }

        public Button GetButton() {
            return button1;
        }
    }
}
