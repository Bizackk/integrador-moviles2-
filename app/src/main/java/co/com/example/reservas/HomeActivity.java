package co.com.example.reservas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentUsername = getIntent().getStringExtra("username");

        Button btnOption1 = findViewById(R.id.btnOption1);
        Button btnOption2 = findViewById(R.id.btnOption2);
        Button btnOption3 = findViewById(R.id.btnOption3);
        Button btnOption4 = findViewById(R.id.btnOption4);
        Button btnOption5 = findViewById(R.id.btnOption5);
        Button btnOption6 = findViewById(R.id.btnOption6);
        Button btnBack = findViewById(R.id.btnBack);

        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation("10:00 AM", "01/01/2023");
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation("11:00 AM", "01/01/2023");
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation("12:00 PM", "01/01/2023");
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation("01:00 PM", "01/01/2023");
            }
        });

        btnOption5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation("02:00 PM", "01/01/2023");
            }
        });

        btnOption6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation("03:00 PM", "01/01/2023");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void makeReservation(String time, String date) {
        if (currentUsername == null) {
            Toast.makeText(HomeActivity.this, "Error: Usuario no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference reservationRef = mDatabase.child("reservations").push();
        Map<String, String> reservationData = new HashMap<>();
        reservationData.put("username", currentUsername);
        reservationData.put("time", time);
        reservationData.put("date", date);

        reservationRef.setValue(reservationData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(HomeActivity.this, "Reserva guardada exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HomeActivity.this, "Error al guardar la reserva: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
