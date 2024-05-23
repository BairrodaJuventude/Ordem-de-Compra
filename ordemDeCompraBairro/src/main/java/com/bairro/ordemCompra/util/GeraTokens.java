package com.bairro.ordemCompra.util;

import java.security.SecureRandom;

public class GeraTokens {

    // Parte inicial da autenticação por token, ainda falta MUITA coisa
    public static String gerarToken() {
        int tokenTamanho = 64;
        char[] tokenChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tokenTamanho; i++) {
            int randomIndex = secureRandom.nextInt(tokenChars.length);
            stringBuilder.append(tokenChars[randomIndex]);
        }

        return stringBuilder.toString();
    }
}

