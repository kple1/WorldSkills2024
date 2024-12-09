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
    public partial class RecommendNewsPanel : UserControl
    {
        public RecommendNewsPanel(string title, string ago, string count, string path, string no, Main main)
        {
            InitializeComponent();
            Dock = DockStyle.Top;
            label1.Text = title;
            Control[] cList = new Control[] { label1, panel1, pictureBox1 };
            for (int i = 0; i < 3; i++)
            {
                cList[i].Click += (s, e) =>
                {
                    main.Hide();
                    string detail = DB.GetString("select n_detail from news where n_title = @1", title);
                    new NewsInfo($"datafiles/newspaper/뉴스타임즈.jpg", "뉴스타임즈", ago, detail, $"datafiles/news/nno{no}.jpg").Show();
                };
            }
            label4.Text = $"{ago}일전";
            label2.Text = $"조회 {count}";
            pictureBox1.Image = Image.FromFile(path);
        }
    }
}
