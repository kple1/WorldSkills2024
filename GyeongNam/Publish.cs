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
    public partial class Publish : Form
    {
        public Publish()
        {
            InitializeComponent();

            Placeholder.Set(textBox1, "제목을 입력하세요");
            Placeholder.Set(textBox2, "내용을 입력해주세요.");

            panel1.Paint += (s, e) =>
            {
                var g = e.Graphics;
                g.DrawLine(new Pen(Color.Gray, 1), 0, 0, panel1.Width, 0);
                g.DrawLine(new Pen(Color.Gray, 1), 0, panel1.Height - 1, panel1.Width, panel1.Height - 1);
            };

            pictureBox1.Paint += (s, e) =>
            {
                var g = e.Graphics;
                g.DrawRectangle(new Pen(Color.Black, 1), 0, 0, pictureBox1.Width - 1, pictureBox1.Height - 1);
            };
            pictureBox1.Image = Image.FromFile("datafiles/icon/camera.png");

            string[] cList = new string[] { "경제", "사회", "국제", "스포츠", "IT", "생활/문화" };
            string[] pList = new string[] { "굴림", "맑은 고딕", "궁서체", "돋움" };
            for (int i = 0; i < cList.Length; i++)
            {
                comboBox1.Items.Add(cList[i]);
            }
            for (int i = 0; i < pList.Length; i++)
            {
                comboBox2.Items.Add(pList[i]);
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBox1.Text) || string.IsNullOrEmpty(textBox2.Text) || textBox1.Text.Equals("제목을 입력하세요") || textBox2.Text.Equals("내용을 입력해주세요."))
            {
                Msg.fail("빈칸이 있습니다.");
            }
            else if (textBox1.Text.Length <= 10 || textBox2.Text.Length <= 10)
            {
                Msg.fail("제목 또는 내용은 10자 이상으로 써주세요.");
            }
            else
            {
                DB.DML("insert into news values (@1, @2, @3, @4, @5, @6)", textBox1.Text, textBox2.Text, comboBox1.SelectedIndex + 1, Login.no, DateTime.Now.ToString("yyyyMMddHHmm"), 0);
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            var dialog = new OpenFileDialog();
            dialog.Title = "file explorer";
            dialog.Filter = "(*.jpg;*.png)|*.JPG;*.PNG";

            var open = dialog.ShowDialog();
            if (open == DialogResult.OK)
            {
                pictureBox1.Image = Image.FromFile(dialog.FileName);
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {
            //이전폼
        }
    }
}
