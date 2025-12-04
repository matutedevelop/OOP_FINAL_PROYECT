package numerical;

import linearalgebra.ContenedorQR;
import linearalgebra.Matrix;
import linearalgebra.Vector;

public class QR {



    public static ContenedorQR desgloseQR(Matrix matrix) {
        int n = matrix.getNrows();
        int m = matrix.getNcols();
        Matrix q = new Matrix(n,m);
        Matrix r = new Matrix(n,m);

        // Iteramos sobre cada columna de matrix

        for (int j = 0; j < n; j++) {
            Vector vec = new Vector(matrix.getColumn(j));
            
            // RESTAR PROYECCIONES  
            for (int i = 0; i < j; i++) {
                Vector q_i = new Vector(q.getColumn(i));
                
                r.setValue(i, j, q_i.dot(vec));

                vec = vec.restar(q_i.multiplicarEscalar(r.getValue(i, j)));
            }

            // Norma del vector 
            

            // Normalizamos para obtener la columna j de Q
            //Manejo de error si la norma es muy pequeña (división por cero)
            if (vec.getNorm() > 1e-10) {
                r.setValue(j, j, vec.getNorm());

                Vector normal = vec.normalize();
                for (int k = 0; k < n; k++) {
                    q.setValue(k,j, normal.getValue(k));
                }
            }
            else {
                r.setValue(j, j, 0.0);
                for (int k = 0; k < n; k++) {
                    q.setValue(k,j, 0.0);
                }
            }

        }

        return new ContenedorQR(q, r);


    } // final de desgloseQR
} 