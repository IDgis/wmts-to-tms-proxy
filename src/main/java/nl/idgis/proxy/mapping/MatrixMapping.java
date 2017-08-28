package nl.idgis.proxy.mapping;

import java.util.ArrayList;
import java.util.List;

import nl.idgis.proxy.Utils;
import nl.idgis.proxy.WMTSPropertiesException;

public class MatrixMapping {

	private List<ZoomMatrixPair> pairs;
	
	public MatrixMapping(String matrixString) throws WMTSPropertiesException {
		pairs = new ArrayList<>();
		
		String[] pairArray = matrixString.split(";");
		
		for (String pair : pairArray) {
			String[] splitPair = pair.split("=");
			
			if (!Utils.isInteger(splitPair[0])) {
				throw new WMTSPropertiesException("zoom level " + splitPair[0] + " should be of type int");
			}
			
			pairs.add(new ZoomMatrixPair(Integer.parseInt(splitPair[0]), splitPair[1]));
		}
	}
	
	public String getMatrix(int zoom) throws WMTSPropertiesException {
		for (ZoomMatrixPair pair : pairs) {
			if (pair.getZoom() == zoom) {
				return pair.getMatrix();
			}
		}
		
		throw new WMTSPropertiesException("no matrix defined for zoom level " + zoom);
	}

	private class ZoomMatrixPair {
		
		private int zoom;
		private String matrix;
		
		public ZoomMatrixPair(int zoom, String matrix) {
			super();
			this.zoom = zoom;
			this.matrix = matrix;
		}

		public int getZoom() {
			return zoom;
		}
		
		public String getMatrix() {
			return matrix;
		}
		
	}
}
