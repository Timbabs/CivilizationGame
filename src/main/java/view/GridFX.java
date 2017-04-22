package view;

import javafx.scene.layout.GridPane;
import model.Map;
import model.TerrainTile;
import runner.CivilizationGame;

/**
 *
 * @author Timothy Baba
 */
public class GridFX extends GridPane {
    private static Map map
        = new Map(CivilizationGame.getRowNum(), CivilizationGame.getColNum());
    //private static Map map = new Map(10, 10);
    private static GridFX instance = new GridFX();


    private GridFX() {
            //pseudo singleton so that update can be called
        instance = this;
        /*for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                this.add(new TerrainTileFX(map.getTile(r, c)), c, r);
            }
        }*/
        for (int r = 0; r < CivilizationGame.getRowNum(); r++) {
            for (int c = 0; c < CivilizationGame.getColNum(); c++) {
                this.add(new TerrainTileFX(map.getTile(r, c)), c, r);
            }
        }
    }

    public static void update() {
        instance.getChildren().forEach(
                n -> ((TerrainTileFX) n).updateTileView());
    }

    public static boolean adjacent(TerrainTileFX current, TerrainTileFX other) {
        return adjacent(current.getTile(), other.getTile());
    }

    public static boolean adjacent(TerrainTile selected, TerrainTile other) {
        int srow = selected.getRow();
        int scol = selected.getCol();
        int orow = other.getRow();
        int ocol = other.getCol();
        return (Math.abs(orow - srow) <= 1) && (Math.abs(ocol - scol) <= 1);
    }

    public static Map getMap() {
        return map;
    }

    public static GridFX getInstance() {
        return instance;
    }
}
