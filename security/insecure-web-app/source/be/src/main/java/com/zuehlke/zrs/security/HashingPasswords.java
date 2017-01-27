//package com.zuehlke.zrs.security;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by krst on 1/13/2017.
// */
//public class HashingPasswords {
//
//    public static void main(String [ ] args){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        List<String> lines = Arrays.asList("user "+passwordEncoder.encode("pass") + " USER", "admin "+passwordEncoder.encode("pass") + " ADMIN");
//        Path file = Paths.get("cryptedCredentials.txt");
//        try {
//            Files.write(file, lines, Charset.forName("UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
