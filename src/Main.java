
import java.util.Scanner;


/**
 * Created by vitalii on 28.04.16.
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Количество строк первой матрицы: ");
        int a = in.nextInt();
        System.out.print("\n Количество столбцов первой матрицы: ");
        int b = in.nextInt();
        System.out.print("\n Количество столбцов второй матрицы: ");
        int c = in.nextInt();

        if(a>=1 && a<=2048 && b>=1 && b<=2048 && c>=1 && c<=2048) {
            int[][] A, B, C,reverseB;
            int i,j,k;
            A = new int[a][b];
            B = new int[b][c];
            reverseB = new int[b][c];
            C = new int[a][c];


            int halfA = a/2;


            System.out.println("Cоздание матрицы A....");
            for (i = 0; i < a; i++) {
                for (j = 0; j < b; j++) {
                    A[i][j] = (int) (Math.random() * 10);
      //             System.out.print(A[i][j] + (j == b - 1 ? "\n" : "\t"));
                }
            }

            System.out.println("\n Создание матрицы B.... ");
            for (i = 0; i < b; i++) {
                for (j = 0; j < c; j++) {
                    B[i][j] = (int) (Math.random() * 10);
      //           System.out.print(B[i][j] + (j == c - 1 ? "\n" : "\t"));
                }
            }

            System.out.println("\n Создание обёрнутой матрицы B.....");
            for(i=0;i<a;i++){
                for(j=0;j<b;j++){
                    reverseB[i][j]=B[j][i];
                }
            }

            long startTime = System.currentTimeMillis();

            Thread secondThread = new Thread(() -> {

                for (int i1 = halfA; i1 < a; i1++) {
                    for (int j1 = 0; j1 < c; j1++) {
                        for (int k1 = 0; k1 < b; k1++) {
                            C[i1][j1] += A[i1][k1] * reverseB[j1][k1];
                        }
                    }
                }
            });


            secondThread.start();

            System.out.println("\n Matrix C=(A*B): ");

            for (i = 0; i < halfA; i++) {
                for (j = 0; j < c ;j++) {
                    for (k = 0; k < b; k++) {
                        C[i][j] += A[i][k] * reverseB[j][k];
                    }
                }
            }
            try {
                secondThread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for ( i = 0; i < halfA; i++) {
                for ( j = 0; j < c; j++) {
                    System.out.print(C[i][j] + (j == c - 1 ? "\n" : "\t"));
                }
            }

            long timeSpent = System.currentTimeMillis() - startTime;

            System.out.println("\n " + timeSpent+" милисекунд");
        } else System.out.println("Ошибка. Введённое число должно находиться в диапазоне 1<x<2048 ");
    }
}