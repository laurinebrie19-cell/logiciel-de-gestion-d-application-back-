package com.stelotechConsulting.gestionMutuel.utilisateur.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;


    public void sendVerificationEmail(String to, String firstName, String userName, String verificationCode) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Votre code de vérification");
            helper.setFrom("noreply@example.com");

            String emailText = "<div style=\"font-family: Arial, sans-serif; font-size: 16px;\">"
                    + "<h2 style=\"font-size: 20px;\">Salut, Madame/Monsieur : <strong>" + verificationCode + "</strong></h2>"
                    + "<p style=\"font-size: 16px;\"><Nous vous envoyons ce courriel pour vous fournir les informations nécessaires à la finalisation de votre inscription. </p>"
                    + "<p><strong>Votre code de vérification est : </strong><span style=\"color: red;\"> <strong style=\"font-size: 18px;\"> " + firstName + "</strong></span></p>"
                    + "<br>"
                    + "<p>Cordialement,</p>"
                    + "<p>L'équipe de support</p>"


                    + "</div>";

            helper.setText(emailText, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void resetpasswordEmail(String to,  String userName, String verificationCode) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Votre code de vérification");
            helper.setFrom("noreply@example.com");

            String emailText = "<div style=\"font-family: Arial, sans-serif; font-size: 16px;\">"
                    + "<h2 style=\"font-size: 20px;\">Salut, Madame/Monsieur : <strong>" + verificationCode + "</strong></h2>"
                    + "<p style=\"font-size: 16px;\"><Nous vous envoyons ce courriel pour vous fournir les informations nécessaires à la finalisation de votre inscription. </p>"
                    + "<p style=\"font-size: 16px;\"> Veuillez trouver ci-dessous vos détails de connexion et votre code de vérification : </p>"
                    + "<p><strong>Votre code de vérification est : </strong><span style=\"color: red;\"> <strong style=\"font-size: 18px;\"> " + userName + "</strong></span></p>"
                    + "<br>"
                    + "<p>Cordialement,</p>"
                    + "<p>L'équipe de support</p>"


                    + "</div>";

            helper.setText(emailText, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendresetEmail(String to, String firstName, String verificationCode) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Votre code de vérification pour la réinitialisation du mot de passe");
            helper.setFrom("noreply@example.com");

            String emailText = "<div style=\"font-family: Arial, sans-serif; font-size: 16px;\">"
                    + "<h2 style=\"font-size: 20px;\">Salut, " + firstName + "</h2>"
                    + "<p>Nous avons reçu une demande pour réinitialiser votre mot de passe. Voici votre code de vérification :</p>"
                    + "<p><strong>Code de vérification : </strong><span style=\"color: red; font-size: 18px;\">" + verificationCode + "</span></p>"
                    + "<p>Si vous n'avez pas demandé cette réinitialisation, ignorez simplement cet e-mail.</p>"
                    + "<br>"
                    + "<p>Cordialement,</p>"
                    + "<p>L'équipe de support</p>"
                    + "</div>";

            helper.setText(emailText, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendCredentialsEmail(String to, String firstName, String userName, String password) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Vos informations de connexion");
            helper.setFrom("noreply@example.com");

            String emailText = "<div style=\"font-family: Arial, sans-serif; font-size: 16px;\">"
                    + "<h2 style=\"font-size:20px;\">Salut, Madame/Monsieur : <strong>" + firstName + "</strong></h2>"
                    + "<p style=\"font-size: 16px;\">Nous vous envoyons ce courriel pour vous fournir les informations nécessaires à la finalisation de votre inscription. </p>"
                    + "<p style=\"font-size: 16px;\"> Veuillez trouver ci-dessous vos détails de connexion :</p>"
                    + "<p><strong>Votre username est : </strong><span style=\"color: red;\"> <strong style=\"font-size: 18px;\">" + userName + "</strong></span></p>"
                    + "<p><strong>Votre mot de passe est : </strong><span style=\"color: red;\"> <strong style=\"font-size: 18px;\">" + password + "</strong></span></p>"
                    + "<br>"
                    + "<p>Cordialement,</p>"
                    + "<p>L'équipe de support</p>"
                    + "<br>"
                    + "<p style=\"font-size: 14px;\"><em><strong>NB : Veuillez modifier votre mot de passe dès que possible.</strong></em></p>"
                    + "</div>";

            helper.setText(emailText, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
