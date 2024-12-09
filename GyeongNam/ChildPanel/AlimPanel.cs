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
    public partial class AlimPanel : UserControl
    {
        public AlimPanel(string comment, string ago)
        {
            InitializeComponent();
            Dock = DockStyle.Top;
            label1.Text = comment;
            label2.Text = ago;
        }

        public void SetColor(Color color)
        {
            label1.ForeColor = color;
        }

        public Label GetComment()
        {
            return label1;
        }
    }
}
