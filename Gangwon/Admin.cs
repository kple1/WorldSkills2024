using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class Admin : Form
    {
        public object Curreny { get; private set; }

        public Admin()
        {
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            comboBox1.Items.Add("고객 매출 순위");
            comboBox1.Items.Add("브랜드 별 매출 순위");
            comboBox1.SelectedIndex = 0;
            reload(0);
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }

        void reload(int index)
        {
            if (index == 0)
            {
                dataGridView2.Visible = false;
                dataGridView1.Visible = true;
                var list = DB.GetUserPurchaseRank();
                for (int i = 0; i < list.Count; i++)
                {
                    dataGridView1.Rows.Add(i + 1, list[i][0], list[i][1] + "번", Currency.Korean(list[i][2]), list[i][3], list[i][4]);
                }
            }
            else
            {
                dataGridView2.Visible = true;
                dataGridView1.Visible = false;
                var list = DB.GetBrandSellRank();
                for (int i = 0; i < list.Count; i++)
                {
                    var stream = new MemoryStream((byte[])list[i][1]);
                    var pc = new PictureBox();
                    pc.Image = new Bitmap(stream);
                    dataGridView2.Rows.Add(i + 1, list[i][0], pc.Image, list[i][2] + "번", Currency.Korean(list[i][3]));
                }
            }
        }

        private void comboBox1_SelectedValueChanged(object sender, EventArgs e)
        {
            reload(comboBox1.SelectedIndex);
        }
    }
}
