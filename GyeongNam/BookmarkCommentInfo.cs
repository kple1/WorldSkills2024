using GyeongNam.ChildPanel;
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

namespace GyeongNam
{
    public partial class BookmarkCommentInfo : Form
    {
        public BookmarkCommentInfo(string state)
        {
            InitializeComponent();
            if (state.Equals("bookmark"))
            {
                Text = "북마크 정보";
                var GetBookMark = DB.GetString("select u_bookmark from [user] where u_no = @1;", Login.no).Split(',');
                for (int i = 0; i < GetBookMark.Length; i++)
                {
                    var GetInfo = DB.GetBookMarkInfo(GetBookMark[i]);
                    string ago = (DateTime.Now - Convert.ToDateTime(GetInfo[1].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-"))).ToString("dd") + "일전";
                    tableLayoutPanel1.Controls.Add(new BookmarkComment(GetInfo[0], $"datafiles/news/nno{GetBookMark[i]}.jpg", ago, GetInfo[2]));
                }
            }
            else
            {
                Text = "댓글 정보";
                var list = DB.GetComment();
                for (int i = 0; i < list.Count; i++)
                {
                    string ago = (DateTime.Now - Convert.ToDateTime(list[i][4].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-"))).ToString("dd") + "일전";
                    tableLayoutPanel1.Controls.Add(new BookmarkComment(list[i][1].ToString(), $"datafiles/news/nno{list[i][3].ToString()}.jpg", ago, ""));
                }
            }
        }
    }
}
