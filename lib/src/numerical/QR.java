package numerical;

import linearalgebra.ContenedorQR;
import linearalgebra.Matrix;
import linearalgebra.Vector;

public class QR {



    public static ContenedorQR desgloseQR(Matrix<Double> matrix) {
        int n = matrix.getNrows();
        Matrix<Double> q = new Matrix<>(n,n);
        Matrix<Double> r = new Matrix<>(n,n);

        // Iteramos sobre cada columna de matrix

        for (int j = 0; j < n; j++) {
            Vector<Double> vec = new Vector<>(matrix.getColumn(j));
            
            // RESTAR PROYECCIONES  
            for (int i = 0; i < n; i++) {
                Vector<Double> q_i = new Vector<>(q.getColumn(i));
                Vector<Double> m_i = new Vector<>(matrix.getColumn(i));
                
                r.setValue(i, j, q_i.dot(m_i));

                vec = vec.restar(q_i.multiplicarEscalar(r.getValue(i, j)));
            }

            // Norma del vector 
            r.setValue(j, j, vec.getNorm());

            // Normalizamos para obtener la columna j de Q
            //Manejo de error si la norma es muy pequeña (división por cero)
            if (vec.getNorm() > 1e-16) {
                Vector<Double> normal = vec.normalize();
                for (int k = 0; k < n; k++) {
                    q.setValue(k,j, normal.getValue(k));
                }
            }
            else {
                for (int k = 0; k < n; k++) {
                    q.setValue(k,j, 0.0);
                }
            }

        }

        return new ContenedorQR(q, r);


    } // final de desgloseQR
} 