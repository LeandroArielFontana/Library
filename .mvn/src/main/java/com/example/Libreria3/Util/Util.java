package com.example.Libreria.Util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static Boolean checkWhiteSpace (String palabra){ //ANDA - DA TRUE SI HAY ESPACIO EN BLANCO
        Pattern pattern = Pattern.compile("[\\s]");
        Matcher matcher = pattern.matcher(palabra);
        boolean find = matcher.find();
        return find;
    }

    public static Boolean checkISBN (Long isbn){ //ANDA - DA TRUE SI EL ISBN ES CORRECTO
        String isbnString = String.valueOf(isbn);
        Pattern pattern = Pattern.compile("^[0-9]{10,13}$");
        Matcher matcher = pattern.matcher(isbnString);
        boolean find = matcher.find();
        return find;
    }

    public static Boolean checkNames(String palabra){ //ANDA - DA TRUE SI ESTA BIEN EL NOMBRE (SIN NUM)
        Pattern pattern = Pattern.compile("^([a-zA-Z]{1}[a-zñáéíóú]+[\\s]*)+$");
        Matcher matcher = pattern.matcher(palabra);
        boolean find = matcher.find();
        return find;
    }

    public static Boolean checkPhoneNumber(String palabra){ //ANDA DA TRUE SI EL TELEFONO ES VALIDO
        Pattern pattern = Pattern.compile("^[0-9]{8,10}$");
        Matcher matcher = pattern.matcher(palabra);
        boolean find = matcher.find();
        return find;
    }

    public static Boolean checkDates(Date borrowingDate, Date returnDate) {
        if (borrowingDate.before(returnDate)){
            return false;
        }else{
            return true;
        }
    }

    public static Boolean checkDNI(Long DNI){ //ANDA DA TRUE SI EL DNI ES VALIDO
        String dniString = String.valueOf(DNI);
        Pattern pattern = Pattern.compile("^[0-9]{7,9}$");
        Matcher matcher = pattern.matcher(dniString);
        boolean find = matcher.find();
        return find;
    }

}
