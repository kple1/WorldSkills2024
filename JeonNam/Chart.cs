using JeonNam.ChildPanel;
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

namespace JeonNam
{
    public partial class Chart : Form
    {
        public Chart()
        {
            InitializeComponent();
            Icon = FormIcon.Set();
            var list = DB.GetSummaryMoneyGiveRank();
            var max = list.Select(arr => arr[1]).Max();
            for (int i = 0; i < list.Count; i++)
            {
                tableLayoutPanel1.Controls.Add(new RankPanel($"datafiles/목표/{i + 1}.png", Convert.ToInt32(max), Convert.ToInt32(list[i][1])));
            }
        }

        //all
        private void button1_Click(object sender, EventArgs e)
        {
            button1.BackColor = Color.RoyalBlue;
            button1.ForeColor = Color.White;
            button2.BackColor = Color.White;
            button2.ForeColor = Color.RoyalBlue;
            tableLayoutPanel1.Controls.Clear();
            var list = DB.GetSummaryMoneyGiveRank();
            var max = list.Select(arr => arr[1]).Max();
            for (int i = 0; i < list.Count; i++)
            {
                tableLayoutPanel1.Controls.Add(new RankPanel($"datafiles/목표/{i + 1}.png", Convert.ToInt32(max), Convert.ToInt32(list[i][1])));
            }
            tableLayoutPanel1.Invalidate();
        }

        //me
        private void button2_Click(object sender, EventArgs e)
        {
            button1.BackColor = Color.White;
            button1.ForeColor = Color.RoyalBlue;
            button2.BackColor = Color.RoyalBlue;
            button2.ForeColor = Color.White;
            tableLayoutPanel1.Controls.Clear();
            var list = DB.GetUserSummaryMoneyGiveRank();
            var max = list.Select(arr => arr[1]).Max();
            for (int i = 0; i < list.Count; i++)
            {
                tableLayoutPanel1.Controls.Add(new RankPanel($"datafiles/목표/{i + 1}.png", Convert.ToInt32(max), Convert.ToInt32(list[i][1])));
            }
            tableLayoutPanel1.Invalidate();
        }
    }
}
