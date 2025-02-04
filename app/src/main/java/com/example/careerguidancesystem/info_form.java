package com.example.careerguidancesystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.careerguidancesystem.R;

import java.util.ArrayList;
import java.util.List;

public class info_form extends AppCompatActivity {

    private EditText etStudentName, etAddress, etClass10Marks, etClass12Marks, etHobbies;
    private Spinner spinnerClass;
    private Button btnAddSubject, btnSubmit;
    private LinearLayout subjectContainer;
    private List<EditText> subjectFields = new ArrayList<>();
    private List<EditText> marksFields = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_form);

        // Initialize fields
        etStudentName = findViewById(R.id.et_student_name);
        etAddress = findViewById(R.id.et_address);
        etHobbies = findViewById(R.id.et_hobbies);  // New field for hobbies or interests
        spinnerClass = findViewById(R.id.spinner_class);
        btnAddSubject = findViewById(R.id.btn_add_subject);
        btnSubmit = findViewById(R.id.btn_submit);
        subjectContainer = findViewById(R.id.subject_container);

        // Add subject dynamically when the button is clicked
        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubjectField();
            }
        });

        // Submit the form
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect the form data
                String studentName = etStudentName.getText().toString();
                String address = etAddress.getText().toString();
                String class10Marks = etClass10Marks.getText().toString();
                String class12Marks = etClass12Marks.getText().toString();
                String hobbies = etHobbies.getText().toString();  // Collect hobbies or interests
                String studentClass = spinnerClass.getSelectedItem().toString();

                // Collect subject and marks data
                List<String> subjects = new ArrayList<>();
                List<String> marks = new ArrayList<>();
                for (int i = 0; i < subjectFields.size(); i++) {
                    subjects.add(subjectFields.get(i).getText().toString());
                    marks.add(marksFields.get(i).getText().toString());
                }

                // Validation and form submission
                if (studentName.isEmpty() || address.isEmpty() || class10Marks.isEmpty() || class12Marks.isEmpty() || hobbies.isEmpty() || subjects.isEmpty() || marks.isEmpty()) {
                    Toast.makeText(info_form.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Display or process the collected data
                    Toast.makeText(info_form.this, "Form Submitted!", Toast.LENGTH_SHORT).show();
                    // You can now send this data to your backend or database
                }
            }
        });
    }

    // Method to dynamically add subject and marks fields
    private void addSubjectField() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        // Subject Name field
        EditText etSubject = new EditText(this);
        etSubject.setHint("Subject");
        etSubject.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        subjectFields.add(etSubject);
        layout.addView(etSubject);

        // Marks field
        EditText etMarks = new EditText(this);
        etMarks.setHint("Marks");
        etMarks.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        etMarks.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        marksFields.add(etMarks);
        layout.addView(etMarks);

        // Add the layout with the new fields to the container
        subjectContainer.addView(layout);
    }
}
