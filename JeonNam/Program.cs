using JeonNam.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.LinkLabel;

namespace JeonNam
{
    internal static class Program
    {
        static NotifyIcon icon;

        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            icon = new NotifyIcon();
            icon.Icon = FormIcon.Set();
            icon.Visible = true;
            icon.ShowBalloonTip(3000, "환영합니다.", "로그인 하기", ToolTipIcon.Info);
            icon.BalloonTipClicked += click;
            icon.Click += click;

            Application.Run();
        }

        static void click(object sender, EventArgs e)
        {
            new Login().Show();
        }
    }
}
