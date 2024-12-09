using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gangwon.Utils
{
    internal class Currency
    {
        public static string Korean(object price)
        {
            string save = price.ToString();
            if (save.Length == 8)
            {
                save = save.Substring(0, 4) + "만원";
            }
            else if (save.Length == 9)
            {
                save = save.Substring(0, 1) + "억 " + save.Substring(1, 4) + "만원";
            }
            else if (save.Length == 10)
            {
                save = save.Substring(0, 2) + "억" + save.Substring(2, 4) + "만원";
            }
            return save;
        }
    }
}
