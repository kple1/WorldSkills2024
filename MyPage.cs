using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class MyPage : Form
    {
        public MyPage()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            dataGridView1.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            dataGridView2.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
        }


        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            dataGridView2.Visible = false;
            dataGridView1.Visible = true;
            if (radioButton1.Checked) {
                refreshGridView1();
            }
        }

        void refreshGridView1()
        {
            dataGridView1.Rows.Clear();
            List<object[]> list = DB.GetUserDrive(Login.no);
            if (list.Count == 0)
            {
                dataGridView1.Visible = false;
                var label = new Label();
                label.AutoSize = false;
                label.Dock = DockStyle.Fill;
                label.Text = "시승 일정이 없습니다.";
                label.Font = new Font("맑은 고딕", 12);
                label.ForeColor = Color.White;
                label.BackColor = Color.Black;
                label.TextAlign = ContentAlignment.MiddleCenter;
                panel3.Controls.Add(label);
            }
            for (int i = 0; i < list.Count; i++)
            {
                dataGridView1.Rows.Add(i + 1, list[i][0], list[i][1], list[i][2], list[i][3]);
                if (DateTime.Now.ToString("yyyy-MM-dd").Equals(dataGridView1.Rows[i].Cells[4].Value.ToString()))
                {
                    dataGridView1.Rows[i].DefaultCellStyle.BackColor = Color.Red;
                }
            }
            dataGridView1.Invalidate();
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            dataGridView2.Visible = true;
            dataGridView1.Visible = false;
            if (radioButton2.Checked)
            {
                dataGridView2.Rows.Clear();
                List<object[]> list = DB.UserPurchaseList(Login.no);
                
                for (int i = 0; i < list.Count; i++)
                {
                    dataGridView2.Rows.Add(i + 1, list[i][0], list[i][1], list[i][2], list[i][3], list[i][4], list[i][5], Currency.Korean(list[i][6]), list[i][7] );
                }
                dataGridView2.Invalidate();
                Msg.fail(list.Count.ToString());
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }

        private void dataGridView1_MouseClick(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Right)
            {
                var menu = new ContextMenuStrip();
                var change = new ToolStripMenuItem("변경하기");
                change.Click += (s1, e1) =>
                {
                    int c_no = DB.getInt("select c_no from car where c_name = @1", dataGridView1.Rows[row].Cells[2].Value);
                    new Calendar(1, c_no).Show();
                    Close();
                };
                var delete = new ToolStripMenuItem("삭제하기");
                delete.Click += (s1, e1) =>
                {
                    Msg.ok($"삭제가 완료되었습니다.");
                    DB.DML("delete from [drive] where u_no = @1 and c_no = @2", Login.no, DB.getInt("select c_no from car where c_name = @1", dataGridView1.Rows[row].Cells[2].Value));
                    refreshGridView1();
                };
                menu.Items.Add(change);
                menu.Items.Add(delete);
                menu.Show(e.X, e.Y);
            }
        }

        int row = 0;
        private void dataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            row = e.RowIndex;
        }
    }
}