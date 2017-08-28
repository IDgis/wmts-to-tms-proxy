package nl.idgis.proxy;

public class Tile {
	
	public static final String TILE_NOT_FOUND_RESPONSE = "Coverage [minx,miny,maxx,maxy] is [0, 0, %d, %d, %d], index [x,y,z] is [%d, %d, %d]";

	private int x;
	private int y;
	private int z;

	public Tile(int x, int y, int z) throws TMSException {
		final int yMax = Utils.yMax(z);
		
		if (x > yMax || y > yMax || x < 0 || y < 0 || z < 0) {
			throw new TMSException(String.format(Tile.TILE_NOT_FOUND_RESPONSE, yMax, yMax, z, x, y, z));
		}
		
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
}
