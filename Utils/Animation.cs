using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon.Utils
{
    internal class Animation
    {
        private static bool isHovering = false;
        private static Timer timer1, timer2;
        private static int rgb1 = 0, rgb2 = 255;

        public static void ChangeBackgroundColor(Control c)
        {
            c.MouseHover += (s, e) =>
            {
                if (isHovering) return;  // 이미 호버 중이라면 이벤트를 무시
                isHovering = true;

                timer1 = new Timer();
                if (timer2 != null && timer2.Enabled) timer2.Stop();

                timer1.Interval = 10;
                timer1.Tick += (s1, e1) =>
                {
                    if (rgb1 + 4 <= 255)
                    {
                        rgb1 += 4;
                        rgb2 -= 4;
                        c.BackColor = Color.FromArgb(rgb1, rgb1, rgb1);
                        c.ForeColor = Color.FromArgb(rgb2, rgb2, rgb2);
                    }
                    else
                    {
                        timer1.Stop();
                    }
                };
                timer1.Start();
            };

            c.MouseLeave += (s, e) =>
            {
                if (!isHovering) return;  // 이미 떠난 상태라면 이벤트를 무시
                isHovering = false;

                timer2 = new Timer();
                if (timer1 != null && timer1.Enabled) timer1.Stop();

                timer2.Interval = 10;
                timer2.Tick += (s1, e1) =>
                {
                    if (rgb1 - 4 >= 0)
                    {
                        rgb1 -= 4;
                        rgb2 += 4;
                        c.BackColor = Color.FromArgb(rgb1, rgb1, rgb1);
                        c.ForeColor = Color.FromArgb(rgb2, rgb2, rgb2);
                    }
                    else
                    {
                        timer2.Stop();
                    }
                };
                timer2.Start();
            };
        }

    }
}
