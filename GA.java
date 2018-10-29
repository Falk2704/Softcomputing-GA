import java.util.*;
import java.math.*;

class GA {
    static int anz = 4; //Population
    static int gene = 4; //22
    static int pm = 1;

    private int[][] eltern = new int[anz][gene];
    private int[][] nachkommen = new int[anz][gene];
    private int[] besteLsg = new int[gene];
    float[] fitness = new float[anz];
    private float besteFitness = -9999999.9f;
    private int[][] data_a = {
            {-98,   98,     -59,    54},
            {0,     -13,    93,     -73},
            {0,     0,      86,     -96},
            {0,     0,      0,      90}
    };

    private int[][] data_b = {
            {-9,   44,     -85,    92},
            {0,     -8,    96,     -38},
            {0,     0,      -78,     17},
            {0,     0,      0,      85}
    };

    Random r = new Random(203);

    float berechneFitness(int elter) {
        float deciwert_a = 0;
        float deciwert_b = 0;

        for (int j = 0; j < anz; j++) {
            for (int k = 0; k < gene; k++) {
                deciwert_a += data_a[j][k] *  eltern[elter][k] * eltern[elter][j];
                deciwert_b += data_b[j][k] *  eltern[elter][k] * eltern[elter][j];
            }
        }
        float dualwert = deciwert_a + deciwert_b;
        return dualwert;
    }

    GA() {
        for (int i = 0; i < anz; i++) {
            for (int j = 0; j < gene; j++) {
                eltern[i][j] = Math.abs(r.nextInt()) % 2;

            }
            eltern[0][0] = 1;
            eltern[0][1] = 1;
            eltern[0][2] = 1;
            eltern[0][3] = 0;
            fitness[i] = berechneFitness(i);
            System.out.println(fitness[i]);
            System.exit(0);

            if (fitness[i] > besteFitness) {
                for (int j = 0; j < gene; j++) {
                    besteLsg[j] = eltern[i][j];
                }
                besteFitness = fitness[i];
                System.out.println("Beste Fitness= " + besteFitness);
            }
        }
    }

    void rekombinieren() {
        int elter1, elter2, indi1, indi2, trennstelle;

        for (int i = 0; i < anz; i++) {
            indi1 = Math.abs(r.nextInt()) % anz;
            indi2 = Math.abs(r.nextInt()) % anz;
            if (fitness[indi1] > fitness[indi2]) elter1 = indi1;
            else elter1 = indi2;

            indi1 = Math.abs(r.nextInt()) % anz;
            indi2 = Math.abs(r.nextInt()) % anz;
            if (fitness[indi1] > fitness[indi2]) elter2 = indi1;
            else elter2 = indi2;

            trennstelle = Math.abs(r.nextInt()) % gene;

            for (int j = 0; j < trennstelle; j++)
                nachkommen[i][j] = eltern[elter1][j];

            for (int j = trennstelle; j < gene; j++)
                nachkommen[i][j] = eltern[elter2][j];
        }
    }

    void mutieren() {
        int zz;
        for (int i = 0; i < anz; i++) {
            for (int j = 0; j < gene; j++) {
                zz = Math.abs(r.nextInt()) % 1000;
                if (zz < pm) {
                    if (nachkommen[i][j] == 0) nachkommen[i][j] = 1;
                    else nachkommen[i][j] = 0;
                }
            }
        }
    }

    void bewerten() {
        for (int i = 0; i < anz; i++) {
            for (int j = 0; j < gene; j++) {
                eltern[i][j] = nachkommen[i][j];
            }
            fitness[i] = berechneFitness(i);


            if (fitness[i] > besteFitness) {
                besteFitness = fitness[i];
                for (int j = 0; j < gene; j++) {
                    besteLsg[j] = eltern[i][j];
                }
            }
        }
        System.out.println("Beste Loesung: " + besteFitness + " Aktuelle Loesung: " + fitness[0]);
    }
}




