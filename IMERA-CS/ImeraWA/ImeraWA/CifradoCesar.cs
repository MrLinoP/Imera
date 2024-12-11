using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ImeraWA
{
    public class CifradoCesar
    {
        public static string Cifrar(string s)
        {
            char[] cifrado = s.ToCharArray();
            for (int i = 0; i < cifrado.Length; i++)
            {
                if (char.IsLetter(cifrado[i]))
                {
                    char offset = char.IsUpper(cifrado[i]) ? 'A' : 'a';
                    cifrado[i] = (char)(((cifrado[i] - offset + 3) % 26) + offset);
                }
            }
            return new string(cifrado);
        }

        public static string Descifrar(string s)
        {
            char[] descifrado = s.ToCharArray();
            for (int i = 0; i < descifrado.Length; i++)
            {
                if (char.IsLetter(descifrado[i]))
                {
                    char offset = char.IsUpper(descifrado[i]) ? 'A' : 'a';
                    descifrado[i] = (char)(((descifrado[i] - offset - 3 + 26) % 26) + offset);
                }
            }
            return new string(descifrado);
        }
    }
}