package main;

/**
 * The DiggingAnt costs 2 food to purchase and can move diagonally up-left,
 * up-right, down-left, and down-right.
 */
public class DiggingAnt implements Ant {

    /**
     * The Tile type that corresponds to this Ant.
     */
    private Tile antTile;

    /**
     * The location on the X axis.
     */
    private int locX;

    /**
     * The location on the Y axis.
     */
    private int locY;

    /**
     * Creates a DiggingAnt with the given X and Y coordinates
     * and the Tile Type "G".
     *
     * @param locX This ant's X coordinate in the 100 x 100 world
     * @param locY This ant's Y coordinate in the 100 x 100 world
     */
    public DiggingAnt(int locX, int locY) {
        antTile = Tile.G;
        this.locX = locX;
        this.locY = locY;
    }

    /**
     * Sets the X-coordinate for this ant.
     *
     * @param locX The new X-coordinate
     */
    @Override
    public void setLocX(int locX) {
        this.locX = locX;
    }

    /**
     * Sets the Y-coordinate for this ant.
     *
     * @param locY The new Y-coordinate
     */
    @Override
    public void setLocY(int locY) {
        this.locY = locY;
    }

    /**
     * Gets the X-coordinate for this ant.
     *
     * @return The X-coordinate
     */
    @Override
    public int getLocX() {
        return locX;
    }

    /**
     * Gets the Y-coordinate for this ant.
     *
     * @return The Y-coordinate
     */
    @Override
    public int getLocY() {
        return locY;
    }

    /**
     * Gets the Tile type for this ant.
     *
     * @return This ant's Tile type
     */
    @Override
    public Tile getAntTile() {
        return antTile;
    }
}
