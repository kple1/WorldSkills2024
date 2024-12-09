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
    public partial class ExplorePanel : UserControl
    {
        string division;
        Main main;
        string title;
        string path;
        public ExplorePanel(string title, string division, string path, Main main)
        {
            InitializeComponent();
            this.division = division;
            this.main = main;
            this.title = title;
            this.path = path;

            label1.Text = title;
            label2.Text = division;
            pictureBox1.Image = Image.FromFile(path);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (division.Equals("뉴스"))
            {
                new News(title, path).Show();
            }
            else
            {
                int num = DB.GetInt("select No from [updates] where title = @1", title);
                new Advertise(title, $"datafiles/탐색/{num}(로고).png", division, $"datafiles/탐색/{num}(이미지).gif").Show();
            }
            main.Close();
        }
    }
}
