package com.example.demo.service.impl;

import com.example.demo.service.FileUploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.demo.FileUploader.ABSOLUTE_FILE_PATH;

public class DefaultFileUploader implements FileUploader {


    @Override
    public void uploadFile() throws IOException {
        File file = new File(ABSOLUTE_FILE_PATH);
        StringBuilder stringBuilderFromFile = new StringBuilder();
        Scanner scanner = new Scanner(file);
        stringBuilderFromFile.append(scanner.nextLine());
        while (scanner.hasNextLine()) {
            stringBuilderFromFile.append(scanner.nextLine());
        }
        String stringFromFile = String.valueOf(stringBuilderFromFile);
        char[] charArray = stringFromFile.toCharArray();
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder StringOfChars = new StringBuilder();
        int countAllChar = 0;
        int countCharInString = 0;

        while (countAllChar < charArray.length) {
            StringOfChars.append(charArray[countAllChar]);
            countAllChar++;
            countCharInString++;
            while (countCharInString == 80) {
                arrayList.add(StringOfChars.toString());
                StringOfChars.delete(0, StringOfChars.length());
                countCharInString = 0;
            }
        }
        arrayList.add(StringOfChars.toString());

        FileWriter fileWriter = new FileWriter(file);
        for (int i = 0; i < arrayList.size(); i++) {
            fileWriter.write(arrayList.get(i) + "\n");
        }
        fileWriter.close();
    }
}
