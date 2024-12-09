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
    public partial class FinalVerfy : Form
    {
        Timer timer;
        int min = 3;
        int sec = 0;
        public static bool successVefy = false;
        public FinalVerfy()
        {
            InitializeComponent();
            timer = new Timer();
            timer.Interval = 1000;
            timer.Tick += (s, e) =>
            {
                if (sec == 0)
                {
                    min -= 1;
                    sec = 59;
                }
                else
                {
                    --sec;
                }
                if (min == 0)
                {
                    timer.Stop();
                    Close();
                    new SecondIdentityVerfication().Show();
                }
                label2.Text = $"{min:N0}:{sec:N0}";
            };
            timer.Start();
        }

        private void label3_Click(object sender, EventArgs e)
        {
            label2.Text = "3:00";
            min = 3;
            sec = 0;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
            new SecondIdentityVerfication().Show();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (SecondIdentityVerfication.verfyCode != Convert.ToInt32(textBox1.Text))
            {
                Msg.fail("인증번호가 일치하지 않습니다.");
            }
            else
            {
                Msg.ok("본인인증이 완료되었습니다.");
                Close();
                new Login().Show();
                successVefy = true;
            }
        }
    }
}
