using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JeonNam.ChildPanel
{
    public partial class PaymentInfo : UserControl
    {
        string day;
        string money;
        public PaymentInfo(string path, string day, string money)
        {
            InitializeComponent();

            Dock = DockStyle.Fill;

            this.day = day;
            this.money = money;

            pictureBox1.Controls.Add(label1);
            label1.Dock = DockStyle.Fill;
            pictureBox1.Image = Image.FromFile(path);

            label1.Font = new Font("굴림", 9);
        }

        private void label1_MouseHover(object sender, EventArgs e)
        {
            Paint += painting;
            label1.BackColor = Color.FromArgb(150, Color.Gray);
            label1.Text = $"{day}\n{money}";
            Invalidate();
        }

        private void label1_MouseLeave(object sender, EventArgs e)
        {
            Paint -= painting;
            label1.BackColor = Color.Transparent;
            label1.Text = "";
            Invalidate();
        }

        void painting(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            g.DrawRectangle(new Pen(Color.Gray, 2), 0, 0, Width, Height);
        }
    }
}
