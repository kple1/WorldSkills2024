using JeonNam.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JeonNam
{
    public partial class GoalInfo : Form
    {
        public GoalInfo(string num, string kor, string eng, string path1, string path2, string keycontent, string content)
        {
            InitializeComponent();
            Icon = FormIcon.SetGoal(path1);
            Text = kor;

            pictureBox1.Image = Image.FromFile(path1);
            pictureBox2.Image = Image.FromFile(path2);
            label1.Text = num;
            label2.Text = kor;
            label3.Text = eng;
            label4.Text = keycontent;
            label5.Text = content;

            pictureBox2.Controls.Add(label1);
            pictureBox2.Controls.Add(label2);
            pictureBox2.Controls.Add(label3);
            pictureBox2.Controls.Add(label4);
            pictureBox2.Controls.Add(label5);
        }

        private void button1_Click(object sender, EventArgs e)
        {

        }
    }
}
