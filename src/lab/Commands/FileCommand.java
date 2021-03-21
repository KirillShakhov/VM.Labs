package lab.Commands;

import lab.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileCommand implements ICommand {
    @Override
    public String getMessage() {
        return "Ввести матрицу с файла";
    }

    @Override
    public void execute() {
        System.out.println("Вводим матрицу с файла");
    }

    public Matrix readMatrixFromFile(String fileName) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(new File(fileName)));
            int size = Integer.parseInt(file.readLine().trim());
            double [][] matrix = new double[size][size + 1];
            for (int i = 0; i < size; i++) {
                String[] row = file.readLine().trim().split(" ");
                if (row.length > size + 1)
                    throw new ArrayIndexOutOfBoundsException();
                for (int j = 0; j < size + 1; j++) {
                    matrix[i][j] = Double.parseDouble(row[j].trim());
                }
            }
            return new Matrix(matrix);
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
        }
        return null;
    }
}
