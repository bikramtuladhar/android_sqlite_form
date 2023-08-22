package np.com.bikramtuladhar.formwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FormPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_preview);

        Button back = findViewById(R.id.back);
        ListView list = findViewById(R.id.listView);

        try (DBHandler dbHandler = new DBHandler(FormPreviewActivity.this)) {
            String[] details = dbHandler.getAllEntries();
            ArrayAdapter<String> arr = new ArrayAdapter<String>(
                    this,
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    details);

            list.setAdapter(arr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        back.setOnClickListener(view -> {
            Intent intent = new Intent(FormPreviewActivity.this, MainActivity.class);

            startActivity(intent);
        });
    }
}