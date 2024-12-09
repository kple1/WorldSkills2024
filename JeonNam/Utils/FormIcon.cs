using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JeonNam.Utils
{
    internal class FormIcon
    {
        public static Icon Set()
        {
            Icon ic = null;
            string img = "datafiles/logo/logo.png";
            using (var bt = new Bitmap(img))
            {
                using (var icon = Icon.FromHandle(bt.GetHicon()))
                {
                    using (var ms = new MemoryStream())
                    {
                        icon.Save(ms);
                        ic = new Icon(icon, new Size(30, 30));
                    }
                }
            }
            return ic;
        }

        public static Icon SetGoal(string path)
        {
            Icon ic = null;
            string img = path;
            using (var bt = new Bitmap(img))
            {
                using (var icon = Icon.FromHandle(bt.GetHicon()))
                {
                    using (var ms = new MemoryStream())
                    {
                        icon.Save(ms);
                        ic = new Icon(icon, new Size(30, 30));
                    }
                }
            }
            return ic;
        }
    }
}
