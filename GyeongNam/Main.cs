using GyeongNam.ChildPanel;
using GyeongNam.Utils;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;

namespace GyeongNam
{
    public partial class Main : Form
    {
        TableLayoutPanel tableLayout = new TableLayoutPanel();
        TableLayoutPanel newspaperPanel = new TableLayoutPanel();
        public Main()
        {
            InitializeComponent();

            view.Controls.Add(new Home(this));
            reload(0);
        }

        void reload(int selectedIndex)
        {
            string[] paths = new string[] { "home.png", "bell.png", "mypage.png" };
            string[] names = new string[] { "홈", "알림", "마이페이지" };
            var uc = new UserControl[] { new Home(this), new Alim(), new MyPage(this) };

            var mvcList = new List<MainViewControllerIcon>();

            for (int i = 0; i < 3; i++)
            {
                int caputer = i;

                var mvc = new MainViewControllerIcon(paths[i], names[i]);
                mvcList.Add(mvc);

                mvc.GetIcon().Click += (s, e) =>
                {
                    foreach (var icon in mvcList)
                    {
                        icon.SetLine( Color.White);
                    }
                    Text = names[caputer];

                    view.Controls.Clear();
                    view.Controls.Add(uc[caputer]);
                    mvc.SetLine(Color.IndianRed);
                };
                if (selectedIndex == i)
                {
                    mvc.SetLine(Color.IndianRed);
                }
                tab.Controls.Add(mvc);
            }
        }
    }
}