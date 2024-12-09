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
    public partial class Explore : UserControl
    {
        Main main;
        public Explore(Main main)
        {
            InitializeComponent();
            this.main = main;

            Dock = DockStyle.Fill;
            tableLayoutPanel1.RowStyles.Clear();
            tableLayoutPanel1.RowCount = 0;
            tableLayoutPanel1.VerticalScroll.Visible = false;

            var r = new Random();
            var list = DB.GetUpdates();
            var shuffled = list.OrderBy(_ => r.Next()).ToList();
            for (int i = 0; i < list.Count; i++)
            {
                string format = Convert.ToInt32(shuffled[i][0]) <= 10 ? ".png" : ".jpg";
                string titleLabel = Convert.ToInt32(shuffled[i][0]) <= 10 ? "" : "(타이틀)";
                var panel = new ExplorePanel(shuffled[i][1].ToString(), shuffled[i][2].ToString(), $"datafiles/탐색/{shuffled[i][0]}{titleLabel}{format}", main);
                AddControl(panel);
            }
        }

        private void AddControl(Control control)
        {
            tableLayoutPanel1.RowCount++;
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 220));
            tableLayoutPanel1.Controls.Add(control);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            main.Close();
            new Quiz().Show();
        }
    }
}