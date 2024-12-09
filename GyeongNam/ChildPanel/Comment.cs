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

namespace GyeongNam.ChildPanel
{
    public partial class Comment : UserControl
    {
        string n_no;
        public Comment(string n_no)
        {
            InitializeComponent();
            this.n_no = n_no;
            Placeholder.Set(textBox1, "친절한 댓글 ...");
            Dock = DockStyle.Fill;

            var list = DB.GetComments(n_no);
            for (int i = 0; i < list.Count; i++)
            {
                int capture = i;
                string ago = (DateTime.Now - Convert.ToDateTime(list[i][1].ToString().Substring(0, 8).Insert(4, "-").Insert(7, "-"))).ToString("dd") + "일전";
                var panel = new CommentPanel(list[i][0].ToString(), ago, list[i][2].ToString());
                panel.GetOption().Click += (s1, e1) =>
                {
                    if (panel.GetName().Equals(list[capture][0].ToString()))
                    {
                        var c = MessageBox.Show("삭제하시겠습니까?", "댓글", MessageBoxButtons.YesNo);
                        if (c == DialogResult.Yes)
                        {
                            tableLayoutPanel1.Controls.Remove(panel);
                            DB.DML("delete from comment where c_no = @1", Convert.ToInt32(list[capture][4]));
                            tableLayoutPanel1.Invalidate();
                        }
                    }
                    else
                    {
                        Msg.ok("신고하시겠습니까?");
                    }
                };
                tableLayoutPanel1.Controls.Add(panel);
 
            }
            label1.Text = $"댓글 {list.Count}";
        }

        public Label GetExitButton()
        {
            return label2;
        }
        private void label3_Click(object sender, EventArgs e)
        {
            string name = DB.GetString("select u_name from [user] where u_no = @1", Login.no);
            var panel = new CommentPanel(name, "0일전", textBox1.Text);
            panel.GetOption().Click += (s1, e1) =>
            {
                if (panel.GetName().Equals(name))
                {
                    var i = MessageBox.Show("삭제하시겠습니까?", "댓글", MessageBoxButtons.YesNo);
                    if (i == DialogResult.Yes)
                    {
                        tableLayoutPanel1.Controls.Remove(panel);
                        tableLayoutPanel1.Invalidate();
                    }
                }
                else
                {
                    Msg.ok("신고하시겠습니까?");
                }
            };
            tableLayoutPanel1.Controls.Add(panel);
            tableLayoutPanel1.Invalidate();
            DB.DML("INSERT INTO comment values (@1, @2, @3, @4)", textBox1.Text, Login.no, n_no, DateTime.Now.ToString("yyyyMMddHHmm"));
        }
    }
}
