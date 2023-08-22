package np.com.bikramtuladhar.formwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.editTextUserName);
        EditText address = findViewById(R.id.editTextAddress);
        Button btn = findViewById(R.id.buttonSubmit);


        try (DBHandler dbHandler = new DBHandler(MainActivity.this)) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String fullNameValue = name.getText().toString();
                    String addressValue = address.getText().toString();

                    if (fullNameValue.isEmpty() && addressValue.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    dbHandler.addNewEntry(fullNameValue, addressValue);
                    Toast.makeText(MainActivity.this, "Details has been added.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, FormPreviewActivity.class);

                    name.setText("");
                    address.setText("");

                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}