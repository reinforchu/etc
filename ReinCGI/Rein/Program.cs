using System;
using System.IO;
using System.Text;

namespace Rein
{
    class Rein
    {
        static void Main(string[] args)
        {
            System.Console.Write("content-type: text/html\n");
            System.Console.Write("\n");
            foreach (string arg in args) {
                StreamReader sr = new StreamReader(arg, Encoding.GetEncoding("shift_jis"));
                string text = sr.ReadToEnd();
                sr.Close();
                System.Console.Write(text);
            }
        }
    }
}