using Gangwon.Utils;
using System.Drawing;
using System.Windows.Forms;

namespace Gangwon.ChildPanel
{
    public partial class ResultPrice : UserControl
    {

        Estimate es;
        public ResultPrice(Estimate es)
        {
            InitializeComponent();
            Dock = DockStyle.Fill;
            Paint += (s, e) =>
            {
                e.Graphics.DrawLine(new Pen(Color.White), 0, 0, Width, 0);
            };
            this.es = es;
        }

        static long p1;
        static int p2;
        static int p3;
        static int p4;
        static int p5;
        public void setNormalPrice(long price)
        {
            p1 = price;
            label2.Text = $"기본가격 + {p1:N0} 원";
            setResult();
        }
        public void setWheelPrice(int price)
        {
            p2 = price;
            label3.Text = $"휠 + {p2:N0} 원";
            setResult();
        }
        public void setBodyPrice(int price)
        { 
            p3 = price;
            label4.Text = $"차체 + {p3:N0} 원";
            setResult();
        }
        public void setEnginePrice(int price)
        {
            p4 = price;
            label5.Text = $"엔진 + {p4:N0} 원";
            setResult();
        }
        public void setSheetPrice(int price) 
        { 
            p5 = price;
            label6.Text = $"시트 + {p5:N0} 원";
            setResult();
        }
        public void setResult()
        {
            long cal = p1 + p2 + p3 + p4 + p5;
            label7.Text = $"{cal:N0}원";
        }

        long result;
        private void button2_Click(object sender, System.EventArgs e)
        {
            result = p1 + p2 + p3 + p4 + p5;
            DB.DML("insert into estimate values (@1, @2, @3, @4, @5, @6, @7)", Login.no, Estimate.c_no, Estimate.op1, Estimate.op2, Estimate.op3, Estimate.op4, result);
            Msg.ok("견적서가 저장되었습니다.");
            es.Close();
            new CarInfo(Estimate.c_no).Show();
        }

        private void button1_Click(object sender, System.EventArgs e)
        {
            es.Close();
            result = p1 + p2 + p3 + p4 + p5;
            byte[] getCarImage = DB.getBytes("select c_img from car where c_no = @1", Estimate.c_no);
            new CheckPurchase(result, getCarImage).Show();
        }
    }
}
