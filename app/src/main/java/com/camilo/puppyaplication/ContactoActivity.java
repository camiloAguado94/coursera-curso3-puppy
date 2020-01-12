package com.camilo.puppyaplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    String correoRegex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    TextInputEditText tiNombre;
    TextInputEditText tiCorreo;
    TextInputEditText tiComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar actionBar = findViewById(R.id.mi_action_bar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void enviarComentario(View v) {
        tiNombre = (TextInputEditText) findViewById(R.id.ti_nombre);
        tiCorreo = (TextInputEditText) findViewById(R.id.ti_correo);
        tiComentario = (TextInputEditText) findViewById(R.id.ti_comentario);

        String correo = tiCorreo.getText().toString();
        String nombre = tiNombre.getText().toString();
        String comentario = tiComentario.getText().toString();

        Pattern pattern = Pattern.compile(correoRegex);
        Matcher matcher = pattern.matcher(correo);
        if (!matcher.matches()) {
            tiCorreo.setError("Correo no válido");
        } else {
            AsyncTask.execute(() -> {
                        Properties props = new Properties();
                        props.setProperty("mail.transport.protocol", "smtp");
                        props.setProperty("mail.host", "smtp.gmail.com");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.socketFactory.fallback", "false");
                        props.setProperty("mail.smtp.quitwait", "false");
                        Session session = Session.getInstance(props, null);

                        try {
                            MimeMessage msg = new MimeMessage(session);
                            msg.setFrom(correo);
                            msg.setRecipients(Message.RecipientType.TO,
                                    correo);
                            msg.setSubject(nombre + ": " + getString(R.string.correo_subject));
                            msg.setSentDate(new Date());
                            msg.setText(comentario);
                            Transport.send(msg, getString(R.string.correo_usr), getString(R.string.correo_pwd));
                            Snackbar.make(v, "Correo enviado exitosamente.", Snackbar.LENGTH_LONG).show();
                        } catch (Exception mex) {
                            mex.printStackTrace();
                            Snackbar.make(v, "Error enviando el correo.", Snackbar.LENGTH_LONG).show();
                        }
                    }
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
