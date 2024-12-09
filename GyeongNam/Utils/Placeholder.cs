using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GyeongNam.Utils
{
    internal class Placeholder
    {
        public static void Set(Control c, string text)
        {
            c.Text = text;
            c.ForeColor = Color.Gray;
            c.GotFocus += (s, e) =>
            {
                c.ForeColor = Color.Black;
                if (c.Text.Equals(text))
                {
                    c.Text = "";
                }
            };

            c.LostFocus += (s, e) => { 
                c.ForeColor = Color.Gray;
                if (string.IsNullOrEmpty(c.Text))
                {
                    c.Text = text;
                }
            };
        }
    }
}
