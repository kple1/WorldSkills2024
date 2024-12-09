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
    public partial class Main : Form
    {
        List<MenuIcon> menuIcons = new List<MenuIcon>();
        string[] nameList = new string[] { "탐색", "목표", "기부", "설정" };
        string[] pathList = new string[] { "updates", "the goals", "donate", "settings" };
        public Main()
        {
            InitializeComponent();
            Icon = FormIcon.Set();

            view.Controls.Add(new Goal(this));

            Control[] controls = new Control[] { new Explore(this), new Goal(this), new Give(this), new Setting() };
            for (int i = 0; i < 4; i++)
            {
                int capture = i;
                var state = i == 1 ? 1 : 0;

                var panel = new MenuIcon(nameList[i], $"datafiles/logo/{pathList[i]}{state}.png");
                menuIcons.Add(panel);

                panel.GetPictureBox().Click += (s, e) =>
                {
                    view.Controls.Clear();
                    for (int k = 0; k < 4; k++)
                    {
                        menuIcons[k].GetPictureBox().Image = Image.FromFile($"datafiles/logo/{pathList[k]}0.png");
                    }
                    menuIcons[capture].GetPictureBox().Image = Image.FromFile($"datafiles/logo/{pathList[capture]}1.png");
                    view.Controls.Add(controls[capture]);
                    view.Invalidate();
                };
                
                tableLayoutPanel1.Controls.Add(panel);
            }
        }
    }
}
