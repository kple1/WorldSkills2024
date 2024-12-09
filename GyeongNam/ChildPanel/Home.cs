using GyeongNam.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GyeongNam.ChildPanel
{
    public partial class Home : UserControl
    {
        TableLayoutPanel tableLayout = new TableLayoutPanel();
        TableLayoutPanel newspaperPanel = new TableLayoutPanel();

        Main main;
        public Home(Main main)
        {
            InitializeComponent();

            this.main = main;

            SetCatrgory(1);
            ChangeTabToLoad(1);

            Dock = DockStyle.Fill;

            pictureBox1.Image = Image.FromFile("datafiles/icon/add.png");
            pictureBox2.Image = Image.FromFile("datafiles/icon/news.png");

            inputPanel.Region = Region.FromHrgn(CreateRoundRectRgn(2, 2, inputPanel.Width, inputPanel.Height, 15, 15));
            inputPanel.BackColor = Color.IndianRed;

            tableLayout.AutoScroll = true;
            tableLayout.Dock = DockStyle.Fill;
            view.Controls.Add(tableLayout);

            newspaperPanel.Dock = DockStyle.Fill;
            newspaperPanel.ColumnCount = 4;
            view.Controls.Add(newspaperPanel);

            for (int i = 0; i < 4; i++)
            {
                newspaperPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50F));
            }

            notfound.Visible = false;
            newspaperPanel.Visible = false;
        }

        [DllImport("Gdi32.dll", EntryPoint = "CreateRoundRectRgn")]
        private static extern IntPtr CreateRoundRectRgn(int nLeftRect
                                                      , int nTopRect
                                                      , int nRightRect
                                                      , int nBottomRect
                                                      , int nWidthEllipse
                                                      , int nHeightEllipse);

        int selectedIndex = 0;
        private void ChangeTabToLoad(int selectedIndex)
        {
            tableLayout.Controls.Clear();
            newspaperPanel.Visible = false;
            notfound.Visible = false;
            this.selectedIndex = selectedIndex;

            if (selectedIndex == 0)
            {
                tableLayout.Visible = false;

                string getSub = DB.GetString("select u_sub from [user] where u_no = @1", Login.no);
                if (getSub.Equals("null"))
                {
                    notfound.Visible = true;
                }
                else
                {
                    newspaperPanel.Visible = true;
                    string[] subs = getSub.Split(',');
                    string[] pngList = new string[] { "경향신문", "동아일보", "매일경제", "문화일보", "전자신문", "조선일보" };
                    for (int i = 0; i < subs.Length; i++)
                    {
                        string newspaper = DB.GetString("select np_name from newspaper where np_no = @1", subs[i]);
                        string format = pngList.Contains(newspaper) ? "png" : "jpg";
                        newspaperPanel.Controls.Add(new NewsPaperIcon($"datafiles/newspaper/{newspaper}.{format}", newspaper));
                    }
                }
            }
            else if (selectedIndex == 1)
            {
                tableLayout.Visible = true;
                var list = DB.GetRecommendNews();
                for (int i = 0; i < list.Count; i++)
                {
                    string whiplash = list[i][2].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-");
                    var date = (DateTime.Now - Convert.ToDateTime(whiplash)).ToString("dd");
                    if ((int)list[i][0] != 19) tableLayout.Controls.Add(new RecommendNewsPanel(list[i][1].ToString(), date.ToString(), list[i][3].ToString(), $"datafiles/news/nno{list[i][0].ToString()}.jpg", list[i][0].ToString(), main));
                }
            }
            else
            {
                tableLayout.Visible = true;
                var list = DB.SelectToCategory(selectedIndex - 1);
                for (int i = 0; i < list.Count; i++)
                {
                    string whiplash = list[i][2].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-");
                    var date = (DateTime.Now - Convert.ToDateTime(whiplash)).ToString("dd");
                    if ((int)list[i][0] != 19) tableLayout.Controls.Add(new RecommendNewsPanel(list[i][1].ToString(), date.ToString(), list[i][3].ToString(), $"datafiles/news/nno{list[i][0].ToString()}.jpg", list[i][0].ToString(), main));
                }
            }
            tableLayout.Invalidate();
        }

        private void SetCatrgory(int selectedIndex)
        {
            categories.Controls.Clear();
            string[] category = new string[] { "구독", "추천", "경제", "사회", "국제", "스포츠", "IT", "생활/문화" };
            for (int i = 0; i < 8; i++)
            {
                int capture = i;

                var label = new Label();
                label.Click += (s, e) =>
                {
                    ChangeTabToLoad(capture);
                    SetCatrgory(capture);
                };

                if (selectedIndex == i)
                {
                    label.ForeColor = Color.IndianRed;
                }
                else
                {
                    label.ForeColor = Color.Black;
                }
                label.Text = category[i];
                label.TextAlign = ContentAlignment.MiddleCenter;
                label.Dock = DockStyle.Fill;
                label.AutoSize = false;
                label.Font = new Font("맑은 고딕", 12);
                categories.Controls.Add(label);
            }
            categories.Invalidate();
        }

        int countRow = 0;

        private void textBox1_KeyDown_1(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                countRow = 0;
                tableLayout.Controls.Clear();
                if (selectedIndex == 1)
                {
                    var list = DB.GetRecommendNews();
                    for (int i = 0; i < list.Count; i++)
                    {
                        string whiplash = list[i][2].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-");
                        var date = (DateTime.Now - Convert.ToDateTime(whiplash)).ToString("dd");
                        if ((int)list[i][0] != 19 && list[i][1].ToString().Contains(textBox1.Text))
                        {
                            tableLayout.Controls.Add(new RecommendNewsPanel(list[i][1].ToString(), date.ToString(), list[i][3].ToString(), $"datafiles/news/nno{list[i][0].ToString()}.jpg", list[i][0].ToString(), main));
                            ++countRow;
                        }
                    }
                }
                else
                {
                    tableLayout.Visible = true;
                    var list = DB.SelectToCategory(selectedIndex - 1);
                    for (int i = 0; i < list.Count; i++)
                    {
                        string whiplash = list[i][2].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-");
                        var date = (DateTime.Now - Convert.ToDateTime(whiplash)).ToString("dd");
                        if ((int)list[i][0] != 19 && list[i][1].ToString().Contains(textBox1.Text))
                        {
                            tableLayout.Controls.Add(new RecommendNewsPanel(list[i][1].ToString(), date.ToString(), list[i][3].ToString(), $"datafiles/news/nno{list[i][0].ToString()}.jpg", list[i][0].ToString(), main));
                            ++countRow;
                        }
                    }
                }
                if (countRow == 0)
                {
                    Msg.fail("검색결과가 없습니다.");
                    textBox1.Text = "";
                }
                tableLayout.Invalidate();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            main.Close();
            new Subscribe("main").Show();
        }
    }
}
