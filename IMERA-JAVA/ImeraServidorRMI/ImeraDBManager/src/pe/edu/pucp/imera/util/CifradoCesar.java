/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.imera.util;

public class CifradoCesar {
    public static String cifrar(String s) {
        char[] cifrado = s.toCharArray();
        for (int i = 0; i < cifrado.length; i++) {
            if (Character.isLetter(cifrado[i])) {
                char offset = Character.isUpperCase(cifrado[i]) ? 'A' : 'a';
                cifrado[i] = (char)(((cifrado[i] - offset + 3) % 26) + offset);
            }
        }
        return new String(cifrado);
    }

    public static String descifrar(String s) {
        char[] descifrado = s.toCharArray();
        for (int i = 0; i < descifrado.length; i++) {
            if (Character.isLetter(descifrado[i])) {
                char offset = Character.isUpperCase(descifrado[i]) ? 'A' : 'a';
                descifrado[i] = (char)(((descifrado[i] - offset - 3 + 26) % 26) + offset);
            }
        }
        return new String(descifrado);
    }
}
