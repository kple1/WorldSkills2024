using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class CarRank : Form
    {
        List<object[]> list;
        public CarRank()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            for (int i = 1; i < 8; i++)
            {
                var stream = new MemoryStream(DB.getBytes("select b_img from [brand] where b_no = @1", i));
                dataGridView1.Rows.Add();
                dataGridView1.Rows[i - 1].Cells[0] = new DataGridViewImageCell()
                {
                    Value = new Bitmap(stream),
                    ImageLayout = DataGridViewImageCellLayout.Zoom
                };
                dataGridView1.Rows[i - 1].Cells[0].Style.SelectionBackColor = Color.Black;
                dataGridView1.Rows[i - 1].Cells[0].Style.BackColor = Color.Black;
            }

            RadioButton[] radioList = new RadioButton[] { radioButton1, radioButton2, radioButton3 };
            string[] com1 = new string[] { "일반", "전기", "하이브리드" };
            string[] com2 = new string[] { "세단", "SUV" };
            for (int i = 0; i < 3; i++)
            {
                int capture = i;
                radioList[i].CheckedChanged += (s, e) =>
                {
                    if (radioList[capture].Checked)
                    {
                        if (capture == 0)
                        {
                            comboBox1.Visible = false;
                            dataGridView1.Visible = true;
                            int a = dataGridView1.CurrentRow.Index;
                            dataGridView1.FirstDisplayedScrollingRowIndex = a;
                            list = DB.GetCarRankBrand(a + 1);
                            Create(list);
                        }
                        else
                        {
                            dataGridView1.Visible = false;
                            comboBox1.Visible = true;
                            comboBox1.Items.Clear();
                            if (capture == 1)
                            {
                                for (int j = 0; j < com1.Length; j++)
                                {
                                    comboBox1.Items.Add(com1[j]);
                                }
                                comboBox1.SelectedIndex = 0;
                                list = DB.GetCarRankDivision(comboBox1.SelectedIndex + 1);
                            }
                            else if (capture == 2)
                            {
                                for (int j = 0; j < com2.Length; j++)
                                {
                                    comboBox1.Items.Add(com2[j]);
                                }
                                comboBox1.SelectedIndex = 0;
                                list = DB.GetCarRankType(comboBox1.SelectedIndex + 1);
                            }

                            Create(list);
                        }
                    }
                };
            }
        }

        void Create(List<object[]> list)
        {
            tableLayoutPanel3.Controls.Clear();
            for (int i = 0; i < 5; i++)
            {
                int capture = i;

                var label = new Label();
                label.Font = new Font("맑은 고딕", 12);
                label.Text = $"{i + 1}";
                label.ForeColor = Color.White;
                label.AutoSize = true;
                tableLayoutPanel3.Controls.Add(label, 0, i);
                for (int j = 0; j < (int)list[i][0]; j++)
                {
                    var stream = new MemoryStream((byte[])list[i][2]);
                    var pc = new PictureBox();
                    pc.MouseHover += (s, e) =>
                    {
                        var toolTip = new ToolTip();
                        toolTip.SetToolTip(pc, $"{(int)list[capture][0]}대");
                    };
                    pc.SizeMode = PictureBoxSizeMode.Zoom;
                    pc.Image = new Bitmap(stream);
                    tableLayoutPanel3.ColumnCount += 1;
                    tableLayoutPanel3.Controls.Add(pc, j + 1, i);
                }
                var name = new Label();
                name.Font = new Font("맑은 고딕", 12);
                name.Text = $"{list[i][1].ToString()}";
                name.ForeColor = Color.White;
                tableLayoutPanel3.Controls.Add(name, (int)list[i][0] + 1, i);
            }
            tableLayoutPanel3.Invalidate();
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }


        private void dataGridView1_Scroll(object sender, ScrollEventArgs e)
        {
            list = DB.GetCarRankBrand(e.NewValue + 1);
            Create(list);
        }

        private void comboBox1_SelectedValueChanged(object sender, EventArgs e)
        {
            if (comboBox1.SelectedItem.ToString().Equals("일반") || comboBox1.SelectedItem.ToString().Equals("전기") || comboBox1.SelectedItem.ToString().Equals("하이브리드"))
            {
                list = DB.GetCarRankDivision(comboBox1.SelectedIndex + 1);
            }
            else
            {
                list = DB.GetCarRankType(comboBox1.SelectedIndex + 1);
            }
            Create(list);
        }

        private void label1_MouseHover(object sender, EventArgs e)
        {

        }
    }
}
