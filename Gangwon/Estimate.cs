using Gangwon.ChildPanel;
using Gangwon.Utils;
using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class Estimate : Form
    {
        int sel1 = 0;
        int sel2 = 4;
        int sel3 = 7;
        int sel4 = 9;
        string carName;
        public static int c_no;
        public static int op1 = 1;
        public static int op2 = 5;
        public static int op3 = 8;
        public static int op4 = 10;
        ResultPrice estimate;
        public Estimate(string carName, byte[] carImage)
        {
            InitializeComponent();
            estimate = new ResultPrice(this);
            c_no = DB.getInt("select c_no from car where c_name = @1", carName); 

            this.carName = carName;
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
            label3.Text = carName;

            var s1 = new MemoryStream(carImage);
            pictureBox2.Image = new Bitmap(s1);

            var s2 = new MemoryStream(getDB(1));
            pictureBox3.Image = new Bitmap(s2);

            var s3 = new MemoryStream(getDB(5));
            pictureBox4.Image = new Bitmap(s3);

            var s4 = new MemoryStream(getDB(8));
            pictureBox5.Image = new Bitmap(s4);

            var s5 = new MemoryStream(getDB(10));
            pictureBox6.Image = new Bitmap(s5);
        }

        int totalHeight = 0;
        void reload()
        {
            tableLayoutPanel2.Controls.Clear();
            var list = DB.options();
            var count = 0;
            var labelName = new string[] { "휠 타입", "차체 타입", "엔진 타입", "시트 타입" };

            estimate.setNormalPrice(DB.getBigInt("select c_price from car where c_name = @1", carName));
            for (int i = 0; i < list.Count; i++)
            {
                int capture = i;
                var panel = new ChooseEstimate(list[i][1].ToString(), "+ " + string.Format("{0:N0}", Convert.ToInt32(list[i][2])) + " 원", (byte[])list[i][4]);
                panel.Click += (s, e) =>
                {
                    if (capture <= 3)
                    {
                        sel1 = capture;
                        var s1 = new MemoryStream(panel.GetPicture());
                        pictureBox3.Image = new Bitmap(s1);
                        estimate.setWheelPrice(Convert.ToInt32(list[capture][2]));
                        op1 = capture + 1;
                    }
                    else if (capture > 3 && capture <= 6)
                    {
                        sel2 = capture;
                        var s1 = new MemoryStream(panel.GetPicture());
                        pictureBox4.Image = new Bitmap(s1);
                        estimate.setBodyPrice(Convert.ToInt32(list[capture][2]));
                        op2 = capture + 1;
                    }
                    else if (capture > 6 && capture <= 8)
                    {
                        sel3 = capture;
                        var s1 = new MemoryStream(panel.GetPicture());
                        pictureBox5.Image = new Bitmap(s1);
                        estimate.setEnginePrice(Convert.ToInt32(list[capture][2]));
                        op3 = capture + 1;
                    }
                    else if (capture > 8 && capture <= 10)
                    {
                        sel4 = capture;
                        var s1 = new MemoryStream(panel.GetPicture());
                        pictureBox6.Image = new Bitmap(s1);
                        estimate.setSheetPrice(Convert.ToInt32(list[capture][2]));
                        op4 = capture + 1;
                    }
                    reload();
                };

                if (i == sel1 || i == sel2 || i == sel3 || i == sel4)
                {
                    panel.Selected();
                }
                else
                {
                    panel.Disable();
                }

                if (i == 0 || i == 4 || i == 7 || i == 9)
                {
                    var label = new Label();
                    label.ForeColor = Color.Red;
                    label.Font = new Font("맑은 고딕", 12);
                    label.Text = $"{count + 1}. {labelName[count]}";
                    ++count;
                    tableLayoutPanel2.Controls.Add(label);
                    totalHeight += label.Height;
                }
                totalHeight += panel.Height;

                tableLayoutPanel2.Controls.Add(panel);
                if (i == 3 || i == 6 || i == 8 || i == 10)
                {
                    var label = new Label();
                    label.AutoSize = false;
                    var size = i != 10 ? tableLayoutPanel2.Height - totalHeight : tableLayoutPanel2.Height - totalHeight - estimate.Height;
                    label.Height = size;
                    label.Dock = DockStyle.Fill;
                    label.BackColor = Color.Black;
                    tableLayoutPanel2.Controls.Add(label);
                    totalHeight = 0;
                }
            }
            tableLayoutPanel2.Controls.Add(estimate);
            tableLayoutPanel2.Invalidate();
        }

        byte[] getDB(int ono)
        {
            return DB.getBytes("select o_img from [option] where o_no = @1", ono);
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }

        private void tableLayoutPanel2_Scroll(object sender, ScrollEventArgs e)
        {
            //Console.WriteLine(e.NewValue.ToString()); //0 1052 2103 3142
        }

        private void label4_Click(object sender, EventArgs e)
        {
            tableLayoutPanel2.VerticalScroll.Value = 0;
            label4.ForeColor = Color.White;
            label6.ForeColor = Color.Gray;
            label8.ForeColor = Color.Gray;
            label10.ForeColor = Color.Gray;
        }

        private void label6_Click(object sender, EventArgs e)
        {
            tableLayoutPanel2.VerticalScroll.Value = 1052;
            label4.ForeColor = Color.Gray;
            label6.ForeColor = Color.White;
            label8.ForeColor = Color.Gray;
            label10.ForeColor = Color.Gray;
        }

        private void label8_Click(object sender, EventArgs e)
        {
            tableLayoutPanel2.VerticalScroll.Value = 2103;
            label4.ForeColor = Color.Gray;
            label6.ForeColor = Color.Gray;
            label8.ForeColor = Color.White;
            label10.ForeColor = Color.Gray;
        }

        private void label10_Click(object sender, EventArgs e)
        {
            tableLayoutPanel2.VerticalScroll.Value = 3142;
            label4.ForeColor = Color.Gray;
            label6.ForeColor = Color.Gray;
            label8.ForeColor = Color.Gray;
            label10.ForeColor = Color.White;
        }

        private void Estimate_SizeChanged(object sender, EventArgs e)
        {
            reload();
        }
    }
}
