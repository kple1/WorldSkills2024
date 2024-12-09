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
    public partial class GoalSelect : Form
    {
        private GoalSelectPanel selectedPanel; //이전 패널 추적

        public GoalSelect()
        {
            InitializeComponent();
            var list = DB.HowManyDonate();
            int max = list.Select(row => row[1]).Max();

            for (int i = 0; i < 17; i++)
            {
                string kor = DB.GetString("select Kor from [goals] where No = @1", i + 1);
                var panel = new GoalSelectPanel($"datafiles/목표/{i + 1}.png", kor, max, list[i][1]);

                panel.GetLabel().Paint += (s, e) =>
                {
                    if (panel == selectedPanel)
                    {
                        e.Graphics.DrawRectangle(new Pen(Color.Black, 3), 0, 0, panel.GetLabel().Width - 1, panel.GetLabel().Height - 1);
                    }
                };

                panel.GetLabel().Click += (s, e) =>
                {
                    selectedPanel?.GetLabel().Invalidate();
                    selectedPanel = panel;
                    panel.GetLabel().Invalidate();
                };

                panel.GetButton().Click += (s, e) =>
                {
                    new Donate("기부하기").Show();
                    Close();
                };

                tableLayoutPanel1.Controls.Add(panel);
            }
        }
    }

}
