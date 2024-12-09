using Gangwon.ChildPanel;
using Gangwon.Utils;
using System;
using System.Drawing;
using System.IO;
using System.Text.RegularExpressions;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class Calendar : Form
    {
        int applyDrive;
        int c_no;
        public Calendar(int applyDrive, int c_no)
        {
            InitializeComponent();
            this.applyDrive = applyDrive;
            this.c_no = c_no;

            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");

            int month = DateTime.Now.Month;
            var now = DateTime.Parse($"2024-{month}-1");
            int dayInMonth = DateTime.DaysInMonth(2024, month);
            int beforeDayInMonth = DateTime.DaysInMonth(2024, month - 1);
            int getWeek = (int)now.DayOfWeek;

            label3.Text = $"2024년 {month}월";

            for (int i = 0; i < getWeek; i++)
            {
                tableLayoutPanel2.Controls.Add(new DayPanel(Color.DimGray, Color.DarkGray, beforeDayInMonth - i));
            }

            for (int i = 0; i < dayInMonth; i++)
            {
                if (i + 1 < DateTime.Now.Day)
                {
                    tableLayoutPanel2.Controls.Add(new DayPanel(Color.FromArgb(220, 20, 60), Color.White, i + 1));
                }
                else
                {
                    tableLayoutPanel2.Controls.Add(new DayPanel(Color.FromArgb(30, 145, 255), Color.White, i + 1));
                }
            }

            for (int i = 0; i < 42 - (dayInMonth + getWeek); i++)
            {
                tableLayoutPanel2.Controls.Add(new DayPanel(Color.DimGray, Color.DarkGray, i + 1));
            }
            reload();
        }

        int month = DateTime.Now.Month;
        void reload()
        {
            tableLayoutPanel2.Controls.Clear();
            var now = DateTime.Parse($"2024-{month}-1");
            int dayInMonth = DateTime.DaysInMonth(2024, month);
            int beforeDayInMonth = DateTime.DaysInMonth(2024, month - 1);
            int getWeek = (int)now.DayOfWeek;

            for (int i = 0; i < getWeek; i++)
            {
                var panel = new DayPanel(Color.DimGray, Color.DarkGray, beforeDayInMonth - i);
                tableLayoutPanel2.Controls.Add(panel);
            }

            for (int i = 0; i < dayInMonth; i++)
            {
                int capture = i;
                var newDate = new DateTime(2024, month, capture + 1);
                int currentMonth = DateTime.Now.Month;
                int currentDay = DateTime.Now.Day;
                Color redColor = Color.FromArgb(220, 20, 60);
                Color blueColor = Color.FromArgb(30, 145, 255);

                Color panelColor = currentMonth > month || (currentMonth == month && i + 1 < currentDay)
                    ? redColor
                    : blueColor;

                var panel = new DayPanel(panelColor, Color.White, i + 1);
                if (panelColor == redColor)
                {
                    panel.GetPictureBox().Click += (s, e) =>
                    {
                        Msg.fail("현재 날짜부터 선택 가능합니다.");
                    };
                }
                else
                {
                    var allDate = DB.Drive();

                    for (int k = 0; k < allDate.Count; k++) {
                        if (newDate.Equals(Convert.ToDateTime(allDate[k][3])))
                        {
                            var stream = new MemoryStream((byte[])allDate[k][2]);
                            panel.GetPictureBox().Image = new Bitmap(stream);
                        }
                    }

                    panel.GetPictureBox().Click += (s, e) =>
                    {
                        for (int j = 0; j < allDate.Count; j++)
                        {
                            if (Login.no.Equals(Convert.ToInt32(allDate[j][0])) && newDate.Equals(Convert.ToDateTime(allDate[j][3])))
                            {
                                Msg.fail("해당 날짜는 이미 다른 차량의 시승 일정이 있습니다.");
                                break;
                            }
                            else if (newDate.Equals(Convert.ToDateTime(allDate[j][3])))
                            {
                                Msg.fail("이미 시승 일정이 있는 날짜입니다.");
                                break;
                            }
                            else
                            {
                                if (applyDrive == 0)
                                {
                                    var check = MessageBox.Show("시승 신청을 하시겠습니까?", "정보", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                    if (check == DialogResult.OK)
                                    {
                                        DB.DML("INSERT INTO drive (u_no, c_no, d_date) values (@1, @2, @3)", Login.no, c_no, new DateTime(2024, month, capture).ToString("yyyy-MM-dd"));
                                        Close();
                                        new CarInfo(c_no).Show();
                                    }
                                }
                                else
                                {
                                    var check = MessageBox.Show("시승 일정을 변경하시겠습니까?", "정보", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                    if (check == DialogResult.OK)
                                    {
                                        DB.DML("update drive set d_date = @1 where u_no = @2 and c_no = @3", newDate, Login.no, c_no);
                                        Close();
                                        new MyPage().Show();
                                    }
                                }
                            }
                        }
                    };
                }
                
                tableLayoutPanel2.Controls.Add(panel);
            }

            for (int i = 0; i < 42 - (dayInMonth + getWeek); i++)
            {
                tableLayoutPanel2.Controls.Add(new DayPanel(Color.DimGray, Color.DarkGray, i + 1));
            }
            tableLayoutPanel2.Invalidate();
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }


        //label3제어 left
        private void button2_Click(object sender, EventArgs e)
        {
            if (month - 1 != 0) { --month; }
            label3.Text = $"2024년 {month}월";
            reload();
        }

        //label3제어 right
        private void button1_Click(object sender, EventArgs e)
        {
            if (month + 1 != 13) { ++month; }
            label3.Text = $"2024년 {month}월";
            reload();
        }
    }
}
