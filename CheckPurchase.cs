using Gangwon.Utils;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;

namespace Gangwon
{
    public partial class CheckPurchase : Form
    {
        long carPrice;
        byte[] carImage;
        public CheckPurchase(long carPrice, byte[] carImage)
        {
            this.carImage = carImage;
            this.carPrice = carPrice;
            InitializeComponent();
            pictureBox1.Image = Image.FromFile("datafiles/image/logo.png");
        }

        private void label2_Click(object sender, EventArgs e)
        {
            var dialog = MessageBox.Show("종료 하시겠습니까?", "확인", MessageBoxButtons.OK, MessageBoxIcon.Question);
            if (dialog == DialogResult.OK)
            {
                Close();
            }
        }

        int h;
        int w;
        Point point;
        PictureBox[] pcList = new PictureBox[6];
        Dictionary<int, Bitmap> beforeDic = new Dictionary<int, Bitmap>();
        Dictionary<int, Bitmap> afterDic = new Dictionary<int, Bitmap>();
        private void CheckPurchase_Load(object sender, EventArgs e)
        {
            h = Height;
            w = Width;

            var stream = new MemoryStream(carImage);
            Image img = new Bitmap(stream);

            int widthThird = (int)(img.Width / 3.0);
            int heightThird = (int)(img.Height / 2.0);
            var bmps = new List<Bitmap>();
            var list = new Bitmap[2, 3];
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    list[i, j] = new Bitmap(widthThird, heightThird);
                    bmps.Add(list[i, j]);
                    Graphics g = Graphics.FromImage(list[i, j]);
                    g.DrawImage(img, new Rectangle(0, 0, widthThird, heightThird), new Rectangle(j * widthThird, i * heightThird, widthThird, heightThird), GraphicsUnit.Pixel);
                    g.Dispose();
                }
            }

            for (int i = 0; i < 6; i++)
            {
                beforeDic.Add(i, bmps[i]);
            }

            var random = new Random();
            shuffled = beforeDic.OrderBy(_ => random.Next()).ToList();
            for (int i = 0; i < 6; i++)
            {
                afterDic.Add(i, shuffled[i].Value);
            }

            reload();
        }

        List<KeyValuePair<int, Bitmap>> shuffled;
        bool firstLoad = true;
        void reload()
        {
            panel2.Controls.Clear();
            int x = 0;
            int y = 0;
            for (int i = 0; i < 6; i++)
            {
                pcList[i] = new PictureBox();
            }

            var pcLocation = new Dictionary<int, Point>();
            for (int i = 0; i < 6; i++)
            {
                int capture = i;

                pcList[i].Location = new Point(x, y);
                pcLocation.Add(i, new Point(x, y));

                if ((i + 1) % 3 == 0)
                {
                    y += h / 2; x = 0;
                }
                else
                {
                    x += w / 3;
                }

                pcList[i].Paint += (s, e1) =>
                {
                    var g = e1.Graphics;
                    g.DrawRectangle(new Pen(Color.White, 1), new Rectangle(0, 0, pcList[capture].Width - 1, pcList[capture].Height - 1));
                };

                pcList[i].Size = new Size(w / 3, h / 2);
                pcList[i].MouseDown += (s, e1) =>
                {
                    point = e1.Location;
                };

                int pcX = 0;
                int pcY = 0;
                int findIndex = -1;
                pcList[i].MouseMove += (s, e1) =>
                {
                    pcX = pcList[capture].Left - (point.X - e1.X);
                    pcY = pcList[capture].Top - (point.Y - e1.Y);
                    if (e1.Button == MouseButtons.Left)
                    {
                        pcList[capture].Location = new Point(pcX, pcY);
                    }

                    for (int j = 0; j < 6; j++)
                    {
                        if (pcLocation[j].X <= pcX && pcLocation[j].Y <= pcY && pcLocation[j].X + pcList[j].Width - 100 >= pcX && pcLocation[j].Y + pcList[j].Height - 100 >= pcY)
                        {
                            findIndex = j;
                            break;
                        }
                    }
                };

                pcList[i].MouseUp += (s, e1) =>
                {
                    try
                    {
                        var pSave1 = pcList[findIndex];
                        var pSave2 = pcList[capture];
                        pcList[capture].Location = pcLocation[findIndex];
                        pcList[findIndex].Location = pcLocation[capture];
                        pcList[capture] = pSave1;
                        pcList[findIndex] = pSave2;

                        var save1 = afterDic[capture];
                        var save2 = afterDic[findIndex];
                        afterDic[capture] = save2;
                        afterDic[findIndex] = save1;

                        reload();

                        bool isOk = true;
                        for (int k = 0; k < 6; k++) {
                            if (!beforeDic[k].Equals(afterDic[k]))
                            {
                                isOk = false;
                            }
                        }
                        if (isOk)
                        {
                            Msg.ok("구매가 완료되었습니다.");
                            DB.DML("insert into purchase values (@1, @2, @3, @4, @5, @6, @7, @8)", Login.no, Estimate.c_no, Estimate.op1, Estimate.op2 - 4, Estimate.op3 - 7, Estimate.op4 - 9, carPrice, DateTime.Now.ToString("yyyy-MM-dd"));
                            Close();
                            new CarInfo(Estimate.c_no).Show();
                        }
                    }
                    catch
                    {

                    }
                };
                pcList[i].SizeMode = PictureBoxSizeMode.StretchImage;
                if (firstLoad)
                {
                    pcList[i].Image = shuffled[i].Value;
                }
                else
                {
                    pcList[i].Image = afterDic[i];
                }
                panel2.Controls.Add(pcList[i]);
            }
            panel2.Invalidate();
            firstLoad = false;
        }
    }
}