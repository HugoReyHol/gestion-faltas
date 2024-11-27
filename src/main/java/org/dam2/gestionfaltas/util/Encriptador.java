package org.dam2.gestionfaltas.util;

import org.apache.commons.codec.digest.DigestUtils;


public class Encriptador {
    public static String encriptar(String cadena) {
        return DigestUtils.sha256Hex(cadena);

    }
}


