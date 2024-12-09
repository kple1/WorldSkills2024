using Gangwon.ChildPanel;
using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.ComTypes;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class CarSearch : Form
    {
        int bno = 0;
        public CarSearch(int bno)
        {
            this.bno = bno;
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            comboBox1.Items.Add("전체");
            comboBox1.Items.Add("일반");
            comboBox1.Items.Add("전기");
            comboBox1.Items.Add("하이브리드");
            comboBox2.Items.Add("전체");
            comboBox2.Items.Add("세단");
            comboBox2.Items.Add("SUV");

            comboBox1.SelectedIndex = 0;
            comboBox2.SelectedIndex = 0;

            for (int i = 0; i < 8; i++)
            {
                if (i != 0)
                {
                    var stream = new MemoryStream(DB.getBytes("select b_img from [brand] where b_no = @1", i));
                    dataGridView2.Rows.Add();
                    dataGridView2.Rows[i].Cells[0] = new DataGridViewImageCell()
                    {
                        Value = new Bitmap(stream),
                        ImageLayout = DataGridViewImageCellLayout.Zoom
                    };
                }
                else
                {
                    dataGridView2.Rows.Add("전체");
                    dataGridView2.Rows[i].Cells[0].Style.ForeColor = Color.White;
                    dataGridView2.Rows[i].Cells[0].ReadOnly = true;
                }

                dataGridView2.Rows[i].Cells[0].Style.BackColor = Color.Black;
                dataGridView2.Rows[i].Cells[0].Style.SelectionBackColor = Color.Black;
            }

            brandNum = bno;
            addPanel();
            brandNum = 0;
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
            addPanel();
        }

        void addPanel()
        {
            tableLayoutPanel1.RowCount = 1;
            tableLayoutPanel1.Controls.Clear();
            List<object[]> list = DB.getCarInformation();
            for (int i = 0; i < list.Count; i++)
            {
                if (string.IsNullOrEmpty(textBox1.Text) || textBox1.Text.Equals(list[i][2].ToString()))
                {
                    if (comboBox1.SelectedIndex == 0 || comboBox1.SelectedItem.ToString().Equals(list[i][3].ToString()))
                    {
                        if (comboBox2.SelectedIndex == 0 || comboBox2.SelectedItem.ToString().Equals(list[i][4].ToString()))
                        {
                            if (brandNum == 0 || brandNum.Equals((int)list[i][5])) {
                                tableLayoutPanel1.Controls.Add(new CarInformationPanel((byte[])list[i][0], (byte[])list[i][1], list[i][2].ToString(), list[i][3].ToString(), list[i][4].ToString(), this));
                                tableLayoutPanel1.RowCount++;
                            }
                        }
                    }
                }
            }
            tableLayoutPanel1.Invalidate();
        }

        int brandNum = 0;
        private void dataGridView2_Scroll(object sender, ScrollEventArgs e)
        {
            brandNum = e.NewValue;
        }
    }
}
