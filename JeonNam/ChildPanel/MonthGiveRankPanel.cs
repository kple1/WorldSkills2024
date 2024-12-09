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
    public partial class MonthGiveRankPanel : UserControl
    {
        public MonthGiveRankPanel(string profile, string name, string totalPrice)
        {
            InitializeComponent();
            if (profile.Equals("none"))
            {
                pictureBox1.BackColor = Color.Gray;
            }
            else
            {
                pictureBox1.Image = Image.FromFile(profile);
            }
            label1.Text = name;
            label2.Text = totalPrice;
        }
    }
}
