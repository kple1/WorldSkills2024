using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JeonNam.Utils
{
    internal class Msg
    {
        public static void ok(string msg)
        {
            MessageBox.Show(msg, "정보", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        public static void fail(string msg)
        {
            MessageBox.Show(msg, "경고", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
