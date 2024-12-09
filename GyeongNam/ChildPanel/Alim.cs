using GyeongNam.Utils;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace GyeongNam.ChildPanel
{
    public partial class Alim : UserControl
    {
        public Alim()
        {
            InitializeComponent();

            Dock = DockStyle.Fill;

            var dic = new Dictionary<AlimPanel, int>();
            var list = DB.GetAlim();
            for (int i = 0; i < list.Count; i++)
            {
                int capture = i;

                AlimPanel panel = null;
                if (list[i][2].ToString().Split(',')[0].Equals(Login.no.ToString()))
                {
                    string b_convertDate = (DateTime.Now - Convert.ToDateTime(list[i][3].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-"))).ToString("dd");
                    string b_ago = b_convertDate.Equals("00") ? "오늘" : b_convertDate + "일전"; ;
                    
                    string getName = DB.GetString("select u_name from [user] where u_no = @1", list[i][1].ToString());
                    panel = new AlimPanel($"{getName}님이 회원님의 글을 북마크 했어요!", b_ago);
                    if (list[i][2].ToString().Split(',')[1].Equals("0"))
                    {
                        panel.SetColor(Color.IndianRed);
                    }

                    panel.GetComment().Click += (s, e) =>
                    {
                        if (list[capture][2].ToString().Split(',')[1].Equals("0"))
                        {
                            panel.SetColor(Color.Black);
                            Msg.ok("알림이 확인되었습니다.");
                            DB.DML("update alim set a_bookmark = @1 where a_no = @2", list[capture][2].ToString().Split(',')[0] + ",1", Convert.ToInt32(list[capture][0]));
                        }
                        else
                        {
                            Msg.fail("이미 확인 한 알림입니다.");
                        }
                    };
                    dic.Add(panel, Convert.ToInt32(b_convertDate));
                }

                if (list[i][4].ToString().Split(',')[0].Equals(Login.no.ToString()))
                {
                    string c_convertDate = (DateTime.Now - Convert.ToDateTime(list[i][5].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-"))).ToString("dd");
                    string c_ago = c_convertDate.Equals("00") ? "오늘" : c_convertDate + "일전";
                    string getName = DB.GetString("select u_name from [user] where u_no = @1", list[i][1].ToString());
                    panel = new AlimPanel($"{getName}님이 회원님의 글에 댓글을 남겼어요!", c_ago);
                    if (list[i][4].ToString().Split(',')[1].Equals("0"))
                    {
                        panel.SetColor(Color.IndianRed);
                    }

                    panel.GetComment().Click += (s, e) =>
                    {
                        if (list[capture][4].ToString().Split(',')[1].Equals("0"))
                        {
                            DB.DML("update alim set a_commet = @1 where a_no = @2", list[capture][4].ToString().Split(',')[0] + ",1", Convert.ToInt32(list[capture][0]));
                            panel.SetColor(Color.Black);
                            Msg.ok("알림이 확인되었습니다.");
                        }
                        else
                        {
                            Msg.fail("이미 확인 한 알림입니다.");
                        }
                    };
                    dic.Add(panel, Convert.ToInt32(c_convertDate));
                }
            }

            var sort = dic.OrderBy(x => x.Value).ToList();
            for (int i = 0; i < dic.Count; i++)
            {
                tableLayoutPanel1.Controls.Add(sort[i].Key);
            }
        }
    }
}
