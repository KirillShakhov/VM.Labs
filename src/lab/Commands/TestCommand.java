package lab.Commands;

import lab.MathModule;
import lab.Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TestCommand implements ICommand{
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getMessage() {
        return "test";
    }

    @Override
    public void execute() {
        String path = "C:\\Users\\kiril\\IdeaProjects\\VM.Lab1\\example\\matrix3";
        Matrix matrix = readMatrixFromFile(path);
        double eps = 0.0001;
        MathModule.findSolution(matrix, eps);
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
