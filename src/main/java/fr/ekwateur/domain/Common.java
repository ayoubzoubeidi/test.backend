package fr.ekwateur.domain;

import java.util.regex.Pattern;

public class Common {
    public static Pattern CLIENT_REFERENCE_REGEX = Pattern.compile("^EKW\\d{8}$");
}
