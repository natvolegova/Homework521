package com.example.homework521;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class FileHelper {
    private Context context;

    public FileHelper(Context context) {
        this.context = context;
    }

    //создаем системный файл для хранения данных
    public void createFile(String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //обновляем данные
    public void updateValue(String fileName, String value) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            bw.write(value);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //получаем данные из файла
    public String getValue(String fileName) {
        String value = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(fileName));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            value = reader.readLine();

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
