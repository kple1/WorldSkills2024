using Gangwon.Utils;
using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class Brand : Form
    {
        public Brand()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            for (int i = 0; i < 7; i++)
            {
                var stream = new MemoryStream(DB.getBytes("select b_img from [brand] where b_no = @1", i + 1));
                dataGridView2.Rows.Add();
                dataGridView2.Rows[i].Cells[0] = new DataGridViewImageCell()
                {
                    Value = new Bitmap(stream),
                    ImageLayout = DataGridViewImageCellLayout.Zoom
                };
                dataGridView2.Rows[i].Cells[0].Style.SelectionBackColor = Color.Black;
                dataGridView2.Rows[i].Cells[0].Style.BackColor = Color.Black;
            }

            reload(1);
        }

        int scrollIndex = 0;
        private void dataGridView2_Scroll(object sender, ScrollEventArgs e)
        {
            scrollIndex = e.NewValue;
            reload(scrollIndex + 1);
        }

        void reload(int index)
        {
            var list = DB.GetBrands(index);
            label8.Text = list[0].ToString();
            label3.Text = list[1].ToString();
            label5.Text = list[2].ToString();
            label6.Text = $"{list[3].ToString()} {list[4].ToString()}";
            label7.Text = list[0].ToString();
            button1.Text = $"{list[0].ToString()}의 더 많은 차량 보기";

            var get = DB.RandomImage(index);
            var getDB = DB.result(get, index);

            pictureBox2.Image = Image.FromFile($"datafiles/image/carInfo/{getDB[1].ToString()}.gif");

            var s2 = new MemoryStream((byte[])getDB[0]);
            pictureBox3.Image = new Bitmap(s2);
        }

        private void label3_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            g.DrawRectangle(new Pen(Color.White), new Rectangle(0, 0, label3.Width - 1, label3.Height - 1));
        }

        private void label5_Paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            g.DrawRectangle(new Pen(Color.White), new Rectangle(0, 0, label5.Width - 1, label5.Height - 1));
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Hide();
            new CarSearch(scrollIndex + 1).Show();
        }
    }
}
