using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JeonNam.Utils
{
    internal class FindNumber
    {
        public static int GetPrice(string s)
        {
            int save = 0;
            var list = new List<int>();
            for (int i = 0; i < s.Length; i++)
            {
                if (char.IsDigit(s[i]))
                {
                    list.Add(int.Parse(s[i].ToString()));
                }
            }
            if (list.Count == 0)
            {
                save = 0;
            }
            else
            {
                save = int.Parse(string.Join("", list));
            }
            return save;
        }
    }
}
