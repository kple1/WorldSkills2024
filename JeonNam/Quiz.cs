using JeonNam.Utils;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace JeonNam
{
    public partial class Quiz : Form
    {
        int stickLength;
        List<int> findMin = new List<int>();
        public Quiz()
        {
            InitializeComponent();
            Icon = FormIcon.Set();
            pictureBox1.Image = Image.FromFile("datafiles/퀴즈.png");
            pictureBox2.Image = Image.FromFile("datafiles/자동차.png");
            pictureBox1.Controls.Add(pictureBox2);

            var r = new Random();
            stickLength = r.Next(1, 49);

            int cuttingLocationMaxValue = r.Next(2, 8);
            List<int> noDistinct = new List<int>();
            int count = 0;
            while (true)
            {
                int choose = r.Next(1, stickLength);
                if (count != cuttingLocationMaxValue)
                {
                    if (!noDistinct.Contains(choose))
                    {
                        noDistinct.Add(choose);
                        ++count;
                    }
                }
                else
                {
                    break;
                }
            }
            Permute(noDistinct, 0);

            textBox1.Text = stickLength.ToString();
            textBox2.Text = string.Join(",", noDistinct);
        }

        void Permute(List<int> arr, int k)
        {
            if (k == arr.Count - 1)
            {
                int result = 0;
                int before = stickLength;
                for (int i = 0; i < arr.Count(); i++)
                {
                    if (i == 0)
                    {
                        result += stickLength;
                    }
                    else
                    {
                        result += before - arr[i - 1];

                        before -= arr[i - 1];

                    }
                }
                findMin.Add(result);
            }
            else
            {
                for (int i = 0; i < arr.Count; i++)
                {
                    Swap(arr, i, k);
                    Permute(arr, k + 1);
                    Swap(arr, i, k);
                }
            }
        }

        void Swap(List<int> arr, int i, int j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        Timer timer;
        int x = 25;
        int y = 99;
        private void button1_Click(object sender, EventArgs e)
        {
            if (!textBox3.Text.Equals(findMin.Min().ToString()))
            {
                Msg.fail("최소 비용이 아닙니다.");
            }
            else
            {
                Msg.ok("최소 비용이 맞습니다.");
                if (Login.no == 0)
                {
                    Msg.fail("로그인을 하지 않아 적립금을 받을 수 없습니다.");
                    new Quiz().Show();
                    Close();
                }
                else
                {
                    timer = new Timer();
                    timer.Interval = 10;
                    timer.Tick += (s1, e1) =>
                    {
                        x += 2;
                        if (x >= 806)
                        {
                            timer.Stop();
                            new Coin(25, 25, this).Show();
                        }
                        pictureBox2.Location = new Point(x, y);
                    };
                    timer.Start();
                }
            }
        }
    }
}
