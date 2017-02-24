package ca.pfv.spmf.datastructures.triangularmatrix;

public interface AbstractTriangularMatrix {

	/**
	 * Return a reprensentation of the triangular matrix as a string.
	 */
    String toString();

	/**
	 * Increment the value at position i,j
	 * @param i a row id
	 * @param j a column id
	 */
    void incrementCount(int i, int j);

	/**
	 * Get the value stored at a given position
	 * @param i a row id
	 * @param j a column id
	 * @return the value.
	 */
    int getSupportForItems(int i, int j);

	void setSupport(Integer i, Integer j, int support);

}