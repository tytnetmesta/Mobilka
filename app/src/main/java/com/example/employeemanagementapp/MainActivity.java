package com.example.employeemanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.employeemanagementapp.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etEmployeeName, etEmployeePosition;
    private Button btnAddEmployee;
    private ListView lvEmployees;
    private List<Employee> employeeList; // Список сотрудников
    private ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация элементов интерфейса
        etEmployeeName = findViewById(R.id.etEmployeeName);
        etEmployeePosition = findViewById(R.id.etEmployeePosition);
        btnAddEmployee = findViewById(R.id.btnAddEmployee);
        lvEmployees = findViewById(R.id.lvEmployees);

        // Инициализация списка сотрудников
        employeeList = new ArrayList<>();
        populateMockData(); // Метод для добавления данных-заглушек

        // Инициализация адаптера для ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        lvEmployees.setAdapter(adapter);

        // Обработчик нажатия кнопки для добавления нового сотрудника
        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    // Метод для добавления данных-заглушек
    private void populateMockData() {
        employeeList.add(new Employee(1, "Alice Johnson", "Manager"));
        employeeList.add(new Employee(2, "Bob Smith", "Developer"));
        employeeList.add(new Employee(3, "Carol Williams", "Designer"));
    }

    // Метод для добавления нового сотрудника в список
    private void addEmployee() {
        String name = etEmployeeName.getText().toString().trim();
        String position = etEmployeePosition.getText().toString().trim();

        if (name.isEmpty() || position.isEmpty()) {
            Toast.makeText(this, "Введите имя и должность", Toast.LENGTH_SHORT).show();
            return;
        }

        // Добавление нового сотрудника
        Employee newEmployee = new Employee(employeeList.size() + 1, name, position);
        employeeList.add(newEmployee);
        adapter.notifyDataSetChanged(); // Обновление ListView

        // Очистка полей ввода
        etEmployeeName.setText("");
        etEmployeePosition.setText("");
        Toast.makeText(this, "Сотрудник добавлен!", Toast.LENGTH_SHORT).show();
    }
}
