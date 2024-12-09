using JeonNam.ChildPanel;
using JeonNam.Utils;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JeonNam
{
    public partial class Setting : UserControl
    {
        public Setting()
        {
            InitializeComponent();

            var objs = new GraphicsPath();
            objs.AddEllipse(0, 0, pictureBox1.Width - 1, pictureBox1.Height - 1);
            var rg = new Region(objs);
            pictureBox1.Region = rg;

            var obj = DB.GetObject("select u.Name, point, sum(directAmount), sum(totalAmount) from [donate] d\r\njoin [user] u on d.User_no = u.No\r\nwhere date between '2024-08-01' and '2024-08-31' and user_no = @1\r\ngroup by point, u.Name", Login.no);
            if (obj.Read())
            {
                label2.Text = $"\\ {Convert.ToInt32(obj[1]):N0}";
                label4.Text = $"\\ {Convert.ToInt32(obj[2]):N0}";
                label6.Text = $"\\ {Convert.ToInt32(obj[3]):N0}";
                label12.Text = $"\\ {Convert.ToInt32(obj[3]):N0}";
                label14.Text = obj[0].ToString();
            }

            var list = DB.GetNowMonthGiveList();
            for (int i = 0; i < list.Count; i++)
            {
                tableLayoutPanel1.RowCount++;
                if ((i + 1) % 4 == 0)
                {
                    tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 100));
                }
                tableLayoutPanel1.Controls.Add(new PaymentInfo($"datafiles/목표/{list[i][0]}.png", list[i][1].ToString(), $"\\ {Convert.ToInt32(list[i][2]):N0}"));
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            tableLayoutPanel1.RowCount = 1;
            tableLayoutPanel1.Controls.Clear();
            tableLayoutPanel1.RowStyles.Clear();
            ToggleButtonState(button1, button2);
            var list = DB.GetNowMonthGiveList();
            for (int i = 0; i < list.Count; i++)
            {
                tableLayoutPanel1.RowCount++;
                if ((i + 1) % 4 == 0)
                {
                    tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 100));
                }
                tableLayoutPanel1.Controls.Add(new PaymentInfo($"datafiles/목표/{list[i][0]}.png", list[i][1].ToString(), $"\\ {Convert.ToInt32(list[i][2]):N0}"));
            }
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 100));
            tableLayoutPanel1.Invalidate();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            tableLayoutPanel1.RowCount = 1;
            tableLayoutPanel1.Controls.Clear();
            tableLayoutPanel1.RowStyles.Clear();
            ToggleButtonState(button2, button1);
            var list = DB.GetAllGiveList();
            for (int i = 0; i < list.Count; i++)
            {
                tableLayoutPanel1.RowCount++;
                if ((i + 1) % 4 == 0)
                {
                    tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 100));
                }
                tableLayoutPanel1.Controls.Add(new PaymentInfo($"datafiles/목표/{list[i][0]}.png", list[i][1].ToString(), $"\\ {Convert.ToInt32(list[i][2]):N0}"));
            }
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 100));
            tableLayoutPanel1.Invalidate();
        }

        private void ToggleButtonState(Button activeButton, Button inactiveButton)
        {
            activeButton.BackColor = Color.RoyalBlue;
            activeButton.ForeColor = Color.White;

            inactiveButton.BackColor = Color.White;
            inactiveButton.ForeColor = Color.RoyalBlue;
        }

        private void pictureBox1_DoubleClick(object sender, EventArgs e)
        {
            var dialog = new OpenFileDialog();
            dialog.Title = "열기";
            dialog.Filter = "(*.png; *.jpg) | *.png; *.jpg";

            var dr = dialog.ShowDialog();
            if (dr == DialogResult.OK)
            {
                string fullPath = dialog.FileName;
                using (var fs = new FileStream(fullPath, FileMode.Open, FileAccess.Read))
                {
                    var img = Image.FromStream(fs);
                    pictureBox1.Image = new Bitmap(img);
                }

                var obj = new GraphicsPath();
                obj.AddEllipse(0, 0, pictureBox1.Width - 1, pictureBox1.Height - 1);
                var rg = new Region(obj);
                pictureBox1.Region = rg;

                string newFileName = $"{Login.no}.jpg";
                string directoryPath = Path.GetDirectoryName(fullPath);
                string newFileNamePath = Path.Combine(directoryPath, newFileName);

                string copyFilePath = Path.Combine("datafiles/프로필/", newFileName);
                try
                {
                    if (File.Exists(newFileNamePath)) File.Delete(newFileNamePath);
                    File.Move(fullPath, newFileNamePath);
                    File.Copy(newFileNamePath, copyFilePath, true);
                }
                catch (Exception e1)
                {
                    Console.WriteLine(e1.Message);
                }
            }
        }
    }
}
